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

	public User get(String registration) {
		return dao.read(registration);
	}

	public List<User> listAll() {
		return dao.readAll();
	}

	public void save(User entity) {
		dao.update(entity);
	}

	public void remove(String registration) {
		dao.delete(registration);
	}

	public boolean existWithRegistration(String registration) {
		return dao.existWithRegistration(registration);
	}

	public boolean existWithEmail(String email) {
		return dao.existWithEmail(email);
	}

	public User getBySessionId(String session_id) {
		return dao.getBySessionId(session_id);
	}

	public boolean existWithSessionId(String session_id) {
		return dao.existWithSessionId(session_id);
	}
}
