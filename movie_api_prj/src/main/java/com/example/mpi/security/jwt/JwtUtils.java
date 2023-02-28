package com.example.mpi.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.WebUtils;

import com.example.mpi.dto.UserDto;
import com.example.mpi.security.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {
	
	@Value("${myweb.mpi.jwtSecretCd}")
	private String jwtSecretCd;
	
	@Value("${myweb.mpi.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	public String generateJwtToken(UserDetailsImpl userPrincipal) {
		return generateTokenFromUsername(userPrincipal.getUsername());
	}
	
	public String generateTokenFromUsername(String username) {
		byte[] secretByteKey = DatatypeConverter.parseBase64Binary(jwtSecretCd);
		
		Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS512.getJcaName());
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(signKey)
				.compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
		
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
		} catch (MalformedJwtException e) {
			log.error("유효하지 JWT 토큰 : {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("만료된 JWT 토큰 : {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("지원되지 않는 JWT 토큰 : {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT Claims String이 비어있습니다. : {}", e.getMessage());
		}
		
		return false;
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
