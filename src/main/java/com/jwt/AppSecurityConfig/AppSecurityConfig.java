package com.jwt.AppSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jwt.Service.CustmerDetails;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Autowired
	private CustmerDetails custmerdetails;

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@SuppressWarnings("deprecation")
	public DaoAuthenticationProvider authprovider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(encoder());
		authProvider.setUserDetailsService(custmerdetails);
		return authProvider;
	}

	@Bean
	@SneakyThrows
	public AuthenticationManager authmanager(AuthenticationConfiguration config) throws Exception {

		return config.getAuthenticationManager();
	}

	@Bean
	@SneakyThrows
	public SecurityFilterChain security(HttpSecurity security) {

		security.authorizeHttpRequests((req) -> {
			req.requestMatchers("/register", "/login").permitAll().anyRequest().authenticated();
		});

		return security.csrf().disable().build();

	}

}
