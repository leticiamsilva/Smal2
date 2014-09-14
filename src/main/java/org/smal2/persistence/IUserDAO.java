package org.smal2.persistence;

import java.util.List;

import org.smal2.domain.entity.User;

public interface IUserDAO {
	void create(User entity);

	User read(long id);

	List<User> readAll();

	void update(User entity);

	void delete(long id);

	User getByRegistration(String registration);

	boolean existRegistration(String registration);
}
