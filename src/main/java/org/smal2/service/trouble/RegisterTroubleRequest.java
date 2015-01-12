package org.smal2.service.trouble;

public class RegisterTroubleRequest {

	private String session_id;
	private String name;

	public RegisterTroubleRequest() {
	}

	public RegisterTroubleRequest(String session_id, String name) {
		this.session_id = session_id;
		this.name = name;
	}

	public String getSession_id() {
		return session_id;
	}

	public String getName() {
		return name;
	}
}
