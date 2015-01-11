package org.smal2.service.user;

import java.util.ArrayList;
import java.util.List;

import org.smal2.domain.entity.Administrator;
import org.smal2.domain.entity.Technician;
import org.smal2.domain.entity.User;
import org.smal2.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

	@Autowired
	private UserRepository repository;

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

		if (repository.existWithRegistration(request.getRegistration())) {
			throw new IllegalArgumentException(
					"User registration already exist.");
		}

		if (repository.existWithEmail(request.getEmail())) {
			throw new IllegalArgumentException("User email already exist.");
		}

		switch (request.getType()) {
		case ADMINISTRATOR:
			repository.insert(new Administrator(request.getRegistration(),
					request.getPassword(), request.getName(), request
							.getEmail()));
			break;

		case TECHNICHAN:
			repository.insert(new Technician(request.getRegistration(), request
					.getPassword(), request.getName(), request.getEmail()));
			break;

		default:
			throw new IllegalArgumentException(
					"User type must be administrator or technichan.");
		}

		return "User registred successfully.";
	}

	public ListUsersResponse listUsers() {

		List<ListUsersResponseItem> users = new ArrayList<ListUsersResponseItem>();
		ListUsersResponseItem item;

		for (User user : repository.listAll()) {
			item = new ListUsersResponseItem(user.getRegistration(),
					user.getName(), user.getType());
			users.add(item);
		}

		return new ListUsersResponse(users);
	}
}
