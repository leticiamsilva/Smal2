package org.smal2.domain.repository;

import java.util.List;

import org.smal2.domain.entity.User;
import org.smal2.persistence.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

	@Autowired
	private IUserDAO dao;

	public void insert(User entity) {
		dao.create(entity);
	}

	public User get(long id) {
		return dao.read(id);
	}

	public List<User> listAll() {
		return dao.readAll();
	}

	public void save(User entity) {
		dao.update(entity);
	}

	public void remove(long id) {
		dao.delete(id);
	}

	public User getByRegistration(String registration) {
		return dao.getByRegistration(registration);
	}

	public boolean existWithRegistration(String registration) {
		return dao.existWithRegistration(registration);
	}
}
