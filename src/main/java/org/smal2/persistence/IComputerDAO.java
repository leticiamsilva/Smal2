package org.smal2.persistence;

import java.util.List;

import org.smal2.domain.entity.Computer;

public interface IComputerDAO {

	void create(Computer entity);

	Computer read(long id);

	List<Computer> readAll();

	void update(Computer entity);

	void delete(long id);

	Computer getByAssetCode(String assetCode);

	boolean existWithAssetCode(String assetCode);

	boolean existWithPosition(long id);
}
