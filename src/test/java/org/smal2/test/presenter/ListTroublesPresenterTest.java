package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.ListTroublesPresenter;
import org.smal2.presentation.view.IListTroublesView;
import org.smal2.presentation.view.basic.ListTroublesViewMock;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.trouble.ListTroublesRequest;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListTroublesPresenterTest extends ABaseTest {

	private String session_id;

	@Before
	public void before() {

		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.STUDENT));

		// Arrange
		session_id = authService
				.loginUser(new LoginUserRequest("0000", "pass"))
				.getSession_id();

		troubleRepository.insert(new Trouble("trouble01"));
		troubleRepository.insert(new Trouble("trouble02"));
		troubleRepository.insert(new Trouble("trouble03"));
	}

	@Test
	public void listMustReturnAllTroubles() {
		// Arrange
		IListTroublesView view = new ListTroublesViewMock();

		// Act
		new ListTroublesPresenter(view, troubleService);
		view.setRequest(new ListTroublesRequest(session_id));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(3, view.getResponse().size());
	}
}
