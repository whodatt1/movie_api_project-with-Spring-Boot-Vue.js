package com.example.mpi.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.example.mpi.mapper.UserMapper;
import com.example.mpi.payload.request.SignUpRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CheckUserIdValidator extends AbstractValidator<SignUpRequest> {
	
	@Autowired
	private UserMapper userRepository;

	@Override
	protected void doValidate(SignUpRequest dto, Errors errors) {
		System.out.println(userRepository.existsByUserId(dto.getUserId()));
		if (userRepository.existsByUserId(dto.getUserId())) {
			errors.rejectValue("userId", "아이디 중복 오류", "이미 사용중인 아이디입니다.");
		}
	}
	
}
