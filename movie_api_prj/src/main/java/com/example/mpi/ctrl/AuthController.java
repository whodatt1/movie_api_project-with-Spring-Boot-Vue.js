package com.example.mpi.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.mpi.payload.request.TokenRefreshRequest;
import com.example.mpi.payload.response.JwtResponse;
import com.example.mpi.payload.response.TokenRefreshResponse;
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
		
		String jwt = jwtUtils.generateJwtToken(userDetails);
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUserId());
		
		return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getUserId(), userDetails.getUserNickNm(), userDetails.getUserEmail(), userDetails.getUserPoint(), userDetails.getProfileImgPath(), userDetails.isAdult(), roles));
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
	public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
		String requestRefreshToken = request.getRefreshToken();
		
		return refreshTokenService.findByToken(requestRefreshToken)
				.map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUserId)
				.map(userId -> {
					String token = jwtUtils.generateTokenFromUsername(userId);
					return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
				})
				.orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "데이터베이스에 리프레쉬 토큰이 없습니다."));
	}
}
