package com.example.mpi.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.mpi.entity.Auth;
import com.example.mpi.entity.User;
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
		
		User user = userMapper.findByUserId(username);
				
		if (user == null) {
			new UsernameNotFoundException("유저를 찾을 수 없습니다 유저 아이디 : " + username);
		}
		
		List<Auth> authList = authMapper.findByUserId(username);
			
		User buildForUser = User.builder()
								.userId(user.getUserId())
								.userPw(user.getUserPw())
								.authList(authList)
								.build();
			
		return UserDetailsImpl.build(buildForUser);
	}
}
