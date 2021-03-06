package org.smal2.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
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
	private String registration;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column
	private String email;

	@Column(nullable = false)
	private UserType type;

	@Column
	private String session_id;

	@Column
	private Date session_timestamp;

	@Column
	private String service_token;

	@Column
	private Date service_token_timestamp;

	private User() {
	}

	public User(String registration, String password, String name, UserType type) {
		this();

		if (registration == null || registration.equals("")) {
			throw new IllegalArgumentException("Registration can not be empty.");
		}

		if (password == null || password.equals("")) {
			throw new IllegalArgumentException("Password can not be empty.");
		}

		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Name can not be empty.");
		}

		this.registration = registration;
		this.password = password;
		this.name = name;
		this.type = type;
	}

	public String getRegistration() {
		return registration;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getType() {
		return type;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public Date getSession_timestamp() {
		return session_timestamp;
	}

	public void setSession_timestamp(Date session_timestamp) {
		this.session_timestamp = session_timestamp;
	}

	public String getService_token() {
		return service_token;
	}

	public void setService_token(String service_token) {
		this.service_token = service_token;
	}

	public Date getService_token_timestamp() {
		return service_token_timestamp;
	}

	public void setService_token_timestamp(Date service_token_timestamp) {
		this.service_token_timestamp = service_token_timestamp;
	}
}
