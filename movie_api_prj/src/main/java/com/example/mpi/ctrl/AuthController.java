package com.example.mpi.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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

import com.example.mpi.entity.Auth;
import com.example.mpi.entity.RefreshToken;
import com.example.mpi.entity.User;
import com.example.mpi.payload.request.LoginRequest;
import com.example.mpi.payload.request.SignUpRequest;
import com.example.mpi.payload.response.UserInfoResponse;
import com.example.mpi.security.jwt.JwtUtils;
import com.example.mpi.security.jwt.exception.TokenRefreshException;
import com.example.mpi.security.services.RefreshTokenService;
import com.example.mpi.security.services.UserDetailsImpl;
import com.example.mpi.service.AuthService;
import com.example.mpi.service.UserService;
import com.example.mpi.validator.CheckUserIdValidator;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
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
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest, Errors errors) {
		
		// 리퀘스트로 넘어온 값 밸리데이션 체크
		if (errors.hasErrors()) {
			Map<String, String> validatorResult = userService.validateHandling(errors);
			return ResponseEntity.badRequest().body(validatorResult);
		}
		
		// 아이디와 비밀번호로 security가 알아볼 수 있는 token 객체로 변환
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getUserPw()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUserId());
		
		ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());
		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
				.body(new UserInfoResponse(userDetails.getUserId(), userDetails.getUserNickNm(), userDetails.isAdult(), roles));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser() {
		Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principle.toString() != "anonymousUser") {
			String userId = ((UserDetailsImpl) principle).getUserId();
			refreshTokenService.deleteByUserId(userId);
		}
		
		ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
		ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();
		
		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
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
		User user = User.builder()
						.userId(signUpRequest.getUserId())
						.userNickNm(signUpRequest.getUserNickNm())
						.userPw(encoder.encode(signUpRequest.getUserPw()))
						.userEmail(signUpRequest.getUserEmail())
						.adult(signUpRequest.isAdult())
						.regUserId(signUpRequest.getUserId())
						.modUserId(signUpRequest.getUserId())
						.build();
		
		// 회원가입시 권한 받는 기능 현재 없으므로 무조건 ROLE_USER 로 세팅
		List<Auth> userAuths = new ArrayList<Auth>();
		
		Auth userAuth = Auth.builder()
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
		System.out.println("리프레시토큰 주세염!!");
		String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);
		
		if ((refreshToken != null) && (refreshToken.length() > 0)) {
			return refreshTokenService.findByToken(refreshToken)
					.map(refreshTokenService::verifyExpiration)
					.map(RefreshToken::getUserId)
					.map(userId -> {
						ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userId);
						
						return ResponseEntity.ok()
								.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
								.body(null);
					})
					.orElseThrow(() -> new TokenRefreshException(refreshToken, "데이터베이스에 리프레쉬 토큰이 없습니다."));
		}
		
		return ResponseEntity.badRequest().body(null);
	}
}
