package org.smal2.persistence;

import java.util.List;

import org.smal2.domain.entity.User;

public interface IUserDAO {
	void create(User entity);

	User read(Long id);

	List<User> readAll();

	void update(User entity);

	void delete(Long id);

	User getByRegistration(String registration);

	boolean existWithRegistration(String registration);

	boolean existWithEmail(String email);

	User getBySession(String session);

	boolean existWithSession(String session);
}
