package org.smal2.service.ticket;

public class AssignTicketRequest {

	private long protocol;
	private String administrator;
	private String technician;

	public AssignTicketRequest() {
	}

	public AssignTicketRequest(long protocol, String administrator,
			String technician) {
		this.protocol = protocol;
		this.administrator = administrator;
		this.technician = technician;
	}

	public long getProtocol() {
		return protocol;
	}

	public String getAdministrator() {
		return administrator;
	}

	public String getTechnician() {
		return technician;
	}
}
