package org.smal2.infrastructure.persistence.jpa;

import java.util.List;

import org.smal2.domain.entity.Computer;
import org.smal2.persistence.IComputerDAO;
import org.springframework.stereotype.Component;

@Component
public class ComputerDAOJPA extends GenericDAOJPA<Computer> implements
		IComputerDAO {

	@Override
	public Computer read(long id) {
		return read(Computer.class, id);
	}

	@Override
	public List<Computer> readAll() {
		return readAll(Computer.class);
	}

	@Override
	public void delete(long id) {
		delete(Computer.class, id);
	}

	@Override
	public Computer getByAssetCode(String assetCode) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM Computer e WHERE e.assetcode = ?");
		Object array[] = { assetCode };

		return super.getEntity(query.toString(), array);
	}

	@Override
	public boolean existWithAssetCode(String assetCode) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM Computer e WHERE e.assetcode = ?");
		Object array[] = { assetCode };

		return super.hasEntity(query.toString(), array);
	}

	@Override
	public boolean existWithPosition(long id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT c FROM Computer c INNER JOIN c.position as p WHERE p.id = ?");
		Object array[] = { id };

		return super.hasEntity(query.toString(), array);
	}
}