package com.example.mpi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "tb_movie_genre_cd")
public class GenreCd {
	
	@Id
	private long id;
	
	@Column(length = 100)
	private String name;
	
	@Column
	private String dataRegAt;
	
	@Column
	private String dataModAt;
}
