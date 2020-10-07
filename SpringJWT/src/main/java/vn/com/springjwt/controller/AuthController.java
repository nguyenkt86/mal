package vn.com.springjwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.springjwt.entity.User;
import vn.com.springjwt.model.AuthenModel;
import vn.com.springjwt.model.LoginModel;
import vn.com.springjwt.service.UserService;
import vn.com.springjwt.utils.JwtUtil;
import vn.com.springjwt.utils.ResponseUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserService userService;
	private JwtUtil jwtUtil;

	public AuthController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(HttpStatus status, @RequestBody LoginModel loginModel) {
		try {
			User user = userService.checkUsernameAndPassword(loginModel.getUsername(), loginModel.getPassword());

			String token = jwtUtil.createToken(loginModel.getUsername());

			AuthenModel authenModel = new AuthenModel(token, "Bearer", user);

			return ResponseUtil.getSuccess(authenModel);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.getError(status.BAD_REQUEST, "Login error", e.getMessage());
		}
	}
}
