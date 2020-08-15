package org.midas.analytics.cassandraconnectivity.repositories.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.midas.analytics.cassandraconnectivity.model.CassandraLoginResponse;
import org.midas.analytics.cassandraconnectivity.model.LoginDetails;
import org.midas.analytics.cassandraconnectivity.model.ResponseModel;
import org.midas.analytics.cassandraconnectivity.repositories.services.CassandraLoginControllerService;
import org.midas.analytics.cassandraconnectivity.repositories.services.CassandraService;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

@Controller ("CassandraLoginControllerServiceImpl")
public class CassandraLoginControllerServiceImpl implements CassandraLoginControllerService {

	private CassandraLoginResponse loginResponseModel;
	private ResponseModel responseStatus;
	
	@PostConstruct
	public void init() {
		loginResponseModel = new CassandraLoginResponse();
		responseStatus = new ResponseModel();
	}
	
	@Autowired
	private CassandraService cassandraService;
	
	@Override
	public CassandraLoginResponse getAlluser() {
		try {
			loginResponseModel.setLoginDetails(cassandraService.getAllUser());
			responseStatus.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			responseStatus.setResponseMessage(HttpStatus.OK.name());
			loginResponseModel.setResponseStatus(responseStatus);
		}
		catch(Exception e) {
			responseStatus.setResponseCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			responseStatus.setResponseMessage(e.getMessage());
			loginResponseModel.setLoginDetails(null);
			loginResponseModel.setResponseStatus(responseStatus);
		}
		
		return loginResponseModel;
	}

	@Override
	public CassandraLoginResponse getUserByID(String id) {
		try {
			List<LoginDetails> loginDetails = new ArrayList<>();
			loginDetails.add(cassandraService.getUserByID(id));
			loginResponseModel.setLoginDetails(loginDetails);
			responseStatus.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			responseStatus.setResponseMessage(HttpStatus.OK.name());
			loginResponseModel.setResponseStatus(responseStatus);
		}
		catch(Exception e) {
			responseStatus.setResponseCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			responseStatus.setResponseMessage(e.getMessage());
			loginResponseModel.setLoginDetails(null);
			loginResponseModel.setResponseStatus(responseStatus);
		}
		
		return loginResponseModel;
	}

	@Override
	public CassandraLoginResponse addUser(LoginDetails loginDetails) {
		try {
			List<LoginDetails> loginDetailsOutput = new ArrayList<>();
			loginDetailsOutput.add(cassandraService.saveOrUpdate(loginDetails));
			loginResponseModel.setLoginDetails(loginDetailsOutput);
			responseStatus.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			responseStatus.setResponseMessage(HttpStatus.OK.name());
			loginResponseModel.setResponseStatus(responseStatus);
		}
		catch(Exception e) {
			responseStatus.setResponseCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			responseStatus.setResponseMessage(e.getMessage());
			loginResponseModel.setLoginDetails(null);
			loginResponseModel.setResponseStatus(responseStatus);
		}
		
		return loginResponseModel;
	}

	@Override
	public CassandraLoginResponse deleteUser(LoginDetails loginDetails) {
		try {
			boolean isDeleted = cassandraService.delete(loginDetails);
			if(isDeleted) {
				List<LoginDetails> loginDetailsOutput = new ArrayList<>();
				loginDetailsOutput.add(loginDetails);
				loginResponseModel.setLoginDetails(loginDetailsOutput);
				responseStatus.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				responseStatus.setResponseMessage(HttpStatus.OK.name());
				loginResponseModel.setResponseStatus(responseStatus);
			}
			else {
				throw new Exception("Unable to delete Login details + " + loginDetails);
			}
			
		}
		catch(Exception e) {
			responseStatus.setResponseCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			responseStatus.setResponseMessage(e.getMessage());
			loginResponseModel.setLoginDetails(null);
			loginResponseModel.setResponseStatus(responseStatus);
		}
		
		return loginResponseModel;		
	}

}
