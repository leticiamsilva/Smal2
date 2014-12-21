package org.smal2.service.auth;

public class RemoteLoginResponse {

	private String token;
	private String error;

	public RemoteLoginResponse(String token, String error) {
		this.token = token;
		this.error = error;
	}

	public String getToken() {
		return token;
	}

	public String getError() {
		return error;
	}
}
