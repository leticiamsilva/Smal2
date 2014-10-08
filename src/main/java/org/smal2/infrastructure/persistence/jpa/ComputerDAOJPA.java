package org.smal2.infrastructure.persistence.jpa;

import java.util.List;

import org.smal2.domain.entity.Computer;
import org.smal2.persistence.IComputerDAO;
import org.springframework.stereotype.Component;

@Component
public class ComputerDAOJPA extends GenericDAOJPA<Computer> implements
		IComputerDAO {

	@Override
	public Computer read(String assetCode) {
		return read(Computer.class, assetCode);
	}

	@Override
	public List<Computer> readAll() {
		return readAll(Computer.class);
	}

	@Override
	public void delete(String assetCode) {
		delete(Computer.class, assetCode);
	}

	@Override
	public boolean existWithAssetCode(String assetCode) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM Computer e WHERE e.assetCode = ?");
		Object array[] = { assetCode };

		return super.hasEntity(query.toString(), array);
	}

	@Override
	public boolean existWithPosition(Long id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT c FROM Computer c INNER JOIN c.position as p WHERE p.id = ?");
		Object array[] = { id };

		return super.hasEntity(query.toString(), array);
	}
}