package org.midas.analytics.cassandraconnectivity.model;

import java.util.Date;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(value = "loginDetails")
public class LoginDetails {
	
	@PrimaryKey
	private String loginUserName;
	private String loginEmailID;
	private String loginPassword;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date lastLoginTime;
	
	public String getLoginUserName() { return loginUserName; }
	
	public void setLoginUserName(String loginUserName) { this.loginUserName = loginUserName; }
	
	public String getLoginEmailID() { return loginEmailID; }
	
	public void setLoginEmailID(String loginEmailID) { this.loginEmailID = loginEmailID; }
	
	public String getLoginPassword() { return loginPassword; }
	
	public void setLoginPassword(String loginPassword) { this.loginPassword = loginPassword; }
	
	public Date getLastLoginTime() { return lastLoginTime; }
	
	public void setLastLoginTime(Date lastLoginTime) { this.lastLoginTime = lastLoginTime; }	
}
