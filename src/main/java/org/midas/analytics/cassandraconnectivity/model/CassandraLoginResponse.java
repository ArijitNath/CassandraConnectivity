package org.midas.analytics.cassandraconnectivity.model;

import java.util.List;

public class CassandraLoginResponse {
	private List<LoginDetails> loginDetails;
	private ResponseModel responseStatus;
	
	public List<LoginDetails> getLoginDetails() { return loginDetails; }
	
	public void setLoginDetails(List<LoginDetails> loginDetails) { this.loginDetails = loginDetails; }
	
	public ResponseModel getResponseStatus() { return responseStatus; }
	
	public void setResponseStatus(ResponseModel responseStatus) { this.responseStatus = responseStatus; }

	public CassandraLoginResponse(List<LoginDetails> loginDetails, ResponseModel responseStatus) {
		super();
		this.loginDetails = loginDetails;
		this.responseStatus = responseStatus;
	}

	public CassandraLoginResponse() {
		super();
	}	
}
