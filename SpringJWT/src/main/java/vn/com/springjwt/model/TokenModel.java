package vn.com.springjwt.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.auth")
public class TokenModel {

	private String tokenSecret;
	private Long tokenExpired;

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

	public Long getTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(Long tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

}