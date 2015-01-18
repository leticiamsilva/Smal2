package org.smal2.service.computer;

public class ListComputersRequest {

	private String session_id;
	private String laboratory_name;

	public ListComputersRequest() {
	}

	public ListComputersRequest(String session_id, String laboratory_name) {
		this.session_id = session_id;
		this.laboratory_name = laboratory_name;
	}

	public String getSession_id() {
		return session_id;
	}

	public String getLaboratory_name() {
		return laboratory_name;
	}
}