package org.smal2.infrastructure.persistence.jpa;

import java.util.List;

import org.smal2.domain.entity.Laboratory;
import org.smal2.persistence.ILaboratoryDAO;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryDAOJPA extends GenericDAOJPA<Laboratory> implements
		ILaboratoryDAO {

	@Override
	public Laboratory read(long id) {
		return read(Laboratory.class, id);
	}

	@Override
	public List<Laboratory> readAll() {
		return readAll(Laboratory.class);
	}

	@Override
	public void delete(long id) {
		delete(Laboratory.class, id);
	}

	@Override
	public Laboratory getByName(String name) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM laboratory e WHERE e.name = ?");
		Object array[] = { name };

		return super.getEntity(query.toString(), array);
	}
}