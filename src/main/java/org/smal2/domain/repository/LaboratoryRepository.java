package org.smal2.domain.repository;

import java.util.List;

import org.smal2.domain.entity.Laboratory;
import org.smal2.persistence.ILaboratoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryRepository {

	@Autowired
	private ILaboratoryDAO dao;

	public void insert(Laboratory entity) {
		dao.create(entity);
	}

	public Laboratory get(String name) {
		return dao.read(name);
	}

	public List<Laboratory> listAll() {
		return dao.readAll();
	}

	public void save(Laboratory entity) {
		dao.update(entity);
	}

	public void remove(String name) {
		dao.delete(name);
	}

	public boolean existWithName(String name) {
		return dao.existWithName(name);
	}
}
