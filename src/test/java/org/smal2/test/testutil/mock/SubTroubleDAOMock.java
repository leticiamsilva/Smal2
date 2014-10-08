package org.smal2.test.testutil.mock;

import org.smal2.domain.entity.SubTrouble;
import org.smal2.persistence.ISubTroubleDAO;
import org.springframework.stereotype.Component;

@Component
public class SubTroubleDAOMock extends AInMemoryDAO<SubTrouble, String>
		implements ISubTroubleDAO {

	@Override
	public void create(SubTrouble entity) {
		createByKey(entity, entity.getName());
	}

	@Override
	public void update(SubTrouble entity) {
		updateByKey(entity, entity.getName());
	}

	@Override
	public boolean existWithName(String name) {
		for (SubTrouble subTrouble : readAll()) {
			if (subTrouble.getName().equals(name)) {

				return true;
			}
		}

		return false;
	}
}