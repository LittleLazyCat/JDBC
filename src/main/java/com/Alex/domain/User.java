package com.Alex.domain;

public class User {
	private int userId;
	private String userName;
	private String userPsw;
	private String userEmail;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int user_Id) {
		this.userId = user_Id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String user_Name) {
		this.userName = user_Name;
	}
	public String getUserPsw() {
		return userPsw;
	}
	public void setUserPsw(String user_Psw) {
		this.userPsw = user_Psw;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String user_Email) {
		this.userEmail = user_Email;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPsw=" + userPsw + ", userEmail=" + userEmail
				+ "]";
	}
	
}
