package com.example.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;
	private String loginId;
	private String password;
	private String auth;

	@Builder
	public User(String loginId, String password, String auth) {
		this.loginId = loginId;
		this.password = password;
		this.auth = auth;
	}

}
