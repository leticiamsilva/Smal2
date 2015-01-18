package org.smal2.service.trouble;

import java.util.ArrayList;
import java.util.List;

import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.domain.entity.UserType;
import org.smal2.domain.repository.TroubleRepository;
import org.smal2.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TroubleService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TroubleRepository troubleRepository;

	public String registerTrouble(RegisterTroubleRequest request) {

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

		if (user.getType() != UserType.ADMINISTRATOR) {
			throw new IllegalArgumentException(
					"Administators only can perform this operation.");
		}

		if (request.getName() == null || request.getName().equals("")) {
			throw new IllegalArgumentException("Undefined trouble name.");
		}

		if (troubleRepository.existWithName(request.getName())) {
			throw new IllegalArgumentException("Trouble name already exist.");
		}

		troubleRepository.insert(new Trouble(request.getName()));

		return "Trouble registred successfully.";
	}

	public ListTroublesResponse listTroubles(ListTroublesRequest request) {

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
				&& user.getType() != UserType.TECHNICIAN
				&& user.getType() != UserType.STUDENT) {
			throw new IllegalArgumentException(
					"User must be at least a Student to perform this operation.");
		}

		List<ListTroublesResponseItem> troubles = new ArrayList<ListTroublesResponseItem>();
		ListTroublesResponseItem item;

		for (Trouble trouble : troubleRepository.listAll()) {
			item = new ListTroublesResponseItem(trouble.getName());
			troubles.add(item);
		}

		return new ListTroublesResponse(troubles);
	}
}
