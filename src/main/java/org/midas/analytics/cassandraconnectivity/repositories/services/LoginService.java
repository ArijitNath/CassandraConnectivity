package org.midas.analytics.cassandraconnectivity.repositories.services;

import java.util.List;

import org.midas.analytics.cassandraconnectivity.model.LoginDetails;

public interface LoginService {
	List<LoginDetails> getAllUser();
	LoginDetails getUserByID(String id);
	LoginDetails saveOrUpdate(LoginDetails loginDetails);
	boolean delete(LoginDetails loginDetails);
}
