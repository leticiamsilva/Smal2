package org.smal2.test.presenter;

import org.smal2.domain.entity.Trouble;
import org.smal2.presentation.presenter.ListTroublesPresenter;
import org.smal2.presentation.view.IListTroublesView;
import org.smal2.presentation.view.basic.ListTroublesViewMock;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListTroublesPresenterTest extends ABaseTest {

	@Before
	public void before() {
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
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(3, view.getResponse().size());
	}
}
