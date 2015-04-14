package org.smal2.test.service;

import java.util.Collections;
import java.util.Comparator;

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

	private String technician_session_id;

	@Before
	public void before() {
		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.TECHNICIAN));

		technician_session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		userRepository.insert(new User("0001", MD5Generator
				.generate("password1"), "Joe", UserType.STUDENT));
		userRepository.insert(new User("0002", MD5Generator
				.generate("password2"), "Jack", UserType.ADMINISTRATOR));
	}

	@Test
	public void listMustReturnAllUsers() {
		// Act
		ListUsersResponse response = userService
				.listUsers(new ListUsersRequest(technician_session_id));

		// Assert
		Assert.assertEquals(3, response.size());

		Collections.sort(response,new Comparator<ListUsersResponseItem>() {
			public int compare(ListUsersResponseItem o1,
					ListUsersResponseItem o2) {
				return o1.getRegistration().compareTo(o2.getRegistration());
			}
		});

		ListUsersResponseItem r0, r1, r2;
		r0 = response.get(0);
		r1 = response.get(1);
		r2 = response.get(2);

		Assert.assertEquals("0000", r0.getRegistration());
		Assert.assertEquals("Jhon", r0.getName());
		Assert.assertEquals(org.smal2.service.user.UserType.TECHNICIAN,
				r0.getType());

		Assert.assertEquals("0001", r1.getRegistration());
		Assert.assertEquals("Joe", r1.getName());
		Assert.assertEquals(org.smal2.service.user.UserType.STUDENT,
				r1.getType());

		Assert.assertEquals("0002", r2.getRegistration());
		Assert.assertEquals("Jack", r2.getName());
		Assert.assertEquals(org.smal2.service.user.UserType.ADMINISTRATOR,
				r2.getType());
	}

	@Test
	public void listPrivilegedUsersMustReturnOnlyPrivilegedUsers() {
		// Act
		ListUsersResponse response = userService
				.listPrivilegedUsers(new ListUsersRequest(technician_session_id));

		// Assert
		Assert.assertEquals(2, response.size());

		Collections.sort(response,new Comparator<ListUsersResponseItem>() {
			public int compare(ListUsersResponseItem o1,
					ListUsersResponseItem o2) {
				return o1.getRegistration().compareTo(o2.getRegistration());
			}
		});

		ListUsersResponseItem r1, r2;
		r1 = response.get(0);
		r2 = response.get(1);

		Assert.assertEquals("0000", r1.getRegistration());
		Assert.assertEquals("Jhon", r1.getName());
		Assert.assertEquals(org.smal2.service.user.UserType.TECHNICIAN,
				r1.getType());

		Assert.assertEquals("0002", r2.getRegistration());
		Assert.assertEquals("Jack", r2.getName());
		Assert.assertEquals(org.smal2.service.user.UserType.ADMINISTRATOR,
				r2.getType());
	}
}
