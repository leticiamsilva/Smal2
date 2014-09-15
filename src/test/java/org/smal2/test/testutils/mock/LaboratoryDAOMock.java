package org.smal2.test.testutils.mock;

import org.smal2.domain.entity.Laboratory;
import org.smal2.infrastructure.persistence.jpa.DAOException;
import org.smal2.persistence.ILaboratoryDAO;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryDAOMock extends AInMemoryDAO<Laboratory, Long> implements
		ILaboratoryDAO {

	private static long sequence = 0;

	@Override
	public void create(Laboratory entity) {
		createById(entity, ++sequence);
	}

	@Override
	public void update(Laboratory entity) {
		updateById(entity, entity.getId());
	}

	@Override
	public Laboratory getByName(String name) {
		for (Laboratory laboratory : readAll()) {
			if (laboratory.getName().equals(name)) {

				return laboratory;
			}
		}

		throw new DAOException("Cannot find specified entity");
	}

	@Override
	public boolean existWithName(String name) {
		for (Laboratory laboratory : readAll()) {
			if (laboratory.getName().equals(name)) {

				return true;
			}
		}

		return false;
	}
}