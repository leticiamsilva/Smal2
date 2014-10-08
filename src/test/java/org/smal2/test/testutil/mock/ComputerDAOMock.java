package org.smal2.test.testutil.mock;

import org.smal2.domain.entity.Computer;
import org.smal2.persistence.IComputerDAO;
import org.springframework.stereotype.Component;

@Component
public class ComputerDAOMock extends AInMemoryDAO<Computer, String> implements
		IComputerDAO {

	@Override
	public void create(Computer entity) {
		createByKey(entity, entity.getAssetCode());
	}

	@Override
	public void update(Computer entity) {
		updateByKey(entity, entity.getAssetCode());
	}

	@Override
	public boolean existWithAssetCode(String assetCode) {
		for (Computer computer : readAll()) {
			if (computer.getAssetCode().equals(assetCode)) {

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean existWithPosition(Long id) {
		for (Computer computer : readAll()) {
			if (computer.getPosition().getId() == id) {

				return true;
			}
		}

		return false;
	}
}
