package org.smal2.test.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Trouble;
import org.smal2.presentation.presenter.RegisterSubTroublePresenter;
import org.smal2.presentation.view.IRegisterSubTroubleView;
import org.smal2.presentation.view.basic.RegisterSubTroubleViewMock;
import org.smal2.service.subtrouble.RegisterSubTroubleRequest;
import org.smal2.test.testutil.ABaseTest;

public class RegisterSubTroublePresenterTest extends ABaseTest {

	@Before
	public void before() {
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
		view.setRequest(new RegisterSubTroubleRequest("subtrouble04",
				"trouble01"));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(4, subTroubleRepository.listAll().size());
		Assert.assertEquals("subtrouble04",
				subTroubleRepository.get("subtrouble04").getName());
		Assert.assertTrue(view.getResponse().equals(
				"Sub-trouble registred successfully."));
	}

	@Test
	public void registerExistentSubTroubleNameMustShowError() {
		// Arrange
		IRegisterSubTroubleView view = new RegisterSubTroubleViewMock();

		// Act
		new RegisterSubTroublePresenter(view, subTroubleService);
		view.setRequest(new RegisterSubTroubleRequest("subtrouble01",
				"trouble01"));
		view.getCommand().execute();

		// Assert
		Assert.assertTrue(view.getError().contains(
				"Sub-trouble name already exist"));
	}
}
