package org.smal2.service.laboratory;

import java.util.ArrayList;
import java.util.List;

import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.User;
import org.smal2.domain.entity.UserType;
import org.smal2.domain.repository.LaboratoryRepository;
import org.smal2.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LaboratoryRepository laboratoryRepository;

	public String registerLaboratory(RegisterLaboratoryRequest request) {
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

		if (request.getName() == null || request.getName().equals("")) {
			throw new IllegalArgumentException("Undefined laboratory name.");
		}

		if (laboratoryRepository.existWithName(request.getName())) {
			throw new IllegalArgumentException("Laboratory name already exist.");
		}

		laboratoryRepository.insert(new Laboratory(request.getName()));

		return "Laboratory registred successfully.";
	}

	public ListLaboratoriesResponse listLaboratories() {

		List<ListLaboratoriesResponseItem> laboratories = new ArrayList<ListLaboratoriesResponseItem>();
		ListLaboratoriesResponseItem item;

		for (Laboratory laboratory : laboratoryRepository.listAll()) {
			item = new ListLaboratoriesResponseItem(laboratory.getName());
			laboratories.add(item);
		}

		return new ListLaboratoriesResponse(laboratories);
	}
}
