package org.smal2.test.service;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.User;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.laboratory.ListLaboratoriesRequest;
import org.smal2.service.laboratory.ListLaboratoriesResponse;
import org.smal2.service.laboratory.ListLaboratoriesResponseItem;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListLaboratoriesServiceTest extends ABaseTest {

	private String session_id;

	@Before
	public void before() {
		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.STUDENT));

		// Arrange
		session_id = authService
				.loginUser(new LoginUserRequest("0000", "pass"))
				.getSession_id();

		laboratoryRepository.insert(new Laboratory("lab01"));
		laboratoryRepository.insert(new Laboratory("lab02"));
	}

	@Test
	public void listMustReturnAllLaboratories() {
		// Act
		ListLaboratoriesResponse response = laboratoryService
				.listLaboratories(new ListLaboratoriesRequest(session_id));

		// Assert
		Assert.assertEquals(2, response.size());

		ListLaboratoriesResponseItem r1, r2;

		if (response.get(0).getName().equals("lab01")) {
			r1 = response.get(0);
			r2 = response.get(1);
		} else {
			r1 = response.get(1);
			r2 = response.get(0);
		}

		Assert.assertEquals("lab01", r1.getName());
		Assert.assertEquals("lab02", r2.getName());
	}
}
