package org.smal2.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.domain.entity.User;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.computer.RegisterComputerRequest;
import org.smal2.test.testutil.ABaseTest;

public class RegisterComputerServiceTest extends ABaseTest {

	private String administrator_session_id;

	@Before
	public void before() {
		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.ADMINISTRATOR));

		administrator_session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		Laboratory lab = new Laboratory("lab01");
		laboratoryRepository.insert(lab);

		Position pos = new Position(3, 3, lab);
		positionRepository.insert(pos);

		computerRepository.insert(new Computer("asset01", pos));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerNullComputerMustThrowException() {
		// Act
		computerService.registerComputer(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerNullComputerAssetCodeMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, null, null, 1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerEmptyComputerAssetCodeMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, "", null, 1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerExistentComputerAssetCodeMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, "asset01", null, 1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerNullComputerLaboratoryNameMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, "asset02", null, 1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerEmptyComputerLaboratoryNameMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, "asset02", "", 1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerNotExistentComputerLaboratoryNameMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, "asset02", "lab02", 1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerComputerOnBusyPositionMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, "asset02", "lab01", 3, 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerComputerOnZeroRowPositionMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, "asset02", "lab01", 0, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerComputerOnZeroColumnPositionMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, "asset02", "lab01", 1, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerComputerOnNegativeRowPositionMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, "asset02", "lab01", -1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerComputerOnNegativeColumnPositionMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, "asset02", "lab01", 1, -1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerComputerOnColumnPositionGreatherThen6MustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest(
				administrator_session_id, "asset02", "lab01", 1, 7));
	}

	@Test
	public void registerComputerOnColumnPositionIqual6MustSaveComputer() {
		// Arrange
		String assetCode = "asset02";
		String laboratory = "lab01";
		int row_num = 6;
		int col_num = 6;

		// Act
		computerService
				.registerComputer(new RegisterComputerRequest(
						administrator_session_id, assetCode, laboratory, row_num,
						col_num));

		// Assert
		Assert.assertEquals(assetCode, computerRepository.get(assetCode)
				.getAssetCode());
		Assert.assertEquals(laboratory, computerRepository.get(assetCode)
				.getPosition().getLaboratory().getName());
		Assert.assertEquals(row_num, computerRepository.get(assetCode)
				.getPosition().getRowNum());
		Assert.assertEquals(col_num, computerRepository.get(assetCode)
				.getPosition().getColumnNum());
		Assert.assertEquals(2, computerRepository.listAll().size());
	}

	@Test
	public void registerNewComputerAt1_2MustSaveComputer() {
		// Arrange
		String assetCode = "asset03";
		String laboratory = "lab01";
		int row_num = 1;
		int col_num = 2;

		// Act
		computerService
				.registerComputer(new RegisterComputerRequest(
						administrator_session_id, assetCode, laboratory, row_num,
						col_num));

		// Assert
		Assert.assertEquals(assetCode, computerRepository.get(assetCode)
				.getAssetCode());
		Assert.assertEquals(laboratory, computerRepository.get(assetCode)
				.getPosition().getLaboratory().getName());
		Assert.assertEquals(row_num, computerRepository.get(assetCode)
				.getPosition().getRowNum());
		Assert.assertEquals(col_num, computerRepository.get(assetCode)
				.getPosition().getColumnNum());
		Assert.assertEquals(2, computerRepository.listAll().size());
	}

	@Test
	public void registerNewComputerAt2_1MustSaveComputer() {
		// Arrange
		String assetCode = "asset02";
		String laboratory = "lab01";
		int row_num = 2;
		int col_num = 1;

		// Act
		computerService
				.registerComputer(new RegisterComputerRequest(
						administrator_session_id, assetCode, laboratory, row_num,
						col_num));

		// Assert
		Assert.assertEquals(assetCode, computerRepository.get(assetCode)
				.getAssetCode());
		Assert.assertEquals(laboratory, computerRepository.get(assetCode)
				.getPosition().getLaboratory().getName());
		Assert.assertEquals(row_num, computerRepository.get(assetCode)
				.getPosition().getRowNum());
		Assert.assertEquals(col_num, computerRepository.get(assetCode)
				.getPosition().getColumnNum());
		Assert.assertEquals(2, computerRepository.listAll().size());
	}
}
