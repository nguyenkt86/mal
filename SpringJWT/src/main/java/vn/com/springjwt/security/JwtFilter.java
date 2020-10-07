package vn.com.springjwt.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import vn.com.springjwt.service.CustomUserService;
import vn.com.springjwt.utils.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private static final String TOKEN_HEADER = "Authorization";
	private static final String TOKEN_PERFIX = "Bearer";

	private CustomUserService customUserService;
	private JwtUtil jwtUtil;
	private AntPathMatcher pathMatcher = new AntPathMatcher();
	
	public JwtFilter(CustomUserService customUserService, JwtUtil jwtUtil) {
		super();
		this.customUserService = customUserService;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader(TOKEN_HEADER);

		String token = null;
		String userName = null;

		if (header != null && header.startsWith(TOKEN_PERFIX)) {
			token = header.substring(TOKEN_PERFIX.length() + 1);
			userName = jwtUtil.getUsernameFromToken(token);
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = customUserService.loadUserByUsername(userName);

			if (jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(final HttpServletRequest request) throws ServletException {
		if (request.getMethod().equals("OPTIONS")) {
			return true;
		}

		Set<String> skipUrls = new HashSet<>(Arrays.asList("/auth/**", "/"));

		return skipUrls.stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
	}
}
