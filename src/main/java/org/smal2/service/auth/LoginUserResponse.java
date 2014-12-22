package org.smal2.service.auth;

public class LoginUserResponse {

	private String session;
	private String message;

	public LoginUserResponse(String session, String message) {
		this.session = session;
		this.message = message;
	}

	public String getSession() {
		return session;
	}

	public String getMessage() {
		return message;
	}
}
