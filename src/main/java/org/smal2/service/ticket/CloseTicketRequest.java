package org.smal2.service.ticket;

import org.smal2.domain.entity.Status;

public class CloseTicketRequest {

	private long protocol;
	private String technician;
	private Status status;

	public CloseTicketRequest() {
	}

	public CloseTicketRequest(long protocol, String technician, Status status) {
		this.protocol = protocol;
		this.technician = technician;
		this.status = status;
	}

	public long getProtocol() {
		return protocol;
	}

	public String getTechnician() {
		return technician;
	}

	public Status getStatus() {
		return status;
	}
}
