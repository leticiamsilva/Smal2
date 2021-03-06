package org.smal2.domain.entity;

public enum UserType {

	STUDENT(1), TECHNICIAN(2), ADMINISTRATOR(3);

	private final int value;

	private UserType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
