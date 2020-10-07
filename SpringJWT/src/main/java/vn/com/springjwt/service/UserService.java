package vn.com.springjwt.service;

import java.util.List;

import vn.com.springjwt.entity.User;

public interface UserService {

	List<User> findAll();
	
	User checkUsernameAndPassword(String username, String password);

	User findByUserName(String username);
}
