package org.smal2.infrastructure;

import org.smal2.service.user.RegisterPrivilegedUserRequest;
import org.smal2.service.user.UserService;
import org.smal2.service.user.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private UserService userService;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {

		if (userService.listUsers().size() == 0) {
			userService
					.registerPrivilegedUser(new RegisterPrivilegedUserRequest(
							"admin", "admin", "Admin", "admin@smal2",
							UserType.ADMINISTRATOR));
		}
	}
}
