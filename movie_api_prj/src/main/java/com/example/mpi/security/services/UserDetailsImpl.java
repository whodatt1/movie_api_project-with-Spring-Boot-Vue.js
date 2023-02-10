package com.example.mpi.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.mpi.entity.User;
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
	
	private long userPoint;
	
	private String profileImgPath;
	
	private boolean adult;
	
	private boolean del;
	
//	private String regDt;
	
	private String regUserId;
	
//	private String modDt;
	
	private String modUserId;
	
	private Collection<? extends GrantedAuthority> authorities;	
	
	public static UserDetailsImpl build(User user) {
		
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleNm().name()))
				.collect(Collectors.toList());
		
		return new UserDetailsImpl(
					user.getUserId(),
					user.getUserPw(),
					user.getUserNickNm(),
					user.getUserPoint(),
					user.getProfileImgPath(),
					user.isAdult(),
					user.isDel(),
					user.getRegUserId(),
					user.getModUserId(),
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	
}
