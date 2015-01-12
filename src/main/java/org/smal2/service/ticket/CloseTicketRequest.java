package org.smal2.service.ticket;

import org.smal2.domain.entity.Status;

public class CloseTicketRequest {

	private String session_id;
	private long protocol;
	private Status status;

	public CloseTicketRequest() {
	}

	public CloseTicketRequest(String session_id, long protocol, Status status) {
		this.session_id = session_id;
		this.protocol = protocol;
		this.status = status;
	}

	public String getSession_id() {
		return session_id;
	}

	public long getProtocol() {
		return protocol;
	}

	public Status getStatus() {
		return status;
	}
}
