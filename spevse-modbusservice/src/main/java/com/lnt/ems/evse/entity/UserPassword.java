/**
* EVSE is part of L&T SPARK Digital Energy Platform
* (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
* All rights reserved
* L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
* No claim to copyright is made for original U.S. Government Works.
**/
package com.lnt.ems.evse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the _user_password database table.
 * 
 */
@Entity
@Table(name = "lntds_user_password")
public class UserPassword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "is_active", length = 1)
	private String isActive;

	@Column(name = "password")
	private String password;

	@Column(name = "password_answer", length = 50)
	private String passwordAnswer;

	@Column(name = "password_hint", length = 50)
	private String passwordHint;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private Users users;

	public UserPassword() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAnswer() {
		return this.passwordAnswer;
	}

	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}

	public String getPasswordHint() {
		return this.passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	@JsonIgnore
	public Users getUsers() {
		return this.users;
	}

	@JsonProperty
	public void setUsers(Users users) {
		this.users = users;
	}

}