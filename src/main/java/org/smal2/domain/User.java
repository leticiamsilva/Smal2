package org.smal2.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
//import javax.persistence.OneToMany;
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
	private String name;

	@Column(nullable = false)
	private String registration;

	@SuppressWarnings("unused")
	private User() {
	}

	public User(String name, String registration) {
		super();

		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Name can not be empty.");
		}
		if (registration == null || registration.equals("")) {
			throw new IllegalArgumentException("Registration can not be empty.");
		}

		this.name = name;
		this.registration = registration;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRegistration() {
		return registration;
	}
}
