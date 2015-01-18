package org.smal2.service.subtrouble;

import java.util.ArrayList;
import java.util.List;

import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.domain.entity.UserType;
import org.smal2.domain.repository.SubTroubleRepository;
import org.smal2.domain.repository.TroubleRepository;
import org.smal2.domain.repository.UserRepository;
import org.smal2.service.subtrouble.ListSubTroublesResponse;
import org.smal2.service.subtrouble.ListSubTroublesResponseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubTroubleService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubTroubleRepository subTroubleRepository;

	@Autowired
	private TroubleRepository troubleRepository;

	public String registerSubTrouble(RegisterSubTroubleRequest request) {

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
			throw new IllegalArgumentException("Undefined sub-trouble name.");
		}

		if (subTroubleRepository.existWithName(request.getName())) {
			throw new IllegalArgumentException(
					"Sub-trouble name already exist.");
		}

		if (request.getTrouble_name() == null
				|| request.getTrouble_name().equals("")) {
			throw new IllegalArgumentException("Undefined trouble name.");
		}

		if (!troubleRepository.existWithName(request.getTrouble_name())) {
			throw new IllegalArgumentException("Trouble must exist.");
		}

		Trouble trouble = troubleRepository.get(request.getTrouble_name());

		subTroubleRepository.insert(new SubTrouble(request.getName(), trouble));

		return "Sub-trouble registred successfully.";
	}

	public ListSubTroublesResponse listSubTroubles(
			ListSubTroublesRequest request) {

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

		if (request.getTrouble_name() == null
				|| request.getTrouble_name().equals("")) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (!troubleRepository.existWithName(request.getTrouble_name())) {
			throw new IllegalArgumentException("Trouble must exist.");
		}

		List<ListSubTroublesResponseItem> subTroubles = new ArrayList<ListSubTroublesResponseItem>();
		ListSubTroublesResponseItem item;

		for (SubTrouble subTrouble : subTroubleRepository.listAll()) {
			if (subTrouble.getTrouble().getName()
					.equals(request.getTrouble_name())) {
				item = new ListSubTroublesResponseItem(subTrouble.getName(),
						subTrouble.getTrouble().getName());
				subTroubles.add(item);
			}
		}

		return new ListSubTroublesResponse(subTroubles);
	}
}
