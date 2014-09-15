package org.smal2.test.testutil.mock;

import org.smal2.domain.entity.Position;
import org.smal2.infrastructure.persistence.jpa.DAOException;
import org.smal2.persistence.IPositionDAO;
import org.springframework.stereotype.Component;

@Component
public class PositionDAOMock extends AInMemoryDAO<Position, Long> implements
		IPositionDAO {

	private static long sequence = 0;

	@Override
	public void create(Position entity) {
		createById(entity, ++sequence);
	}

	@Override
	public void update(Position entity) {
		updateById(entity, entity.getId());
	}

	@Override
	public Position getByLaboratoryAndPosition(String laboratory, int rowNum,
			int columnNum) {
		for (Position position : readAll()) {
			if (position.getLaboratory().getName().equals(laboratory)
					&& position.getRowNum() == rowNum
					&& position.getColumnNum() == columnNum) {

				return position;
			}
		}

		throw new DAOException("Cannot find specified entity");
	}

	@Override
	public boolean existWithLaboratoryAndPosition(String laboratory,
			int rowNum, int columnNum) {
		for (Position position : readAll()) {
			if (position.getLaboratory().getName().equals(laboratory)
					&& position.getRowNum() == rowNum
					&& position.getColumnNum() == columnNum) {

				return true;
			}
		}

		return false;
	}
}