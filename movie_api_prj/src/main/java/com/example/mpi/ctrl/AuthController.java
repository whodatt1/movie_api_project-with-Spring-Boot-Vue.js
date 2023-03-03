package com.example.mpi.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mpi.dto.AuthDto;
import com.example.mpi.dto.RefreshTokenDto;
import com.example.mpi.dto.UserDto;
import com.example.mpi.payload.request.LoginRequest;
import com.example.mpi.payload.request.SignUpRequest;
import com.example.mpi.payload.response.JwtResponse;
import com.example.mpi.payload.response.MessageResponse;
import com.example.mpi.payload.response.TokenRefreshResponse;
import com.example.mpi.security.jwt.JwtUtils;
import com.example.mpi.security.jwt.exception.TokenRefreshException;
import com.example.mpi.security.services.RefreshTokenService;
import com.example.mpi.security.services.UserDetailsImpl;
import com.example.mpi.service.AuthService;
import com.example.mpi.service.UserService;
import com.example.mpi.validator.CheckUserIdValidator;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	// 리프레시 쿠키 이름
	@Value("${myweb.mpi.jwtRefreshCookieName}")
    private String jwtRefreshCookie;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Autowired
	CheckUserIdValidator checkUserIdValidator;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest, Errors errors, HttpServletResponse resp) {
		
		// 리퀘스트로 넘어온 값 밸리데이션 체크
		if (errors.hasErrors()) {
			Map<String, String> validatorResult = userService.validateHandling(errors);
			return ResponseEntity.badRequest().body(validatorResult);
		}
		System.out.println("여기");
		System.out.println(loginRequest.getUserId());
		System.out.println(loginRequest.getUserPw());
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getUserPw()));
		System.out.println("여기2");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("여기3");
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		System.out.println("여기4");
		String jwt = jwtUtils.generateJwtToken(userDetails);
		System.out.println("여기5");
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		System.out.println("여기6");
		RefreshTokenDto refreshToken = refreshTokenService.createRefreshToken(userDetails.getUserId());
		System.out.println("여기7");
		Cookie cookie = new Cookie(jwtRefreshCookie, refreshToken.getToken());
		
		cookie.setMaxAge(24 * 60 * 60);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		
		resp.addCookie(cookie);
		
		return ResponseEntity.ok()
				.body(new JwtResponse(jwt ,userDetails.getUserId(), userDetails.getUserNickNm(), userDetails.isAdult(), roles));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser(HttpServletResponse resp) {
		Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principle.toString() != "anonymousUser") {
			String userId = ((UserDetailsImpl) principle).getUserId();
			refreshTokenService.deleteByUserId(userId);
		}
		
		Cookie cookie = new Cookie(jwtRefreshCookie, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		
		resp.addCookie(cookie);
		
		return ResponseEntity.ok()
				.body(null);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest, Errors errors) {
		
		// 여기서 따로 중복체크 해야함 코드 작성 예정
		
		// 리퀘스트로 넘어온 값 밸리데이션 체크
		if (errors.hasErrors()) {
			Map<String, String> validatorResult = userService.validateHandling(errors);
			return ResponseEntity.badRequest().body(validatorResult);
			
		}
		
		// 유저 정보
		UserDto user = UserDto.builder()
						.userId(signUpRequest.getUserId())
						.userNickNm(signUpRequest.getUserNickNm())
						.userPw(encoder.encode(signUpRequest.getUserPw()))
						.userEmail(signUpRequest.getUserEmail())
						.adult(signUpRequest.isAdult())
						.regUserId(signUpRequest.getUserId())
						.modUserId(signUpRequest.getUserId())
						.build();
		
		// 회원가입시 권한 받는 기능 현재 없으므로 무조건 ROLE_USER 로 세팅
		List<AuthDto> userAuths = new ArrayList<AuthDto>();
		
		AuthDto userAuth = AuthDto.builder()
							.userId(signUpRequest.getUserId())
							.auth("ROLE_USER")
							.build();
		userAuths.add(userAuth);
		
		userService.saveUser(user);
		authService.saveAuth(userAuths);
		
		return ResponseEntity.ok().body(null);
	}
	
	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshToken(HttpServletRequest request) {
		String refreshToken = jwtUtils.getCookieValueByName(request, jwtRefreshCookie);
		
		if ((refreshToken != null) && (refreshToken.length() > 0)) {
			return refreshTokenService.findByToken(refreshToken)
					.map(refreshTokenService::verifyExpiration)
					.map(RefreshTokenDto::getUserId)
					.map(userId -> {
						String jwt = jwtUtils.generateTokenFromUsername(userId);
						
						return ResponseEntity.ok(new TokenRefreshResponse(jwt));
					})
					.orElseThrow(() -> 
					new TokenRefreshException(refreshToken, "데이터베이스에 리프레쉬 토큰이 없습니다."));
		}
		
		return ResponseEntity.badRequest().body(null);
	}
}
