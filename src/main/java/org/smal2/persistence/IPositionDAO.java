package org.smal2.persistence;

import java.util.List;

import org.smal2.domain.entity.Position;

public interface IPositionDAO {

	void create(Position entity);

	Position read(long id);

	List<Position> readAll();

	void update(Position entity);

	void delete(long id);

	Position getByLaboratoryAndPosition(String laboratory, int rowNum,
			int columnNum);

	boolean existWithLaboratoryAndPosition(String laboratory, int rowNum,
			int columnNum);
}
