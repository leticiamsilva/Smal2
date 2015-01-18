package org.smal2.service.user;

public class ListUsersRequest {

	private String session_id;

	public ListUsersRequest() {
	}

	public ListUsersRequest(String session_id) {
		this.session_id = session_id;
	}

	public String getSession_id() {
		return session_id;
	}
}