package org.smal2.test.presenter;

import org.smal2.presentation.presenter.RegisterLaboratoryPresenter;
import org.smal2.presentation.view.IRegisterLaboratoryView;
import org.smal2.test.presenter.mock.RegisterLaboratoryViewMock;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class RegisterLaboratoryPresenterTest extends ALaboratoriesPresenterTest {

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
		Assert.assertEquals("lab04", laboratoryRepository.getByName("lab04")
				.getName());
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
		Assert.assertTrue(view.getResponse().contains(
				"Laboratory register error"));
	}
}
