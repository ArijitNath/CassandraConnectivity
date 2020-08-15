package org.midas.analytics.cassandraconnectivity.repositories.services;

import org.midas.analytics.cassandraconnectivity.model.CassandraLoginResponse;
import org.midas.analytics.cassandraconnectivity.model.LoginDetails;

public interface CassandraLoginControllerService {
	CassandraLoginResponse getAlluser();
	CassandraLoginResponse getUserByID( String id );
	CassandraLoginResponse addUser(LoginDetails loginDetails);
	CassandraLoginResponse deleteUser(LoginDetails loginDetails);
}
