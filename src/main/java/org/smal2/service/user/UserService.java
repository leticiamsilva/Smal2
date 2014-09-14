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

	public ListUsersResponse listUser() {

		List<ListUsersResponseItem> users = new ArrayList<ListUsersResponseItem>();
		ListUsersResponseItem item;

		int type;

		for (User user : repository.listAll()) {
			if (user.getClass() == Administrator.class) {
				type = 1;
			} else if (user.getClass() == Technician.class) {
				type = 2;
			} else {
				type = 0;
			}

			item = new ListUsersResponseItem(user.getRegistration(),
					user.getName(), type);
			users.add(item);
		}

		return new ListUsersResponse(users);
	}

	public void registerPrivilegedUser(RegisterPrivilegedUserRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (request.getRegistration() == null || request.getRegistration() == "") {
			throw new IllegalArgumentException("Undefined user registration.");
		}
		
		if(repository.existRegistration(request.getRegistration()))
		{
			throw new IllegalArgumentException("User registration already exist.");
		}

		// TODO [CMP] thinking about to use one entity class only
		switch (request.getType()) {
		case ADMINISTRATOR:
			repository.insert(new Administrator(request.getRegistration(),
					request.getName(), request.getBirthDate(), request
							.getPassword()));
			break;

		case TECHNICHAN:
			repository.insert(new Technician(request.getRegistration(), request
					.getName(), request.getBirthDate(), request.getPassword()));
			break;

		default:
			throw new IllegalArgumentException(
					"User type must be administrator or technichan.");
		}
	}
}
