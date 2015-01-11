package org.smal2.service.auth;

public class LoginUserResponse {

	private String session;
	private String registration;
	private String name;
	private int type;
	private String message;

	public LoginUserResponse(String session, String registration, String name,
			int type, String message) {
		this.session = session;
		this.registration = registration;
		this.name = name;
		this.type = type;
		this.message = message;
	}

	public String getSession() {
		return session;
	}

	public String getRegistration() {
		return registration;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}
}
