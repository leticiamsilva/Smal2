package org.smal2.infrastructure.persistence.jpa;

import java.util.List;

import org.smal2.domain.entity.SubTrouble;
import org.smal2.persistence.ISubTroubleDAO;
import org.springframework.stereotype.Component;

@Component
public class SubTroubleDAOJPA extends GenericDAOJPA<SubTrouble> implements
		ISubTroubleDAO {

	@Override
	public SubTrouble read(String name) {
		return read(SubTrouble.class, name);
	}

	@Override
	public List<SubTrouble> readAll() {
		return readAll(SubTrouble.class);
	}

	@Override
	public void delete(String name) {
		delete(SubTrouble.class, name);
	}

	@Override
	public boolean existWithName(String name) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM SubTrouble e WHERE e.name = ?");
		Object array[] = { name };

		return super.hasEntity(query.toString(), array);
	}
}