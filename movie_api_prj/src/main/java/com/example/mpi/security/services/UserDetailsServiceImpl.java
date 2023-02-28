package com.example.mpi.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.mpi.dto.AuthDto;
import com.example.mpi.dto.UserDto;
import com.example.mpi.mapper.AuthMapper;
import com.example.mpi.mapper.UserMapper;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	AuthMapper authMapper;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDto user = userMapper.findByUserId(username);
				
		if (user == null) {
			new UsernameNotFoundException("유저를 찾을 수 없습니다 유저 아이디 : " + username);
		}
		
		List<AuthDto> authList = authMapper.findByUserId(username);
			
		UserDto buildForUser = UserDto.builder()
								.userId(user.getUserId())
								.userPw(user.getUserPw())
								.userNickNm(user.getUserNickNm())
								.userEmail(user.getUserEmail())
								.userPoint(user.getUserPoint())
								.profileImgPath(user.getProfileImgPath())
								.authList(authList)
								.build();
			
		return UserDetailsImpl.build(buildForUser);
	}
}
