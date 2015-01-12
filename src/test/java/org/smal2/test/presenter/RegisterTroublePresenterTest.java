package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.RegisterTroublePresenter;
import org.smal2.presentation.view.IRegisterTroubleView;
import org.smal2.presentation.view.basic.RegisterTroubleViewMock;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.trouble.RegisterTroubleRequest;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterTroublePresenterTest extends ABaseTest {

	private String session_id;

	@Before
	public void before() {
		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.ADMINISTRATOR));

		session_id = authService
				.loginUser(new LoginUserRequest("0000", "pass"))
				.getSession_id();

		troubleRepository.insert(new Trouble("trouble01"));
		troubleRepository.insert(new Trouble("trouble02"));
		troubleRepository.insert(new Trouble("trouble03"));
	}

	@Test
	public void registerTroubleMustSaveTrouble() {
		// Arrange
		IRegisterTroubleView view = new RegisterTroubleViewMock();

		// Act
		new RegisterTroublePresenter(view, troubleService);
		view.setRequest(new RegisterTroubleRequest(session_id, "trouble04"));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(4, troubleRepository.listAll().size());
		Assert.assertEquals("trouble04", troubleRepository.get("trouble04")
				.getName());
		Assert.assertEquals("Trouble registred successfully.",
				view.getResponse());
	}

	@Test
	public void registerExistentTroubleMustShowError() {
		// Arrange
		IRegisterTroubleView view = new RegisterTroubleViewMock();

		// Act
		new RegisterTroublePresenter(view, troubleService);
		view.setRequest(new RegisterTroubleRequest(session_id, "trouble01"));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(
				"Register trouble error:\nTrouble name already exist.",
				view.getError());
	}
}
