package com.example.mpi.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User {
	
	@Id
	@Column(length = 100)
	private String userId;
	
	@Column(length = 1000)
	private String userPw;
	
	@Column(length = 100)
	private String userNickNm;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "tb_user_roles",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<Role>();
	
	@Column
	private long userPoint;
	
	@Column
	private String profileImgPath;
	
	@Column
	private boolean adult;
	
	@Column
	private boolean del;
	
	@Column
	private String regDt;
	
	@Column(length = 100)
	private String regUserId;
	
	@Column
	private String modDt;
	
	@Column(length = 100)
	private String modUserId;
	
}
