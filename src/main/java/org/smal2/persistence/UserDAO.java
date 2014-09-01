package org.smal2.persistence;

import java.util.List;

import org.smal2.domain.User;

public interface UserDAO {
	void create(User entity);

	User read(long id);

	List<User> readAll();

	void update(User entity);

	void delete(long id);
}
