package vn.com.springjwt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.com.springjwt.entity.User;
import vn.com.springjwt.repository.UserRepository;
import vn.com.springjwt.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository UserRepository;

	public UserServiceImpl(UserRepository UserRepository) {
		this.UserRepository = UserRepository;
	}

	@Override
	public User checkUsernameAndPassword(String username, String password) {
		User user = UserRepository.findByUsername(username);
		if (username == null || username.trim().length() == 0) {
			throw new RuntimeException("Username is required!");
		}

		if (password == null || password.trim().length() == 0) {
			throw new RuntimeException("Password is required!");
		}

		if (user == null) {
			throw new RuntimeException("Username or password invalid!");
		}

		if (!user.getPassword().equals(password)) {
			throw new RuntimeException("Username or password invalid!");
		}

		return user;
	}

	@Override
	public User findByUserName(String username) {

		return null;
	}

	@Override
	public List<User> findAll() {
		return UserRepository.findAll();
	}

}
