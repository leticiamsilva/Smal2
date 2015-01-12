package org.smal2.service.computer;

import java.util.ArrayList;
import java.util.List;

import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.domain.entity.User;
import org.smal2.domain.entity.UserType;
import org.smal2.domain.repository.ComputerRepository;
import org.smal2.domain.repository.LaboratoryRepository;
import org.smal2.domain.repository.PositionRepository;
import org.smal2.domain.repository.UserRepository;
import org.smal2.service.computer.ListComputersResponse;
import org.smal2.service.computer.ListComputersResponseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComputerService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ComputerRepository computerRepository;

	@Autowired
	private LaboratoryRepository laboratoryRepository;

	@Autowired
	private PositionRepository positionRepository;

	public String registerComputer(RegisterComputerRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (request.getSession_id() == null
				|| request.getSession_id().equals("")) {
			throw new IllegalArgumentException("Undefined session identifier.");
		}

		if (!userRepository.existWithSessionId(request.getSession_id())) {
			throw new IllegalArgumentException("Invalid session identifier");
		}

		User user = userRepository.getBySessionId(request.getSession_id());

		if (user.getType() != UserType.ADMINISTRATOR
				&& user.getType() != UserType.TECHNICIAN) {
			throw new IllegalArgumentException(
					"User must be at least a Technician to perform this operation.");
		}

		if (request.getAsset_code() == null
				|| request.getAsset_code().equals("")) {
			throw new IllegalArgumentException("Undefined computer asset code.");
		}

		if (computerRepository.existWithAssetCode(request.getAsset_code())) {
			throw new IllegalArgumentException(
					"Computer asset code already exist.");
		}

		if (request.getLaboratory_name() == null
				|| request.getLaboratory_name().equals("")) {
			throw new IllegalArgumentException("Undefined laboratory name.");
		}

		if (!laboratoryRepository.existWithName(request.getLaboratory_name())) {
			throw new IllegalArgumentException("Laboratory must exist.");
		}

		Laboratory lab = laboratoryRepository.get(request.getLaboratory_name());

		if (request.getRow_num() < 1) {
			throw new IllegalArgumentException(
					"Position row cannot be less then 1.");
		}

		if (request.getColumn_num() < 1) {
			throw new IllegalArgumentException(
					"Position column cannot be less then 1.");
		}

		if (request.getColumn_num() > 6) {
			throw new IllegalArgumentException(
					"Position column cannot be greather then 6.");
		}

		Position pos;

		if (positionRepository.existWithLaboratoryAndPosition(
				request.getLaboratory_name(), request.getRow_num(),
				request.getColumn_num())) {
			pos = positionRepository.getByLaboratoryAndPosition(
					request.getLaboratory_name(), request.getRow_num(),
					request.getColumn_num());

			if (computerRepository.existWithPosition(pos.getId())) {
				throw new IllegalArgumentException("Position must be empty.");
			}
		} else {
			pos = new Position(request.getRow_num(), request.getColumn_num(),
					lab);
			positionRepository.insert(pos);
		}

		computerRepository.insert(new Computer(request.getAsset_code(), pos));

		return "Computer registred successfully.";
	}

	public ListComputersResponse listComputers(String laboratoryName) {

		if (laboratoryName == null || laboratoryName.equals("")) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (!laboratoryRepository.existWithName(laboratoryName)) {
			throw new IllegalArgumentException("Laboratory must exist.");
		}

		List<ListComputersResponseItem> computers = new ArrayList<ListComputersResponseItem>();
		ListComputersResponseItem item;

		for (Computer computer : computerRepository.listAll()) {
			if (computer.getPosition().getLaboratory().getName()
					.equals(laboratoryName)) {
				item = new ListComputersResponseItem(computer.getAssetCode(),
						computer.getPosition().getRowNum(), computer
								.getPosition().getColumnNum());
				computers.add(item);
			}
		}

		return new ListComputersResponse(computers);
	}
}
