package org.smal2.service.user;

public enum UserType {

	STUDENT(1), TECHNICHAN(2), ADMINISTRATOR(3);

	private final int value;

	UserType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
