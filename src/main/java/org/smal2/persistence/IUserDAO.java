package org.smal2.persistence;

import java.util.List;

import org.smal2.domain.entity.User;

public interface IUserDAO {

	void create(User entity);

	User read(String registration);

	List<User> readAll();

	void update(User entity);

	void delete(String registration);

	boolean existWithRegistration(String registration);

	boolean existWithEmail(String email);

	User getBySessionId(String session_id);

	boolean existWithSessionId(String session_id);

	List<User> readAllPrivilegedUsers();
}
