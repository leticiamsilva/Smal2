package org.smal2.domain.entity;

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

	private Computer() {
	}

	public Computer(String assetCode, Position position) {
		this();

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
}
