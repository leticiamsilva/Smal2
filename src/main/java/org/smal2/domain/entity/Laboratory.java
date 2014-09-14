package org.smal2.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "laboratory")
public class Laboratory {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "laboratory", fetch = FetchType.LAZY)
	private List<Position> positions;

	private Laboratory() {
		positions = new ArrayList<Position>();
	}

	public Laboratory(String name) {
		this();

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

	public List<Position> getPositions() {
		return positions;
	}

	// from
	// http://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Getters_and_Setters
	public void addPosition(Position position) {
		if (!this.positions.contains(position)) {
			this.positions.add(position);
		}

		if (position.getLaboratory() != this) {
			position.setLaboratory(this);
		}
	}
}
