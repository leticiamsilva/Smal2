package org.smal2.infrastructure;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.User;
import org.smal2.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {

		if (userRepository.listAll().size() == 0) {

			User user = new User("admin", MD5Generator.generate("admin"),
					"Admin", org.smal2.domain.entity.UserType.ADMINISTRATOR);
			userRepository.insert(user);
		}
	}
}
