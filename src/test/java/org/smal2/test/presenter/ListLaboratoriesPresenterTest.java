package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.ListLaboratoriesPresenter;
import org.smal2.presentation.view.IListLaboratoriesView;
import org.smal2.presentation.view.basic.ListLaboratoriesViewMock;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.laboratory.ListLaboratoriesRequest;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListLaboratoriesPresenterTest extends ABaseTest {

	private String student_session_id;

	@Before
	public void before() {

		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.STUDENT));

		student_session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		laboratoryRepository.insert(new Laboratory("lab01"));
		laboratoryRepository.insert(new Laboratory("lab02"));
		laboratoryRepository.insert(new Laboratory("lab03"));
	}

	@Test
	public void listMustReturnAllLaboratories() {
		// Arrange
		IListLaboratoriesView view = new ListLaboratoriesViewMock();

		// Act
		new ListLaboratoriesPresenter(view, laboratoryService);
		view.setRequest(new ListLaboratoriesRequest(student_session_id));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(3, view.getResponse().size());
	}
}
