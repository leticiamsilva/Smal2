package org.smal2.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subtrouble")
public class SubTrouble {

	@Id
	@Column(nullable = false)
	private String name;

	@ManyToOne(optional = false)
	private Trouble trouble;

	private SubTrouble() {
	}

	public SubTrouble(String name, Trouble trouble) {
		this();

		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Name can not be empty.");
		}

		if (trouble == null) {
			throw new IllegalArgumentException("Trouble can not be null.");
		}

		this.name = name;
		this.trouble = trouble;
	}

	public String getName() {
		return name;
	}

	public Trouble getTrouble() {
		return trouble;
	}
}
