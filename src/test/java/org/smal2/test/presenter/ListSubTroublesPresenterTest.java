package org.smal2.test.presenter;

import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Trouble;
import org.smal2.presentation.presenter.ListSubTroublesPresenter;
import org.smal2.presentation.view.IListSubTroublesView;
import org.smal2.presentation.view.basic.ListSubTroublesViewMock;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListSubTroublesPresenterTest extends ABaseTest {

	@Before
	public void before() {
		Trouble trouble = new Trouble("trouble01");
		troubleRepository.insert(trouble);
		subTroubleRepository.insert(new SubTrouble("subtrouble01", trouble));
		subTroubleRepository.insert(new SubTrouble("subtrouble02", trouble));
		subTroubleRepository.insert(new SubTrouble("subtrouble03", trouble));
	}

	@Test
	public void listMustReturnAllSubTroubles() {
		// Arrange
		IListSubTroublesView view = new ListSubTroublesViewMock();

		// Act
		new ListSubTroublesPresenter(view, subTroubleService);
		view.setRequest("trouble01");
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(3, view.getResponse().size());
	}
}
