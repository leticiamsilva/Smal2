package org.smal2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String assetCode;
	
	@OneToOne(optional = false)
	private Position position;

	@SuppressWarnings("unused")
	private Computer() {
	}

	public Computer(String assetCode, Position position) {
		super();

		if (assetCode == null || assetCode.equals("")) {
			throw new IllegalArgumentException("Asset code can not be empty.");
		}

		if (position == null) {
			throw new IllegalArgumentException("Position can not be null.");
		}

		this.assetCode = assetCode;
		this.position = position;
	}

	public long getId() {
		return id;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public Position getPosition() {
		return position;
	}

	// from
	// http://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Getters_and_Setters
	public void setPosition(Position position) {
		if (this.position != position) {
			this.position = position;
		}

		if (position.getComputer() != this) {
			position.setMaquina(this);
		}
	}
}
