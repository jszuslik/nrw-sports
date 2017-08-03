package com.norulesweb.authapp.api.security;

import com.norulesweb.authapp.api.security.service.JwtUserDetailsServiceImpl;
import com.norulesweb.authapp.core.utility.UserConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.norulesweb.authapp.core.utility.UserConstants.ANONYMOUS_USER;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

	@Autowired
	private JwtUserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.header.user}")
	private String headerUser;

	@Value("${jwt.header.password}")
	private String headerPassword;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		String authUser = request.getHeader(this.headerUser);
		String authPassword = request.getHeader(this.headerPassword);
		String authToken = request.getHeader(this.tokenHeader);

		String username = jwtTokenUtil.getUsernameFromToken(authToken);

		logger.info("checking authentication for user " + username);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (jwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} else if (!StringUtils.isEmpty(authUser) || !StringUtils.isEmpty(authPassword)){
			JwtUser user = userDetailsService.loadUserByUsername(authUser);

			if(user != null) {
				if (!BCrypt.checkpw(authPassword, user.getPassword())) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UserConstants.INVALID_PASSWORD);
				}
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} else {
			logger.info("PATH INFO - {}", request.getServletPath());
			if(request.getServletPath().equals(UserConstants.ENDPOINT_FRONT_END_USER_REGISTER)) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(UserConstants.ANONYMOUS_USER);
				if (userDetails != null) {
					if (!BCrypt.checkpw(UserConstants.ANONYMOUS_PASSWORD, userDetails.getPassword())) {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UserConstants.INVALID_PASSWORD);
					}
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					logger.info("authenticated user " + UserConstants.ANONYMOUS_USER + ", setting security context");
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}

		chain.doFilter(request, response);
	}

	private AnonymousAuthenticationToken generateAnonymousAuthenticationToken() {
		SimpleGrantedAuthority grantedAuthorityImpl = new SimpleGrantedAuthority( "ROLE_ANONYMOUS" );
		return new AnonymousAuthenticationToken( ANONYMOUS_USER, ANONYMOUS_USER,
				                                       Collections.singletonList( grantedAuthorityImpl ) );
	}
}
