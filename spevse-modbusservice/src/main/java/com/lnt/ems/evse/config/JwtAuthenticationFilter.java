package com.lnt.ems.evse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lnt.ems.evse.serviceImpl.CustomUserDetailsService;
import com.lnt.ems.evse.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;


        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            jwtToken = requestTokenHeader.substring(7);
            try {

                username = this.jwtUtil.getUsernameFromToken(jwtToken);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);


                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

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
