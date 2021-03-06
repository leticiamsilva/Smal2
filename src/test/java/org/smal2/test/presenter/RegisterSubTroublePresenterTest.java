package org.smal2.test.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.RegisterSubTroublePresenter;
import org.smal2.presentation.view.IRegisterSubTroubleView;
import org.smal2.presentation.view.basic.RegisterSubTroubleViewMock;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.subtrouble.RegisterSubTroubleRequest;
import org.smal2.test.testutil.ABaseTest;

public class RegisterSubTroublePresenterTest extends ABaseTest {

	private String administrator_session_id;

	@Before
	public void before() {
		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.ADMINISTRATOR));

		administrator_session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		Trouble trouble = new Trouble("trouble01");
		troubleRepository.insert(trouble);
		subTroubleRepository.insert(new SubTrouble("subtrouble01", trouble));
		subTroubleRepository.insert(new SubTrouble("subtrouble02", trouble));
		subTroubleRepository.insert(new SubTrouble("subtrouble03", trouble));
	}

	@Test
	public void registerNewSubTroubleMustSaveTrouble() {
		// Arrange
		IRegisterSubTroubleView view = new RegisterSubTroubleViewMock();

		// Act
		new RegisterSubTroublePresenter(view, subTroubleService);
		view.setRequest(new RegisterSubTroubleRequest(administrator_session_id,
				"subtrouble04", "trouble01"));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(4, subTroubleRepository.listAll().size());
		Assert.assertEquals("subtrouble04",
				subTroubleRepository.get("subtrouble04").getName());
		Assert.assertEquals("Sub-trouble registred successfully.",
				view.getResponse());
	}

	@Test
	public void registerExistentSubTroubleNameMustShowError() {
		// Arrange
		IRegisterSubTroubleView view = new RegisterSubTroubleViewMock();

		// Act
		new RegisterSubTroublePresenter(view, subTroubleService);
		view.setRequest(new RegisterSubTroubleRequest(administrator_session_id,
				"subtrouble01", "trouble01"));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(
				"Register sub-trouble error:\nSub-trouble name already exist.",
				view.getError());
	}
}
