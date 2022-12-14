/**
 * PPC is part of L&T SPARK Digital Energy Platform
 * (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
 * All rights reserved
 * L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
 * No claim to copyright is made for original U.S. Government Works.
 **/
package com.lnt.hmi.alerts.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lnt.hmi.alerts.serviceImpl.CustomUserDetailsService;
import com.lnt.hmi.alerts.util.JwtUtil;
import com.lnt.hmi.alerts.config.LNTLoggerConfig;

import com.lnt.hmi.alerts.dataSource.DataSourceContextHolder;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		DataSourceContextHolder.setDataSourceContext(DataSourceEnum.spevse);
		// get jwt
		// Bearer
		// validate
		String hostDB = request.getHeader("hostDB");
		LNTLoggerConfig.DATABASE=request.getHeader("hostDB");
		LNTLoggerConfig.PRODUCT_NAME=request.getHeader("application");
		DataSourceContextHolder.setDataSourceContext(hostDB);
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		// null and format
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

			try {

				username = this.jwtUtil.getUsernameFromToken(jwtToken);
				// checkActive
				Boolean isCurrentToken = this.customUserDetailsService.verifyCurrentToken(username,jwtToken);
				
				if (Boolean.TRUE.equals(isCurrentToken) && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

					UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
					// security

					// token AUthentication
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());

					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

					// update lastActive
					this.customUserDetailsService.updateUserActivetime(username);

				} else {
					logger.info("Token is not validated...");
				}

			} catch (Exception e) {
				logger.error(e.toString());
			}

		}

		filterChain.doFilter(request, response);

	}
}
