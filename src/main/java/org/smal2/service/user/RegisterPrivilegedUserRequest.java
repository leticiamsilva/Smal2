package org.smal2.service.user;

import java.util.Date;

public class RegisterPrivilegedUserRequest {

	private String registration;
	private String name;
	private String password;
	private Date birth_date;
	private UserType type;

	public RegisterPrivilegedUserRequest() {
	}

	public RegisterPrivilegedUserRequest(String registration, String password,
			String name, Date birth_date, UserType type) {
		this.registration = registration;
		this.password = password;
		this.name = name;
		this.birth_date = birth_date;
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

	public Date getBirth_date() {
		return birth_date;
	}

	public UserType getType() {
		return type;
	}
}
