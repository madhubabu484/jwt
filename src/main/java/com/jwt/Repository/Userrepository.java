package com.jwt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.Model.Custmer;

public interface Userrepository extends JpaRepository<Custmer, Long> {
	
	Custmer findByemail(String email);

}
