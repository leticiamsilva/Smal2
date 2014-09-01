package org.smal2.service.user;

public class ListUsersResponseItem {
	private long id;
	private String registration;
	private String name;
	private int type;

	public ListUsersResponseItem(long id, String registration, String name,
			int type) {
		this.id = id;
		this.registration = registration;
		this.name = name;
		this.type = type;
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

	public int getType() {
		return type;
	}
}
