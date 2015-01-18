package org.smal2.test.service;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.trouble.ListTroublesRequest;
import org.smal2.service.trouble.ListTroublesResponse;
import org.smal2.service.trouble.ListTroublesResponseItem;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListTroublesServiceTest extends ABaseTest {

	private String student_session_id;

	@Before
	public void before() {
		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.STUDENT));

		student_session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		troubleRepository.insert(new Trouble("troub01"));
		troubleRepository.insert(new Trouble("troub02"));
	}

	@Test
	public void listMustReturnAllTroubles() {
		// Act
		ListTroublesResponse response = troubleService
				.listTroubles(new ListTroublesRequest(student_session_id));

		// Assert
		Assert.assertEquals(2, response.size());

		ListTroublesResponseItem r1, r2;

		if (response.get(0).getName().equals("troub01")) {
			r1 = response.get(0);
			r2 = response.get(1);
		} else {
			r1 = response.get(1);
			r2 = response.get(0);
		}

		Assert.assertEquals("troub01", r1.getName());
		Assert.assertEquals("troub02", r2.getName());
	}
}
