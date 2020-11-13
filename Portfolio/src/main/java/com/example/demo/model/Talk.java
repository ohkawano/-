//package com.example.demo.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//
//import org.springframework.data.annotation.Id;
//
//@Entity
//public class Talk {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	@Column(length = 100)
//	private String userName;
//	@Column(length = 140)
//	private String cooment;
//
//	public Talk(Long id, String userName, String cooment) {
//		super();
//		this.id = id;
//		this.userName = userName;
//		this.cooment = cooment;
//	}
//
//	public Talk() {
//
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getCooment() {
//		return cooment;
//	}
//
//	public void setCooment(String cooment) {
//		this.cooment = cooment;
//	}
//
//}
