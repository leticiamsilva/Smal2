package org.smal2.domain.repository;

import java.util.List;

import org.smal2.domain.entity.Computer;
import org.smal2.persistence.IComputerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComputerRepository {

	@Autowired
	IComputerDAO dao;

	public void insert(Computer entity) {
		dao.create(entity);
	}

	public Computer get(long id) {
		return dao.read(id);
	}

	public List<Computer> listAll() {
		return dao.readAll();
	}

	public void save(Computer entity) {
		dao.update(entity);
	}

	public void remove(long id) {
		dao.delete(id);
	}

	public Computer getByAssetCode(String assetCode) {
		return dao.getByAssetCode(assetCode);
	}

	public boolean existWithAssetCode(String assetCode) {
		return dao.existWithAssetCode(assetCode);
	}

	public boolean existWithPosition(long id) {
		return dao.existWithPosition(id);
	}
}
