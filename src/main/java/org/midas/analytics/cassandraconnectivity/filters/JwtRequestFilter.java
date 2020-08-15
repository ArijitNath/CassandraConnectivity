package org.midas.analytics.cassandraconnectivity.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.midas.analytics.cassandraconnectivity.constants.CassandraConstants;
import org.midas.analytics.cassandraconnectivity.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private Logger LOGGER = Logger.getLogger(JwtRequestFilter.class.getName());

	
	@Autowired
	private UserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader(CassandraConstants.AUTHORIZATION);
		
		String username = null;
		String jwt = null;
		
		if( authorizationHeader != null && authorizationHeader.startsWith(CassandraConstants.BEARER) ) {
			LOGGER.log(Level.INFO, "JwtRequestFilter:doFilterInternal :: Authorization Header exists, extracting JWT and Username");
			jwt = authorizationHeader.substring(7);
			username = jwtUtils.extractUserName(jwt);
		}
		
		if( username != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
			
			if( jwtUtils.validateToken(jwt, userDetails) ) {
				LOGGER.log(Level.INFO, "JwtRequestFilter:doFilterInternal :: JWT Token Validated");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request)
						);
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
