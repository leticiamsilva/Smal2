package org.smal2.service.ticket;

public class AssignTicketRequest {

	private String session_id;
	private long protocol;
	private String technician;

	public AssignTicketRequest() {
	}

	public AssignTicketRequest(String session_id, long protocol,
			String technician) {
		this.session_id = session_id;
		this.protocol = protocol;
		this.technician = technician;
	}

	public String getSession_id() {
		return session_id;
	}

	public long getProtocol() {
		return protocol;
	}

	public String getTechnician() {
		return technician;
	}
}
