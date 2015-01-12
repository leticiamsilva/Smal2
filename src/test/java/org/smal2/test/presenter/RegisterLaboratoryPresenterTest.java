package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.RegisterLaboratoryPresenter;
import org.smal2.presentation.view.IRegisterLaboratoryView;
import org.smal2.presentation.view.basic.RegisterLaboratoryViewMock;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.laboratory.RegisterLaboratoryRequest;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterLaboratoryPresenterTest extends ABaseTest {

	@Before
	public void before() {
		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.ADMINISTRATOR));

		laboratoryRepository.insert(new Laboratory("lab01"));
		laboratoryRepository.insert(new Laboratory("lab02"));
		laboratoryRepository.insert(new Laboratory("lab03"));
	}

	@Test
	public void registerLaboratoryMustSaveLaboratory() {
		// Arrange
		String session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		IRegisterLaboratoryView view = new RegisterLaboratoryViewMock();

		// Act
		new RegisterLaboratoryPresenter(view, laboratoryService);
		view.setRequest(new RegisterLaboratoryRequest(session_id, "lab04"));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(4, laboratoryRepository.listAll().size());
		Assert.assertEquals("lab04", laboratoryRepository.get("lab04")
				.getName());
		Assert.assertEquals("Laboratory registred successfully.",
				view.getResponse());
	}

	@Test
	public void registerExistentLaboratoryMustShowError() {
		// Arrange
		String session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		IRegisterLaboratoryView view = new RegisterLaboratoryViewMock();

		// Act
		new RegisterLaboratoryPresenter(view, laboratoryService);
		view.setRequest(new RegisterLaboratoryRequest(session_id, "lab01"));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(
				"Register laboratory error:\nLaboratory name already exist.",
				view.getError());
	}
}
