package org.smal2.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
@DiscriminatorValue("administrator")
public class Administrator extends Technician {

	protected Administrator() {
	}

	public Administrator(String registration, String password, String name,
			String email) {
		super(registration, password, name, email);
	}
}
