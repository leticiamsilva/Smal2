package org.smal2.test.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.RegisterComputerPresenter;
import org.smal2.presentation.view.IRegisterComputerView;
import org.smal2.presentation.view.basic.RegisterComputerViewMock;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.computer.RegisterComputerRequest;
import org.smal2.test.testutil.ABaseTest;

public class RegisterComputerPresenterTest extends ABaseTest {

	@Before
	public void before() {
		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.ADMINISTRATOR));

		Laboratory lab = new Laboratory("lab01");
		laboratoryRepository.insert(lab);

		Position pos = new Position(1, 1, lab);
		positionRepository.insert(pos);

		computerRepository.insert(new Computer("asset01", pos));
	}

	@Test
	public void registerNewComputerMustSaveComputer() {
		// Arrange
		String assetCode = "asset02";
		String laboratory = "lab01";
		int row_num = 2;
		int col_num = 1;

		String session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		IRegisterComputerView view = new RegisterComputerViewMock();

		// Act
		new RegisterComputerPresenter(view, computerService);
		view.setRequest(new RegisterComputerRequest(session_id, assetCode,
				laboratory, row_num, col_num));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(2, computerRepository.listAll().size());
		Assert.assertEquals("Computer registred successfully.",
				view.getResponse());
	}

	@Test
	public void registerExistentComputerAssetCodeMustShowError() {
		// Arrange
		String assetCode = "asset01";
		String laboratory = "lab01";
		int row_num = 2;
		int col_num = 1;

		String session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		IRegisterComputerView view = new RegisterComputerViewMock();

		// Act
		new RegisterComputerPresenter(view, computerService);
		view.setRequest(new RegisterComputerRequest(session_id, assetCode,
				laboratory, row_num, col_num));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(1, computerRepository.listAll().size());
		Assert.assertEquals(
				"Register computer error:\nComputer asset code already exist.",
				view.getError());
	}
}
