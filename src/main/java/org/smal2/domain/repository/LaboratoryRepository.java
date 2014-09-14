package org.smal2.domain.repository;

import java.util.List;

import org.smal2.domain.entity.Laboratory;
import org.smal2.persistence.ILaboratoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryRepository {

	@Autowired
	ILaboratoryDAO dao;

	public void insert(Laboratory laboratory) {
		dao.create(laboratory);
	}

	public Laboratory get(long id) {
		return dao.read(id);
	}

	public List<Laboratory> listAll() {
		return dao.readAll();
	}

	public void save(Laboratory entity) {
		dao.update(entity);
	}

	public void remove(long id) {
		dao.delete(id);
	}

	public Laboratory getByName(String name) {
		return dao.getByName(name);
	}

	public boolean existName(String name) {
		return dao.existName(name);
	}
}
