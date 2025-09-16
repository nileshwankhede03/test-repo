package com.marvellous.booking_system.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AuthResponse {

	private String token;
    private String username;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public AuthResponse(String token, String username) {
		super();
		this.token = token;
		this.username = username;
	}
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AuthResponse [token=" + token + ", username=" + username + "]";
	}
    
    
}
