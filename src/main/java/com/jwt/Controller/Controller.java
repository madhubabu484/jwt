package com.jwt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.Model.Custmer;
import com.jwt.Service.CustmerDetails;

@RestController
public class Controller {

	@Autowired
	private CustmerDetails userservice;

	@Autowired
	private AuthenticationManager authmanager;

	// for User Login

	@PostMapping("/login")
	public ResponseEntity<String> userlogin(@RequestBody Custmer c) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(c.getEmail(),
				c.getPassword());
		Authentication authenticate = authmanager.authenticate(token);

		boolean status = authenticate.isAuthenticated();

		if (status) {
			return new ResponseEntity<String>("WelcomeUser", HttpStatus.CREATED);

		} else {
			return new ResponseEntity<String>("Please enter the Correct Credentals", HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Custmer user) {
		boolean status = userservice.saveuser(user);
		if (status) {
			return new ResponseEntity<>("Sucess", HttpStatus.CREATED);
		}

		else {
			return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
