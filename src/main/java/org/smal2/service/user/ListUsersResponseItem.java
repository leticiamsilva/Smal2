package org.smal2.service.user;

public class ListUsersResponseItem {
	private String registration;
	private String name;
	private int type;

	public ListUsersResponseItem(String registration, String name, int type) {
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

	public int getType() {
		return type;
	}
}
