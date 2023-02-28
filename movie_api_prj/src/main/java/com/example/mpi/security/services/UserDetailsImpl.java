package com.example.mpi.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.mpi.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	@JsonIgnore
	private String userPw;
	
	private String userNickNm;
	
	private String userEmail;
	
	private long userPoint;
	
	private String profileImgPath;
	
	private boolean adult;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public static UserDetailsImpl build(UserDto user) {
		
		List<GrantedAuthority> authorities = user.getAuthList()
			.stream()
			.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
			.collect(Collectors.toList());
		
		return new UserDetailsImpl(
					user.getUserId(),
					user.getUserPw(),
					user.getUserNickNm(),
					user.getUserEmail(),
					user.getUserPoint(),
					user.getProfileImgPath(),
					user.isAdult(),
					authorities
				);
	}
	
	@Override
	public String getPassword() {
		return userPw;
	}

	@Override
	public String getUsername() {
		return userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		UserDetailsImpl user = (UserDetailsImpl) obj;
		return Objects.equals(userId, user.userId);
	}
	
}
