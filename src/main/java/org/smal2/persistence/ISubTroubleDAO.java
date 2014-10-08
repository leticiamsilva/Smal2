package org.smal2.persistence;

import java.util.List;

import org.smal2.domain.entity.SubTrouble;

public interface ISubTroubleDAO {

	void create(SubTrouble entity);

	SubTrouble read(String name);

	List<SubTrouble> readAll();

	void update(SubTrouble entity);

	void delete(String name);

	boolean existWithName(String name);
}
