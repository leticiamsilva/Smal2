package org.smal2.service.computer;

import java.util.ArrayList;
import java.util.List;

import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.domain.repository.ComputerRepository;
import org.smal2.domain.repository.LaboratoryRepository;
import org.smal2.domain.repository.PositionRepository;
import org.smal2.service.computer.ListComputersResponse;
import org.smal2.service.computer.ListComputersResponseItem;
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

	public ListComputersResponse listComputers(String laboratoryName) {

		if (laboratoryName == null || laboratoryName == "") {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (!laboratoryRepository.existWithName(laboratoryName)) {
			throw new IllegalArgumentException("Laboratory must exist.");
		}

		List<ListComputersResponseItem> computers = new ArrayList<ListComputersResponseItem>();
		ListComputersResponseItem item;

		for (Computer computer : computerRepository.listAll()) {
			if (computer.getPosition().getLaboratory().getName() == laboratoryName) {
				item = new ListComputersResponseItem(computer.getAssetCode(),
						computer.getPosition().getRowNum(), computer
								.getPosition().getColumnNum());
				computers.add(item);
			}
		}

		return new ListComputersResponse(computers);
	}
}
