package com.example.mpi.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.example.mpi.entity.User;
import com.example.mpi.security.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {
	
	@Value("${myweb.mpi.jwtSecretCd}")
	private String jwtSecretCd;
	
	@Value("${myweb.mpi.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	@Value("${myweb.mpi.jwtCookieName}")
	private String jwtCookie;
	
	@Value("${myweb.mpi.jwtRefreshCookieName}")
	private String jwtRefreshCookie;
	
	public ResponseCookie createJwtCookie(UserDetailsImpl userPrincipal) {
		String jwt = createTokenFromUserId(userPrincipal.getUsername());
		return createCookie(jwtCookie, jwt, "/");
	}
	
	public ResponseCookie createJwtCookie(User user) {
		String jwt = createTokenFromUserId(user.getUserId());
		return createCookie(jwtCookie, jwt, "/");
	}
	
	public ResponseCookie createRefreshJwtCookie(String refreshToken) {
		return createCookie(jwtRefreshCookie, refreshToken, "/auth/refreshtoken");
	}
	
	public String getJwtFromCookies(HttpServletRequest request) {
		return getCookieValueByName(request, jwtCookie);
	}
	
	public String getJwtRefreshFromCookies(HttpServletRequest request) {
		return getCookieValueByName(request, jwtRefreshCookie);
	}
	
	public ResponseCookie getCleanJwtCookie() {
		ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/").build();
		return cookie;
	}
	
	public ResponseCookie getCleanJwtRefreshCookie() {
		ResponseCookie cookie = ResponseCookie.from(jwtRefreshCookie, null).path("/").build();
		return cookie;
	}
	
	public String getUserIdFromJwtToken(String token) {
		
		byte[] secretByteKey = DatatypeConverter.parseBase64Binary(jwtSecretCd);
		
		Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS512.getJcaName());
		
		return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			byte[] secretByteKey = DatatypeConverter.parseBase64Binary(jwtSecretCd);
			
			Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS512.getJcaName());
			
			Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(authToken);
			return true;
		} catch (ExpiredJwtException e) {
			log.error("만료된 JWT 토큰 : {}", e.getMessage());
		} catch (JwtException e) {
			log.error("유효하지 않은 JWT 토큰 : {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT Claims String이 비어있습니다. : {}", e.getMessage());
		}
		
		return false;
	}
	
	public String createTokenFromUserId(String username) {
		
		byte[] secretByteKey = DatatypeConverter.parseBase64Binary(jwtSecretCd);
		
		Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS512.getJcaName());
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(signKey, SignatureAlgorithm.HS512)
				.compact();
	}
	
	public ResponseCookie createCookie(String name, String value, String path) {
		ResponseCookie cookie = ResponseCookie.from(name, value).path(path).maxAge(24 * 60 * 60).httpOnly(true).build();
		return cookie;
	}
	
	public String getCookieValueByName(HttpServletRequest request, String name) {
		Cookie cookie = WebUtils.getCookie(request, name);
		if (cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}
}
