package org.smal2.service.subtrouble;

import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.repository.SubTroubleRepository;
import org.smal2.domain.repository.TroubleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubTroubleService {

	@Autowired
	private SubTroubleRepository subTroubleRepository;

	@Autowired
	TroubleRepository troubleRepository;

	public String registerSubTrouble(RegisterSubTroubleRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
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
}
