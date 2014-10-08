package org.smal2.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "laboratory")
public class Laboratory {

	@Id
	@Column(nullable = false)
	private String name;

	private Laboratory() {
	}

	public Laboratory(String name) {
		this();

		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Name can not be empty.");
		}

		this.name = name;
	}

	public String getName() {
		return name;
	}
}
