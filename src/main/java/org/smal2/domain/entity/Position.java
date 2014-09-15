package org.smal2.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	@ManyToOne(optional = false)
	private Laboratory laboratory;

	private Position() {
	}

	public Position(int rowNum, int columnNum, Laboratory laboratory) {
		this();

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
		this.laboratory = laboratory;
	}

	public long getId() {
		return id;
	}

	public Laboratory getLaboratory() {
		return laboratory;
	}

	public int getRowNum() {
		return rowNum;
	}

	public int getColumnNum() {
		return columnNum;
	}
}
