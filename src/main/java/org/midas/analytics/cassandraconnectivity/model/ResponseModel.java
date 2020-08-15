package org.midas.analytics.cassandraconnectivity.model;

public class ResponseModel {
	
	private String responseCode;
	private String responseMessage;
	
	public String getResponseCode() { return responseCode; }
	
	public void setResponseCode(String responseCode) { this.responseCode = responseCode; }
	
	public String getResponseMessage() { return responseMessage; }
	
	public void setResponseMessage(String responseMessage) { this.responseMessage = responseMessage; }

	public ResponseModel(String responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public ResponseModel() {
		super();
	}	
}
