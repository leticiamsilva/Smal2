package org.smal2.persistence;

import java.util.List;

import org.smal2.domain.entity.Laboratory;

public interface ILaboratoryDAO {

	void create(Laboratory entity);

	Laboratory read(String name);

	List<Laboratory> readAll();

	void update(Laboratory entity);

	void delete(String name);

	boolean existWithName(String name);
}
