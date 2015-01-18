package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.ListSubTroublesPresenter;
import org.smal2.presentation.view.IListSubTroublesView;
import org.smal2.presentation.view.basic.ListSubTroublesViewMock;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.subtrouble.ListSubTroublesRequest;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListSubTroublesPresenterTest extends ABaseTest {

	private String session_id;

	@Before
	public void before() {

		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.STUDENT));

		// Arrange
		session_id = authService
				.loginUser(new LoginUserRequest("0000", "pass"))
				.getSession_id();

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
		view.setRequest(new ListSubTroublesRequest(session_id, "trouble01"));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(3, view.getResponse().size());
	}
}
