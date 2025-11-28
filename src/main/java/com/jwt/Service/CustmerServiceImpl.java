package com.jwt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.DTO.RequestDto;
import com.jwt.DTO.ResponseDTO;
import com.jwt.Model.Custmer;
import com.jwt.Repository.Userrepository;

@Service
public class CustmerServiceImpl implements CustmerService {

	@Autowired
	private Userrepository userrepository;

	@Override
	public ResponseDTO saveuser(RequestDto dto) {

		Custmer u1 = new Custmer();
		u1.setName(dto.getName());
		u1.setPassword(dto.getPassword());
		u1.setEmail(dto.getEmail());
		u1.setPincode(dto.getPincode());

		Custmer user = userrepository.save(u1);

		ResponseDTO responsedto = new ResponseDTO();
		responsedto.setEmail(user.getEmail());
		responsedto.setName(user.getName());
		responsedto.setPincode(user.getPincode());
		responsedto.setMessage("User Sucessfully Created");

		return responsedto;

	}

}
