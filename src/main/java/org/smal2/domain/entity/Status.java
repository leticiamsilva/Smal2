package org.smal2.domain.entity;

public enum Status {

	OPEN(1), IN_PROGRESS(2), RESOLVED(3), NOT_RESOLVED(4);

	private final int value;

	Status(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
