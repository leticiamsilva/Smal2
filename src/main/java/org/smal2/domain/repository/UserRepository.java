package org.smal2.domain.repository;

import java.util.List;

import org.smal2.domain.entity.User;
import org.smal2.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

	@Autowired
	UserDAO userDAO;

	public void insert(User user) {
		userDAO.create(user);
	}

	public User get(long id) {
		return userDAO.read(id);
	}

	public List<User> listAll() {
		return userDAO.readAll();
	}

	public void save(User entity) {
		userDAO.update(entity);
	}

	public void remove(long id) {
		userDAO.delete(id);
	}

	public User getByRegistration(String registration) {
		return userDAO.getByRegistration(registration);
	}
}
