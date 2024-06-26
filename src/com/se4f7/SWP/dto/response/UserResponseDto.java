package com.se4f7.SWP.dto.response;

import com.se4f7.SWP.enums.UserStatus;

public class UserResponseDto {

	private int id;
	private String username;
	private UserStatus status;
	private String firstName;
	private String lastName;
	private int role;

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserResponseDto(int id, String username, UserStatus status) {
		super();
		this.id = id;
		this.username = username;
		this.status = status;
	}

	public UserResponseDto() {
		super();
	}

}
