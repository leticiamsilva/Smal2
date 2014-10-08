package org.smal2.test.testutil.mock;

import org.smal2.domain.entity.Trouble;
import org.smal2.persistence.ITroubleDAO;
import org.springframework.stereotype.Component;

@Component
public class TroubleDAOMock extends AInMemoryDAO<Trouble, String> implements
		ITroubleDAO {

	@Override
	public void create(Trouble entity) {
		createByKey(entity, entity.getName());
	}

	@Override
	public void update(Trouble entity) {
		updateByKey(entity, entity.getName());
	}

	@Override
	public boolean existWithName(String name) {
		for (Trouble trouble : readAll()) {
			if (trouble.getName().equals(name)) {

				return true;
			}
		}

		return false;
	}
}