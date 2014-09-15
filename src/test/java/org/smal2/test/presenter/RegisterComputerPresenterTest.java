package org.smal2.test.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.presentation.presenter.RegisterComputerPresenter;
import org.smal2.presentation.view.IRegisterComputerView;
import org.smal2.service.computer.RegisterComputerRequest;
import org.smal2.test.presenter.mock.RegisterComputerViewMock;
import org.smal2.test.testutils.AComputerTest;

public class RegisterComputerPresenterTest extends AComputerTest {

	@Before
	public void before() {
		Laboratory lab = new Laboratory("lab01");
		laboratoryRepository.insert(lab);

		Position pos = new Position(1, 1, lab);
		positionRepository.insert(pos);

		computerRepository.insert(new Computer("asset01", pos));
	}

	@Test
	public void registerNewComputerMustRegister() {
		// Arrange
		String assetCode = "asset02";
		String laboratory = "lab01";
		int row_num = 2;
		int col_num = 1;

		IRegisterComputerView view = new RegisterComputerViewMock();

		// Act
		new RegisterComputerPresenter(view, computerService);
		view.setRequest(new RegisterComputerRequest(assetCode, laboratory,
				row_num, col_num));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(2, computerRepository.listAll().size());
		Assert.assertTrue(view.getResponse().equals(
				"Computer registred successfully."));
	}

	@Test
	public void registerExisterComputerAssetCodeMustShowError() {
		// Arrange
		String assetCode = "asset01";
		String laboratory = "lab01";
		int row_num = 2;
		int col_num = 1;

		IRegisterComputerView view = new RegisterComputerViewMock();

		// Act
		new RegisterComputerPresenter(view, computerService);
		view.setRequest(new RegisterComputerRequest(assetCode, laboratory,
				row_num, col_num));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(1, computerRepository.listAll().size());
		Assert.assertTrue(view.getResponse()
				.contains("Computer register error"));
	}
}
