package org.smal2.test.mock;

import org.smal2.domain.User;
import org.smal2.persistence.UserDAO;
import org.springframework.stereotype.Component;

@Component
public class UserDAOMock extends InMemoryDAO<User, Long> implements UserDAO {

	private static long sequence = 0;

	public void create(User entity) {
		createById(entity, ++sequence);
	}

	public void update(User entity) {
		updateById(entity, entity.getId());
	}
}