package vn.com.springjwt.model;

import vn.com.springjwt.entity.User;

public class AuthenModel {

	private String token;
	private String type;
	private User user;

	public AuthenModel(String token, String type, User user) {
		super();
		this.token = token;
		this.type = type;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
