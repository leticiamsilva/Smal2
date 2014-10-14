package org.smal2.service.ticket;

import java.util.Date;

import org.smal2.domain.entity.Status;

public class ListTicketsResponseItem {
	private long protocol;
	private Date open_date;
	private Date close_date;
	private String description;
	private String user;
	private String technician;
	private String administrator;
	private Status status;
	private String trouble;
	private String subTrouble;
	private String computer;

	public ListTicketsResponseItem(long protocol, Date open_date,
			Date close_date, String description, String user,
			String technician, String administrator, Status status,
			String trouble, String subTrouble, String computer) {
		super();
		this.protocol = protocol;
		this.open_date = open_date;
		this.close_date = close_date;
		this.description = description;
		this.user = user;
		this.technician = technician;
		this.administrator = administrator;
		this.status = status;
		this.trouble = trouble;
		this.subTrouble = subTrouble;
		this.computer = computer;
	}

	public long getProtocol() {
		return protocol;
	}

	public Date getOpen_date() {
		return open_date;
	}

	public Date getClose_date() {
		return close_date;
	}

	public String getDescription() {
		return description;
	}

	public String getUser() {
		return user;
	}

	public String getTechnician() {
		return technician;
	}

	public String getAdministrator() {
		return administrator;
	}

	public Status getStatus() {
		return status;
	}

	public String getTrouble() {
		return trouble;
	}

	public String getSubTrouble() {
		return subTrouble;
	}

	public String getComputer() {
		return computer;
	}
}
