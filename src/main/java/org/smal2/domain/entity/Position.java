package org.smal2.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class Position {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private int rowNum;

	@Column(nullable = false)
	private int columnNum;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "laboratory_fk")
	private Laboratory laboratory;

	@OneToOne(mappedBy = "position", optional = true)
	private Computer computer;

	@SuppressWarnings("unused")
	private Position() {
	}

	public Position(int rowNum, int columnNum, Laboratory laboratory) {
		super();

		if (rowNum < 0) {
			throw new IllegalArgumentException("RowNum invalid.");
		}

		if (columnNum < 0) {
			throw new IllegalArgumentException("ColumnNum invalid.");
		}

		if (laboratory == null) {
			throw new IllegalArgumentException("Laboratory can not be null.");
		}

		this.rowNum = rowNum;
		this.columnNum = columnNum;
		setLaboratory(laboratory);
	}

	public Laboratory getLaboratory() {
		return laboratory;
	}

	// from
	// http://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Getters_and_Setters
	public void setLaboratory(Laboratory laboratory) {
		if (this.laboratory != laboratory) {
			this.laboratory = laboratory;
		}

		if (!laboratory.getPositions().contains(this)) {
			laboratory.addPosition(this);
		}
	}

	public int getRowNum() {
		return rowNum;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public Computer getComputer() {
		return computer;
	}

	// from
	// http://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Getters_and_Setters
	public void setMaquina(Computer computer) {
		if (this.computer != computer) {
			this.computer = computer;
		}

		if (computer.getPosition() != this) {
			computer.setPosition(this);
		}
	}
}
