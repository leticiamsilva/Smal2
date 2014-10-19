package org.smal2.test.presenter;

import org.smal2.domain.entity.Trouble;
import org.smal2.presentation.presenter.RegisterTroublePresenter;
import org.smal2.presentation.view.IRegisterTroubleView;
import org.smal2.presentation.view.basic.RegisterTroubleViewMock;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterTroublePresenterTest extends ABaseTest {

	@Before
	public void before() {
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
		view.setRequest("trouble04");
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
		view.setRequest("trouble01");
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(
				"Register trouble error:\nTrouble name already exist.",
				view.getError());
	}
}
