package org.smal2.domain.repository;

import org.smal2.persistence.IAuthDAO;
import org.smal2.service.auth.RemoteLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthRepository {

	@Autowired
	private IAuthDAO authDao;

	public RemoteLoginResponse getRemoteLoginResponse(String username,
			String password) {
		return authDao.getRemoteLoginResponse(username, password);
	}
}
