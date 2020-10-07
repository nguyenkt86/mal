package vn.com.springjwt.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import vn.com.springjwt.model.TokenModel;

@Component
public class JwtUtil {

//	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String TOKEN_PERFIX = "Bearer";
	private TokenModel tokenModel;

	@Autowired
	public JwtUtil(TokenModel tokenModel) {
		super();
		this.tokenModel = tokenModel;
	}

	public String createToken(String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + tokenModel.getTokenExpired());

		return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, tokenModel.getTokenSecret()).setHeaderParam("typ", "JWT").compact();
	}

	public String getUsernameFromToken(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(tokenModel.getTokenSecret()).parseClaimsJws(token).getBody();

			return String.valueOf(claims.getSubject());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public String getUsernameFromRequest(HttpServletRequest request) {
		String header = request.getHeader(TOKEN_HEADER);

		if (header == null || header.startsWith(TOKEN_PERFIX))
			return null;

		String token = header.substring(TOKEN_PERFIX.length() + 1);

		return getUsernameFromToken(token);
	}

	public boolean isTokenExpired(String token) {
		try {
			Jwts.parser().setSigningKey(tokenModel.getTokenSecret()).parseClaimsJws(token).getBody();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		String username = getUsernameFromToken(token);

		return (username.equals(userDetails.getUsername()) && isTokenExpired(token));
	}
}