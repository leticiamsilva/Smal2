package org.smal2.service.trouble;

import java.util.ArrayList;
import java.util.List;

import org.smal2.domain.entity.Trouble;
import org.smal2.domain.repository.TroubleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TroubleService {

	@Autowired
	private TroubleRepository repository;

	public String registerTrouble(String name) {

		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Undefined trouble name.");
		}

		if (repository.existWithName(name)) {
			throw new IllegalArgumentException("Trouble name already exist.");
		}

		repository.insert(new Trouble(name));

		return "Trouble registred successfully.";
	}

	public ListTroublesResponse listTroubles() {

		List<ListTroublesResponseItem> troubles = new ArrayList<ListTroublesResponseItem>();
		ListTroublesResponseItem item;

		for (Trouble trouble : repository.listAll()) {
			item = new ListTroublesResponseItem(trouble.getName());
			troubles.add(item);
		}

		return new ListTroublesResponse(troubles);
	}
}
