package org.smal2.test.testutil.mock;

import org.smal2.domain.entity.User;
import org.smal2.infrastructure.persistence.jpa.DAOException;
import org.smal2.persistence.IUserDAO;
import org.springframework.stereotype.Component;

@Component
public class UserDAOMock extends AInMemoryDAO<User, Long> implements IUserDAO {

	private static long sequence = 0;

	@Override
	public void create(User entity) {
		createByKey(entity, ++sequence);
	}

	@Override
	public void update(User entity) {
		updateByKey(entity, entity.getId());
	}

	@Override
	public User getByRegistration(String registration) {
		for (User user : readAll()) {
			if (user.getRegistration().equals(registration)) {

				return user;
			}
		}

		throw new DAOException("Cannot find specified entity");
	}

	@Override
	public boolean existWithRegistration(String registration) {
		for (User user : readAll()) {
			if (user.getRegistration().equals(registration)) {

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean existWithEmail(String email) {
		for (User user : readAll()) {
			if (user.getEmail() != null && user.getEmail().equals(email)) {

				return true;
			}
		}

		return false;
	}

	@Override
	public User getBySessionId(String session_id) {
		for (User user : readAll()) {
			if (user.getSession_id() != null
					&& user.getSession_id().equals(session_id)) {

				return user;
			}
		}

		throw new DAOException("Cannot find specified entity");
	}

	@Override
	public boolean existWithSessionId(String session_id) {
		for (User user : readAll()) {
			if (user.getSession_id() != null
					&& user.getSession_id().equals(session_id)) {

				return true;
			}
		}

		return false;
	}
}