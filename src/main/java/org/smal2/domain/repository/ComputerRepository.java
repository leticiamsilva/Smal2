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

	public Computer get(String assetCode) {
		return dao.read(assetCode);
	}

	public List<Computer> listAll() {
		return dao.readAll();
	}

	public void save(Computer entity) {
		dao.update(entity);
	}

	public void remove(String assetCode) {
		dao.delete(assetCode);
	}

	public boolean existWithAssetCode(String assetCode) {
		return dao.existWithAssetCode(assetCode);
	}

	public boolean existWithPosition(long id) {
		return dao.existWithPosition(id);
	}
}
