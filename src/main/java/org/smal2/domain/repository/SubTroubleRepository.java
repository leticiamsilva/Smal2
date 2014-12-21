package org.smal2.domain.repository;

import java.util.List;

import org.smal2.domain.entity.SubTrouble;
import org.smal2.persistence.ISubTroubleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubTroubleRepository {

	@Autowired
	private ISubTroubleDAO dao;

	public void insert(SubTrouble entity) {
		dao.create(entity);
	}

	public SubTrouble get(String name) {
		return dao.read(name);
	}

	public List<SubTrouble> listAll() {
		return dao.readAll();
	}

	public void save(SubTrouble entity) {
		dao.update(entity);
	}

	public void remove(String name) {
		dao.delete(name);
	}

	public boolean existWithName(String name) {
		return dao.existWithName(name);
	}
}
