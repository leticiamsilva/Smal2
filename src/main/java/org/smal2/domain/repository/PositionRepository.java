package org.smal2.domain.repository;

import java.util.List;

import org.smal2.domain.entity.Position;
import org.smal2.persistence.IPositionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionRepository {

	@Autowired
	private IPositionDAO dao;

	public void insert(Position entity) {
		dao.create(entity);
	}

	public Position get(long id) {
		return dao.read(id);
	}

	public List<Position> listAll() {
		return dao.readAll();
	}

	public void save(Position entity) {
		dao.update(entity);
	}

	public void remove(long id) {
		dao.delete(id);
	}

	public Position getByLaboratoryAndPosition(String laboratory, int rowNum,
			int columnNum) {
		return dao.getByLaboratoryAndPosition(laboratory, rowNum, columnNum);
	}

	public boolean existWithLaboratoryAndPosition(String laboratory,
			int rowNum, int columnNum) {
		return dao
				.existWithLaboratoryAndPosition(laboratory, rowNum, columnNum);
	}
}
