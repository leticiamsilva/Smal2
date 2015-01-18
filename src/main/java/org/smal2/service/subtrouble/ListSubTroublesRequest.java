package org.smal2.service.subtrouble;

public class ListSubTroublesRequest {

	private String session_id;
	private String trouble_name;

	public ListSubTroublesRequest() {
	}

	public ListSubTroublesRequest(String session_id, String trouble_name) {
		this.session_id = session_id;
		this.trouble_name = trouble_name;
	}

	public String getSession_id() {
		return session_id;
	}

	public String getTrouble_name() {
		return trouble_name;
	}
}