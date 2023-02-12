package com.example.mpi.ctrl;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mpi.dto.UserDto;
import com.example.mpi.entity.Role;
import com.example.mpi.entity.User;
import com.example.mpi.security.jwt.JwtUtils;
import com.example.mpi.service.RoleService;
import com.example.mpi.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUpUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
		
		// 리퀘스트로 넘어온 값 밸리데이션 체크
		if (bindingResult.hasErrors()) {
			Map<String, String> validatorResult = userService.validateHandling(bindingResult);
			System.out.println(validatorResult);
			
			return new ResponseEntity<>(validatorResult, HttpStatus.BAD_REQUEST);
		}
		
//		// 유저 정보
//		User user = User.builder()
//						.userId(userDto.getUserId())
//						.userNickNm(userDto.getUserNickNm())
//						.userPw(encoder.encode(userDto.getUserPw()))
//						.userEmail(userDto.getUserEmail())
//						.adult(userDto.isAdult())
//						.regUserId(userDto.getUserId())
//						.modUserId(userDto.getUserId())
//						.build();
//		
//		// 유저의 권한
//		List<Role> userRoles = userDto.getRoles();
//		
//		// 회원가입시 받는 기능 현재 없으므로 무조건 ROLE_USER 로 세팅
//		if (userRoles == null) {
//			Role userRole = Role.builder()
//								.userId(userDto.getUserId())
//								.roleNm("ROLE_USER")
//								.build();
//			
//			userRoles.add(userRole);
//		}
//		
//		userService.saveUser(user);
//		roleService.saveRole(userRoles);
		
		return ResponseEntity.ok().body(null);
	}
}
