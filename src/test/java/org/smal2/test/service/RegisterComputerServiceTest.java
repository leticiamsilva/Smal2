package org.smal2.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.service.computer.RegisterComputerRequest;

public class RegisterComputerServiceTest extends AComputerServiceTest {

	@Before
	public void before() {
		Laboratory lab = new Laboratory("lab01");
		laboratoryRepository.insert(lab);

		Position pos = new Position(1, 1, lab);
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
		computerService.registerComputer(new RegisterComputerRequest(null,
				null, 0, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerEmptyComputerAssetCodeMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest("", null,
				0, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerExistentComputerAssetCodeMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest("asset01",
				null, 0, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerNullComputerLaboratoryNameMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest("asset02",
				null, 0, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerEmptyComputerLaboratoryNameMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest("asset02",
				"", 0, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerNotExistentComputerLaboratoryNameMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest("asset02",
				"lab02", 0, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerComputerOnBusyPositionMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest("asset02",
				"lab01", 1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerComputerOnNegativeRowPositionMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest("asset02",
				"lab01", -1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerComputerOnNegativeColumnPositionMustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest("asset02",
				"lab01", 1, -1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerComputerOnRowPositionGreatherThen6MustThrowException() {
		// Act
		computerService.registerComputer(new RegisterComputerRequest("asset02",
				"lab01", 7, 1));
	}

	@Test
	public void registerNewComputerAt1_2MustRegister() {
		// Arrange
		String assetCode = "asset02";
		String laboratory = "lab01";
		int row_num = 1;
		int col_num = 2;

		// Act
		computerService.registerComputer(new RegisterComputerRequest(assetCode,
				laboratory, row_num, col_num));

		// Assert
		Assert.assertEquals(assetCode,
				computerRepository.getByAssetCode(assetCode).getAssetCode());
		Assert.assertEquals(laboratory,
				computerRepository.getByAssetCode(assetCode).getPosition()
						.getLaboratory().getName());
		Assert.assertEquals(row_num,
				computerRepository.getByAssetCode(assetCode).getPosition()
						.getRowNum());
		Assert.assertEquals(col_num,
				computerRepository.getByAssetCode(assetCode).getPosition()
						.getColumnNum());
		Assert.assertEquals(2, computerRepository.listAll().size());
	}

	@Test
	public void registerNewComputerAt2_1MustRegister() {
		// Arrange
		String assetCode = "asset02";
		String laboratory = "lab01";
		int row_num = 2;
		int col_num = 1;

		// Act
		computerService.registerComputer(new RegisterComputerRequest(assetCode,
				laboratory, row_num, col_num));

		// Assert
		Assert.assertEquals(assetCode,
				computerRepository.getByAssetCode(assetCode).getAssetCode());
		Assert.assertEquals(laboratory,
				computerRepository.getByAssetCode(assetCode).getPosition()
						.getLaboratory().getName());
		Assert.assertEquals(row_num,
				computerRepository.getByAssetCode(assetCode).getPosition()
						.getRowNum());
		Assert.assertEquals(col_num,
				computerRepository.getByAssetCode(assetCode).getPosition()
						.getColumnNum());
		Assert.assertEquals(2, computerRepository.listAll().size());
	}
}
