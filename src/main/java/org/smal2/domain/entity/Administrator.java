package org.smal2.domain.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
@DiscriminatorValue("administrator")
public class Administrator extends Technician {

	protected Administrator() {
	}

	public Administrator(String registration, String name, Date birthDate,
			String password) {
		super(registration, name, birthDate, password);
	}
}
