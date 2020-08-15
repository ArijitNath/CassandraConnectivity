package org.midas.analytics.cassandraconnectivity.repositories.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.midas.analytics.cassandraconnectivity.model.LoginDetails;
import org.midas.analytics.cassandraconnectivity.repositories.CassandraRepository;
import org.midas.analytics.cassandraconnectivity.repositories.services.CassandraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CassandraServiceImpl implements CassandraService {
	
	private CassandraRepository cassandraRepository;
	
	@Autowired
	public CassandraServiceImpl(CassandraRepository cassandraRepository) {
		this.cassandraRepository = cassandraRepository;
	}
	

	@Override
	public LoginDetails getUserByID(String id) {
		return cassandraRepository.findById(id).get();
	}

	@Override
	public LoginDetails saveOrUpdate(LoginDetails loginDetails) {
		cassandraRepository.save(loginDetails);
		return loginDetails;
	}

	@Override
	public boolean delete(LoginDetails loginDetails) {
		cassandraRepository.delete(loginDetails);
		return true;
	}

	@Override
	public List<LoginDetails> getAllUser() {
		List<LoginDetails> allUser = new ArrayList<LoginDetails>();
		cassandraRepository.findAll().forEach(allUser :: add);
		
		return allUser;
	}

}
