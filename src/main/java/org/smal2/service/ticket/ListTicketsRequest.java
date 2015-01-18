package org.smal2.service.ticket;

public class ListTicketsRequest {

	private String session_id;

	public ListTicketsRequest() {
	}

	public ListTicketsRequest(String session_id) {
		this.session_id = session_id;
	}

	public String getSession_id() {
		return session_id;
	}
}