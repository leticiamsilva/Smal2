package org.smal2.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
@DiscriminatorValue("administrator")
public class Administrator extends Technician {

	public Administrator(String name, String registration, String password) {
		super(name, registration, password);
	}
}
