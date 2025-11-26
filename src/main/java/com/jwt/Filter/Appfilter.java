package com.jwt.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.Model.Custmer;
import com.jwt.Service.CustmerDetails;
import com.jwt.Service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Appfilter  extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtservice;
	
	@Autowired
	private CustmerDetails custmerdetails;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		   String authHeader = request.getHeader("Authorization");
	        String token = null;
	        String username = null;
	        if(authHeader != null && authHeader.startsWith("Bearer ")){
	            token = authHeader.substring(7);
	            username = jwtservice.extractUsername(token);
	        }

	        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
	        	Custmer custmerdetails = custmerdetails.loadUserByUsername(email);
	            if(jwtservice.validateToken(token, custmerdetails)){
	                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(custmerdetails, null, custmerdetails.getAuthorities());
	                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	            }
	        }
	        filterChain.doFilter(request, response);
	    }

		
	}


