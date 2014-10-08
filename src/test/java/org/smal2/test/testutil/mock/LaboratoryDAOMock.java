package org.smal2.test.testutil.mock;

import org.smal2.domain.entity.Laboratory;
import org.smal2.persistence.ILaboratoryDAO;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryDAOMock extends AInMemoryDAO<Laboratory, String>
		implements ILaboratoryDAO {

	@Override
	public void create(Laboratory entity) {
		createByKey(entity, entity.getName());
	}

	@Override
	public void update(Laboratory entity) {
		updateByKey(entity, entity.getName());
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