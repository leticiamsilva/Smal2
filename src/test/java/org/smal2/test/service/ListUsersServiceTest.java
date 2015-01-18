package org.smal2.test.service;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.User;
import org.smal2.domain.entity.UserType;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.user.ListUsersRequest;
import org.smal2.service.user.ListUsersResponse;
import org.smal2.service.user.ListUsersResponseItem;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListUsersServiceTest extends ABaseTest {

	private String session_id;

	@Before
	public void before() {
		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.TECHNICIAN));

		session_id = authService
				.loginUser(new LoginUserRequest("0000", "pass"))
				.getSession_id();

		userRepository.insert(new User("0001", MD5Generator
				.generate("password1"), "Jhon", UserType.STUDENT));
		userRepository.insert(new User("0002", MD5Generator
				.generate("password2"), "Jack", UserType.ADMINISTRATOR));
	}

	@Test
	public void listMustReturnAllUsers() {
		// Act
		ListUsersResponse response = userService
				.listUsers(new ListUsersRequest(session_id));

		// Assert
		Assert.assertEquals(3, response.size());

		ListUsersResponseItem r1, r2;

		if (response.get(0).getRegistration().equals("0001")) {
			r1 = response.get(0);
			r2 = response.get(1);
		} else {
			r1 = response.get(1);
			r2 = response.get(0);
		}

		Assert.assertEquals("0001", r1.getRegistration());
		Assert.assertEquals("Jhon", r1.getName());
		// TODO [CMP] to test
		// Assert.assertEquals(UserType.STUDENT, r1.getType());

		Assert.assertEquals("0002", r2.getRegistration());
		Assert.assertEquals("Jack", r2.getName());
		// TODO [CMP] to test
		// Assert.assertEquals(UserType.ADMINISTRATOR, r2.getType());
	}
}
