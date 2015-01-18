package org.smal2.service.laboratory;

public class ListLaboratoriesRequest {

	private String session_id;

	public ListLaboratoriesRequest() {
	}

	public ListLaboratoriesRequest(String session_id) {
		this.session_id = session_id;
	}

	public String getSession_id() {
		return session_id;
	}
}