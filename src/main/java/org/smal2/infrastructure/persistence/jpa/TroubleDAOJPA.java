package org.smal2.infrastructure.persistence.jpa;

import java.util.List;

import org.smal2.domain.entity.Trouble;
import org.smal2.persistence.ITroubleDAO;
import org.springframework.stereotype.Component;

@Component
public class TroubleDAOJPA extends GenericDAOJPA<Trouble> implements
		ITroubleDAO {

	@Override
	public Trouble read(String name) {
		return read(Trouble.class, name);
	}

	@Override
	public List<Trouble> readAll() {
		return readAll(Trouble.class);
	}

	@Override
	public void delete(String name) {
		delete(Trouble.class, name);
	}

	@Override
	public boolean existWithName(String name) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM Trouble e WHERE e.name = ?");
		Object array[] = { name };

		return super.hasEntity(query.toString(), array);
	}
}