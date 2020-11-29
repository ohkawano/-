package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Table(name = "adminisrators")
@Entity
public class Adminisrators {
	@Id
	@NotBlank
	@Column(name = "user_name")
	private String user_name;
	@NotBlank
	private String password;

	public Adminisrators(@NotBlank String user_name, @NotBlank String password) {
		super();
		this.user_name = user_name;
		this.password = password;
	}

	public Adminisrators() {
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
