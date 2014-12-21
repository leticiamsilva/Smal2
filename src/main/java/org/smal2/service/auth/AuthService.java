package org.smal2.service.auth;

import java.util.Date;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.User;
import org.smal2.domain.repository.AuthRepository;
import org.smal2.domain.repository.SubTroubleRepository;
import org.smal2.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

	@Autowired
	private SubTroubleRepository subTroubleRepository;

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private UserRepository userRepository;

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

		// local user exists?
		if (!userRepository.existWithRegistration(request.getRegistration())) {

			// remote login
			RemoteLoginResponse response = authRepository
					.getRemoteLoginResponse(request.getRegistration(),
							request.getPassword());

			if (response.getError() != null || response.getToken() == null
					|| response.getToken().trim().isEmpty()) {
				String error = "Cannot authenticate user in remote auth service.";
				error += "\nError: ";
				error += ((response.getError() == null) ? ("Null") : (response
						.getError()));
				throw new IllegalArgumentException(error);
			}

			// TODO [CMP] local md5 password registration
			// local registration
			// String md5Pass = MD5Generator.generate(request.getPassword());
			User unprivilegedUser = new User(request.getRegistration(),
					"Auto-registred unprivileged user", new Date());
			userRepository.insert(unprivilegedUser);
		}

		// local login
		User user = userRepository.getByRegistration(request.getRegistration());

		String md5Pass = MD5Generator.generate(request.getPassword());

		if (md5Pass == null) {
			throw new IllegalArgumentException(
					"Internal error: cannot generate md5 hash for this password.");
		}

		// if (!user.getPassword().equals(md5Pass)) {
		// throw new IllegalArgumentException("Invalid username or password");
		// }

		return "User login successfully.";
	}
}
