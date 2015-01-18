package org.smal2.service.user;

import java.util.ArrayList;
import java.util.List;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.User;
import org.smal2.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public String registerPrivilegedUser(RegisterPrivilegedUserRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (request.getRegistration() == null
				|| request.getRegistration().equals("")) {
			throw new IllegalArgumentException("Undefined user registration.");
		}

		if (request.getPassword() == null || request.getPassword().equals("")) {
			throw new IllegalArgumentException("Undefined user password.");
		}

		if (request.getEmail() == null || request.getEmail().equals("")) {
			throw new IllegalArgumentException("Undefined user email.");
		}

		if (request.getName() == null || request.getName().equals("")) {
			throw new IllegalArgumentException("Undefined user name.");
		}

		if (userRepository.existWithRegistration(request.getRegistration())) {
			throw new IllegalArgumentException(
					"User registration already exist.");
		}

		if (userRepository.existWithEmail(request.getEmail())) {
			throw new IllegalArgumentException("User email already exist.");
		}

		String md5Pass = MD5Generator.generate(request.getPassword());

		org.smal2.domain.entity.UserType type;

		switch (request.getType()) {
		case ADMINISTRATOR:
			type = org.smal2.domain.entity.UserType.ADMINISTRATOR;
			break;

		case TECHNICIAN:
			type = org.smal2.domain.entity.UserType.TECHNICIAN;
			break;

		default:
			throw new IllegalArgumentException(
					"User type must be administrator or technician.");
		}

		User user = new User(request.getRegistration(), md5Pass,
				request.getName(), type);
		user.setEmail(request.getEmail());
		userRepository.insert(user);

		return "User registred successfully.";
	}

	public ListUsersResponse listUsers(ListUsersRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (request.getSession_id() == null
				|| request.getSession_id().equals("")) {
			throw new IllegalArgumentException("Undefined session identifier.");
		}

		if (!userRepository.existWithSessionId(request.getSession_id())) {
			throw new IllegalArgumentException("Invalid session identifier");
		}

		User user = userRepository.getBySessionId(request.getSession_id());

		if (user.getType() != org.smal2.domain.entity.UserType.ADMINISTRATOR
				&& user.getType() != org.smal2.domain.entity.UserType.TECHNICIAN) {
			throw new IllegalArgumentException(
					"User must be at least a Student to perform this operation.");
		}

		List<ListUsersResponseItem> users = new ArrayList<ListUsersResponseItem>();
		ListUsersResponseItem item;

		UserType type;

		for (User user_tmp : userRepository.listAll()) {

			switch (user_tmp.getType()) {
			case ADMINISTRATOR:
				type = UserType.ADMINISTRATOR;
				break;
			case TECHNICIAN:
				type = UserType.TECHNICIAN;
				break;
			case STUDENT:
				type = UserType.STUDENT;
				break;

			default:
				throw new IllegalStateException("Invalid user type.");
			}

			item = new ListUsersResponseItem(user_tmp.getRegistration(),
					user_tmp.getName(), type);
			users.add(item);
		}

		return new ListUsersResponse(users);
	}
}
