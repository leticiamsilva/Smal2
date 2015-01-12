package org.smal2.service.user;

public class ListUsersResponseItem {
	private String registration;
	private String name;
	private UserType type;

	public ListUsersResponseItem(String registration, String name, UserType type) {
		this.registration = registration;
		this.name = name;
		this.type = type;
	}

	public String getRegistration() {
		return registration;
	}

	public String getName() {
		return name;
	}

	public UserType getType() {
		return type;
	}
}
