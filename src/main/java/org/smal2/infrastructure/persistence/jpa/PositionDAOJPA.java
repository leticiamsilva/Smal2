package org.smal2.infrastructure.persistence.jpa;

import java.util.List;

import org.smal2.domain.entity.Position;
import org.smal2.persistence.IPositionDAO;
import org.springframework.stereotype.Component;

@Component
public class PositionDAOJPA extends GenericDAOJPA<Position> implements
		IPositionDAO {

	@Override
	public Position read(long id) {
		return read(Position.class, id);
	}

	@Override
	public List<Position> readAll() {
		return readAll(Position.class);
	}

	@Override
	public void delete(long id) {
		delete(Position.class, id);
	}

	@Override
	public Position getByLaboratoryAndPosition(String laboratory, int rowNum,
			int columnNum) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM Position p INNER JOIN p.laboratory as l WHERE l.name = ? AND p.rownum = ? AND p.columnnum = ?");
		Object array[] = { laboratory, rowNum, columnNum };

		return super.getEntity(query.toString(), array);
	}

	@Override
	public boolean existWithLaboratoryAndPosition(String laboratory,
			int rowNum, int columnNum) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM Position p INNER JOIN p.laboratory as l WHERE l.name = ? AND p.rownum = ? AND p.columnnum = ?");
		Object array[] = { laboratory, rowNum, columnNum };

		return super.hasEntity(query.toString(), array);
	}
}