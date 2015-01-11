package org.smal2.service.auth;

public class LoginUserResponse {

	private String session_id;
	private String registration;
	private String name;
	private int type;
	private String message;

	public LoginUserResponse(String session_id, String registration,
			String name, int type, String message) {
		this.session_id = session_id;
		this.registration = registration;
		this.name = name;
		this.type = type;
		this.message = message;
	}

	public String getSession_id() {
		return session_id;
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
