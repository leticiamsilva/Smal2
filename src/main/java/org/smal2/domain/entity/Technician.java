package org.smal2.domain.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "technician")
@DiscriminatorValue("technician")
public class Technician extends User {

	@Column(nullable = false)
	private String email;

	protected Technician() {
	}

	public Technician(String registration, String password, String name,
			String email) {
		super(registration, password, name);

		if (email == null || email.equals("")) {
			throw new IllegalArgumentException("Email can not be empty.");
		}

		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
