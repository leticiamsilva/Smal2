package org.smal2.service.computer;

import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.domain.repository.ComputerRepository;
import org.smal2.domain.repository.LaboratoryRepository;
import org.smal2.domain.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComputerService {

	@Autowired
	private ComputerRepository computerRepository;

	@Autowired
	LaboratoryRepository laboratoryRepository;

	@Autowired
	PositionRepository positionRepository;

	public void registerComputer(RegisterComputerRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (request.getAssetCode() == null || request.getAssetCode() == "") {
			throw new IllegalArgumentException("Undefined computer asset code.");
		}

		if (computerRepository.existWithAssetCode(request.getAssetCode())) {
			throw new IllegalArgumentException(
					"Computer asset code already exist.");
		}

		if (request.getLaboratory() == null || request.getLaboratory() == "") {
			throw new IllegalArgumentException("Undefined laboratory name.");
		}

		if (!laboratoryRepository.existWithName(request.getLaboratory())) {
			throw new IllegalArgumentException("Laboratory must exist.");
		}

		Laboratory lab = laboratoryRepository
				.getByName(request.getLaboratory());

		if (request.getRowNum() > 6) {
			throw new IllegalArgumentException(
					"Position row number cannot be greather then 6.");
		}

		Position pos;

		if (positionRepository.existWithLaboratoryAndPosition(
				request.getLaboratory(), request.getRowNum(),
				request.getColumnNum())) {
			pos = positionRepository.getByLaboratoryAndPosition(
					request.getLaboratory(), request.getRowNum(),
					request.getColumnNum());

			if (computerRepository.existWithPosition(pos.getId())) {
				throw new IllegalArgumentException("Position must be empty.");
			}
		} else {
			pos = new Position(request.getRowNum(), request.getColumnNum(), lab);
			positionRepository.save(pos);
		}

		computerRepository.insert(new Computer(request.getAssetCode(), pos));
	}
}
