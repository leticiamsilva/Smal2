package org.smal2.service.subtrouble;

public class RegisterSubTroubleRequest {

	private String session_id;
	private String name;
	private String trouble_name;

	public RegisterSubTroubleRequest() {
	}

	public RegisterSubTroubleRequest(String session_id, String name,
			String trouble_name) {
		this.session_id = session_id;
		this.name = name;
		this.trouble_name = trouble_name;
	}

	public String getSession_id() {
		return session_id;
	}

	public String getName() {
		return name;
	}

	public String getTrouble_name() {
		return trouble_name;
	}
}
