package org.smal2.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_name_class")
public class User {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String registration;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Date birthDate;

	private User() {
	}

	public User(String registration, String name, Date birthDate) {
		this();

		if (registration == null || registration.equals("")) {
			throw new IllegalArgumentException("Registration can not be empty.");
		}

		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Name can not be empty.");
		}

		if (birthDate == null) {
			throw new IllegalArgumentException("Birth date can not be null.");
		}

		this.name = name;
		this.registration = registration;
		this.birthDate = birthDate;
	}

	public long getId() {
		return id;
	}

	public String getRegistration() {
		return registration;
	}

	public String getName() {
		return name;
	}

	public Date getBirthDate() {
		return birthDate;
	}
}
