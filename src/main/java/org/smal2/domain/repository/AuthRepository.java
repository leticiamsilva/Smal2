package org.smal2.domain.repository;

import org.smal2.persistence.IAuthDAO;
import org.smal2.persistence.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthRepository {

	@Autowired
	private IUserDAO userDao;

	@Autowired
	private IAuthDAO authDao;

	public boolean existWithRegistration(String registration) {
		return userDao.existWithRegistration(registration);
	}

	public String getToken(String username, String password) {
		return authDao.getRemoteLoginMessage(username, password);
	}
}
