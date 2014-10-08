package org.smal2.persistence;

import java.util.List;

import org.smal2.domain.entity.Trouble;

public interface ITroubleDAO {

	void create(Trouble entity);

	Trouble read(String name);

	List<Trouble> readAll();

	void update(Trouble entity);

	void delete(String name);

	boolean existWithName(String name);
}
