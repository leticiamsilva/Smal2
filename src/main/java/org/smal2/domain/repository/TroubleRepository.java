package org.smal2.domain.repository;

import java.util.List;

import org.smal2.domain.entity.Trouble;
import org.smal2.persistence.ITroubleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TroubleRepository {

	@Autowired
	private ITroubleDAO dao;

	public void insert(Trouble entity) {
		dao.create(entity);
	}

	public Trouble get(String name) {
		return dao.read(name);
	}

	public List<Trouble> listAll() {
		return dao.readAll();
	}

	public void save(Trouble entity) {
		dao.update(entity);
	}

	public void remove(String name) {
		dao.delete(name);
	}

	public boolean existWithName(String name) {
		return dao.existWithName(name);
	}
}
