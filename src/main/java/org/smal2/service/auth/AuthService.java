package org.smal2.service.auth;

import org.smal2.domain.repository.AuthRepository;
import org.smal2.domain.repository.SubTroubleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

	@Autowired
	private SubTroubleRepository subTroubleRepository;

	@Autowired
	AuthRepository authRepository;

	public String loginUser(LoginUserRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (request.getRegistration() == null
				|| request.getRegistration().equals("")) {
			throw new IllegalArgumentException("Undefined registration.");
		}

		if (request.getPassword() == null || request.getPassword().equals("")) {
			throw new IllegalArgumentException("Undefined password.");
		}

		return "User login successfully.";
	}
}
