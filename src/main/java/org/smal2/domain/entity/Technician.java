package org.smal2.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "technician")
@DiscriminatorValue("technician")
public class Technician extends User {

	@Column(nullable = false)
	private String password;

	public Technician(String registration, String name, Date birthDate,
			String password) {
		super(registration, name, birthDate);

		if (password == null || password.equals("")) {
			throw new IllegalArgumentException("Password can not be empty.");
		}

		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
