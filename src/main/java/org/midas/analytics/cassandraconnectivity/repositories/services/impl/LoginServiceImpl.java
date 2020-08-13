package org.midas.analytics.cassandraconnectivity.repositories.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.midas.analytics.cassandraconnectivity.model.LoginDetails;
import org.midas.analytics.cassandraconnectivity.repositories.LoginRepository;
import org.midas.analytics.cassandraconnectivity.repositories.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	
	private LoginRepository loginRepository;
	
	@Autowired
	public LoginServiceImpl(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
	

	@Override
	public LoginDetails getUserByID(String id) {
		return loginRepository.findById(id).get();
	}

	@Override
	public LoginDetails saveOrUpdate(LoginDetails loginDetails) {
		loginRepository.save(loginDetails);
		return loginDetails;
	}

	@Override
	public boolean delete(LoginDetails loginDetails) {
		loginRepository.delete(loginDetails);
		return true;
	}

	@Override
	public List<LoginDetails> getAllUser() {
		List<LoginDetails> allUser = new ArrayList<LoginDetails>();
		loginRepository.findAll().forEach(allUser :: add);
		
		return allUser;
	}

}
