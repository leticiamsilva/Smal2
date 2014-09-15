package org.smal2.test.testutil.mock;

import org.smal2.domain.entity.Computer;
import org.smal2.infrastructure.persistence.jpa.DAOException;
import org.smal2.persistence.IComputerDAO;
import org.springframework.stereotype.Component;

@Component
public class ComputerDAOMock extends AInMemoryDAO<Computer, Long> implements
		IComputerDAO {

	private static long sequence = 0;

	@Override
	public void create(Computer entity) {
		createById(entity, ++sequence);
	}

	@Override
	public void update(Computer entity) {
		updateById(entity, entity.getId());
	}

	@Override
	public Computer getByAssetCode(String assetCode) {
		for (Computer computer : readAll()) {
			if (computer.getAssetCode().equals(assetCode)) {

				return computer;
			}
		}

		throw new DAOException("Cannot find specified entity");
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
	public boolean existWithPosition(long id) {
		for (Computer computer : readAll()) {
			if (computer.getPosition().getId() == id) {

				return true;
			}
		}

		return false;
	}
}
