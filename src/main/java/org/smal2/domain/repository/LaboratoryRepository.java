package org.smal2.domain.repository;

import java.util.List;

import org.smal2.domain.entity.Laboratory;
import org.smal2.persistence.ILaboratoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryRepository {

	@Autowired
	ILaboratoryDAO laboratoryDAO;

	public void insert(Laboratory laboratory) {
		laboratoryDAO.create(laboratory);
	}

	public Laboratory get(long id) {
		return laboratoryDAO.read(id);
	}

	public List<Laboratory> listAll() {
		return laboratoryDAO.readAll();
	}

	public void save(Laboratory entity) {
		laboratoryDAO.update(entity);
	}

	public void remove(long id) {
		laboratoryDAO.delete(id);
	}

	public Laboratory getByName(String name) {
		return laboratoryDAO.getByName(name);
	}

	public boolean existName(String name) {
		return laboratoryDAO.existName(name);
	}
}
