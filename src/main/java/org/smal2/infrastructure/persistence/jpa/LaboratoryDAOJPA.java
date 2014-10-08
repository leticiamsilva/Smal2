package org.smal2.infrastructure.persistence.jpa;

import java.util.List;

import org.smal2.domain.entity.Laboratory;
import org.smal2.persistence.ILaboratoryDAO;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryDAOJPA extends GenericDAOJPA<Laboratory> implements
		ILaboratoryDAO {

	@Override
	public Laboratory read(String name) {
		return read(Laboratory.class, name);
	}

	@Override
	public List<Laboratory> readAll() {
		return readAll(Laboratory.class);
	}

	@Override
	public void delete(String name) {
		delete(Laboratory.class, name);
	}

	@Override
	public boolean existWithName(String name) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM Laboratory e WHERE e.name = ?");
		Object array[] = { name };

		return super.hasEntity(query.toString(), array);
	}
}