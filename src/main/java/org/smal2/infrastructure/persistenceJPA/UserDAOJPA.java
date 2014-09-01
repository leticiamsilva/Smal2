package org.smal2.infrastructure.persistenceJPA;

import java.util.List;

import org.smal2.domain.User;
import org.smal2.persistence.UserDAO;
import org.springframework.stereotype.Component;

@Component
public class UserDAOJPA extends GenericDAOJPA<User> implements UserDAO {

	@Override
	public User read(long id) {
		return read(User.class, id);
	}

	@Override
	public List<User> readAll() {
		return readAll(User.class);
	}

	@Override
	public void delete(long id) {
		delete(User.class, id);
	}
}