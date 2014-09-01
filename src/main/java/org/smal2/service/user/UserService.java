package org.smal2.service.user;

import java.util.ArrayList;
import java.util.List;

import org.smal2.domain.Administrator;
import org.smal2.domain.Technician;
import org.smal2.domain.User;
import org.smal2.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

	@Autowired
	UserRepository repository;

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

			item = new ListUsersResponseItem(user.getId(),
					user.getRegistration(), user.getName(), type);
			users.add(item);
		}

		return new ListUsersResponse(users);
	}

	public void registerUser(RegisterUserRequest request) {

		// TODO [CMP] thinking about to use one class only
		switch (request.getType()) {
		case ADMINISTRATOR:

			break;

		default:
			break;
		}

		repository
				.insert(new User(request.getRegistration(), request.getName(),
						request.getBirthDate()/* , request.getType() */));
	}
}
