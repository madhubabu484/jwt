package com.jwt.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.Model.Custmer;
import com.jwt.Repository.Userrepository;

@Service
public class CustmerDetails implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder pwdcoder;

	@Autowired
	private Userrepository userrepository;
	@Override
	public UserDetails  loadUserByUsername(String email) throws UsernameNotFoundException {
		 Custmer custmer = userrepository.findByemail(email);

		 if (custmer == null) {
		        throw new UsernameNotFoundException("Customer not found");
		    }

		    return new org.springframework.security.core.userdetails.User(
		    		custmer.getEmail(),
		    		custmer.getPassword(),
		            Collections.emptyList()   // No roles / authorities
		    );
		
	}


	 public boolean saveuser(Custmer u1) {
		String encode = pwdcoder.encode(u1.getPassword());
		u1.setPassword(encode);
		Custmer saveduser = userrepository.save(u1);
		return saveduser.getId() != null;	
		}

	
}
