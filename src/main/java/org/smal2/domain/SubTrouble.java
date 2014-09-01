package org.smal2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subtrouble")
public class SubTrouble {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(optional = false)
	private Trouble trouble;

	@SuppressWarnings("unused")
	private SubTrouble() {
	}

	public SubTrouble(String name, Trouble trouble) {
		super();

		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Name can not be empty.");
		}

		if (trouble == null) {
			throw new IllegalArgumentException("Trouble can not be null.");
		}

		this.name = name;
		this.trouble = trouble;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Trouble getTrouble() {
		return trouble;
	}
}
