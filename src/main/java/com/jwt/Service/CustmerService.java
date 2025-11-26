package com.jwt.Service;

import org.springframework.stereotype.Service;

import com.jwt.DTO.RequestDto;
import com.jwt.DTO.ResponseDTO;

@Service
public interface CustmerService {
	
	
	ResponseDTO saveuser(RequestDto dto);

}
