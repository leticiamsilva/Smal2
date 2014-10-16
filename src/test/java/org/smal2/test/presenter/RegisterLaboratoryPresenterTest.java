package org.smal2.test.presenter;

import org.smal2.domain.entity.Laboratory;
import org.smal2.presentation.presenter.RegisterLaboratoryPresenter;
import org.smal2.presentation.view.IRegisterLaboratoryView;
import org.smal2.presentation.view.basic.RegisterLaboratoryViewMock;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterLaboratoryPresenterTest extends ABaseTest {

	@Before
	public void before() {
		laboratoryRepository.insert(new Laboratory("lab01"));
		laboratoryRepository.insert(new Laboratory("lab02"));
		laboratoryRepository.insert(new Laboratory("lab03"));
	}

	@Test
	public void registerLaboratoryMustSaveLaboratory() {
		// Arrange
		IRegisterLaboratoryView view = new RegisterLaboratoryViewMock();

		// Act
		new RegisterLaboratoryPresenter(view, laboratoryService);
		view.setRequest("lab04");
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(4, laboratoryRepository.listAll().size());
		Assert.assertEquals("lab04", laboratoryRepository.get("lab04")
				.getName());
		Assert.assertTrue(view.getResponse().equals(
				"Laboratory registred successfully."));
	}

	@Test
	public void registerExistentLaboratoryMustShowError() {
		// Arrange
		IRegisterLaboratoryView view = new RegisterLaboratoryViewMock();

		// Act
		new RegisterLaboratoryPresenter(view, laboratoryService);
		view.setRequest("lab01");
		view.getCommand().execute();

		// Assert
		Assert.assertTrue(view.getError().contains("Register laboratory error"));
	}
}
