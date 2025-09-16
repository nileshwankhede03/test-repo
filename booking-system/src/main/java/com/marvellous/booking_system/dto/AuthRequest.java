package com.marvellous.booking_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthRequest {
    private String username;
    private String password;
	public Object getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AuthRequest [username=" + username + ", password=" + password + "]";
	}
	
	
}

