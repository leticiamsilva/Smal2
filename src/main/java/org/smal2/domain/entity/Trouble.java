package org.smal2.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trouble")
public class Trouble {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String name;

	@SuppressWarnings("unused")
	private Trouble() {
	}

	public Trouble(String name) {
		super();

		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Name can not be empty.");
		}

		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
