package org.smal2.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue
	private long protocol;

	@Column(nullable = false)
	private Date openDate;

	@Column(nullable = true)
	private Date closeDate;

	@Column(nullable = false)
	private String description;

	@ManyToOne(optional = false)
	private User user;

	@ManyToOne(optional = true)
	private Technician technician;

	@ManyToOne(optional = true)
	private Administrator administrator;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Status status;

	@ManyToOne(optional = false)
	private SubTrouble subTrouble;

	@ManyToOne(optional = false)
	private Computer computer;

	private Ticket() {
	}

	public Ticket(Date openDate, String description, User user,
			SubTrouble subTrouble, Computer computer) {
		this();

		if (openDate == null) {
			throw new IllegalArgumentException("Open date can not be null.");
		}

		if (description == null || description.equals("")) {
			throw new IllegalArgumentException("Description can not be empty.");
		}

		if (user == null) {
			throw new IllegalArgumentException("User can not be null.");
		}

		if (subTrouble == null) {
			throw new IllegalArgumentException("Sub-trouble can not be null.");
		}

		if (computer == null) {
			throw new IllegalArgumentException("Computer can not be null.");
		}

		this.openDate = openDate;
		this.description = description;
		this.user = user;
		this.status = Status.OPEN;
		this.subTrouble = subTrouble;
		this.computer = computer;
	}

	public long getProtocol() {
		return protocol;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public String getDescription() {
		return description;
	}

	public User getUser() {
		return user;
	}

	public Technician getTechnician() {
		return technician;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public Status getStatus() {
		return status;
	}

	public Trouble getTrouble() {
		return subTrouble.getTrouble();
	}

	public SubTrouble getSubTrouble() {
		return subTrouble;
	}

	public Computer getComputer() {
		return computer;
	}

	public void assign(Administrator admin, Technician tech) {

		if (admin == null) {
			throw new IllegalArgumentException("Administrator can not be null.");
		}

		if (tech == null) {
			throw new IllegalArgumentException("Technician can not be null.");
		}

		if (status != Status.OPEN) {
			throw new IllegalArgumentException(
					"Ticket with status different then Open cannot be assigned.");
		}

		administrator = admin;
		technician = tech;
		status = Status.IN_PROGRESS;
	}

	public void close(Status status) {
		if (this.status != Status.IN_PROGRESS) {
			throw new IllegalArgumentException(
					"Ticket with status different then 'In Progress' cannot be closed.");
		}

		if (status != Status.RESOLVED && status != Status.NOT_RESOLVED) {
			throw new IllegalArgumentException(
					"Ticket cannot be closed with final status different then 'Resolved' and 'Not Resloved'.");
		}

		this.status = status;
		this.closeDate = new Date();
	}
}
