package org.smal2.service.trouble;

public class ListTroublesRequest {

	private String session_id;

	public ListTroublesRequest() {
	}

	public ListTroublesRequest(String session_id) {
		this.session_id = session_id;
	}

	public String getSession_id() {
		return session_id;
	}
}