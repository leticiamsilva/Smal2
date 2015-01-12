package org.smal2.service.laboratory;

public class RegisterLaboratoryRequest {

	private String session_id;
	private String name;

	public RegisterLaboratoryRequest() {
	}

	public RegisterLaboratoryRequest(String session_id, String name) {
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
