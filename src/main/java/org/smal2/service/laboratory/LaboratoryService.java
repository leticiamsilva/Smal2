package org.smal2.service.laboratory;

import java.util.ArrayList;
import java.util.List;

import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryService {

	@Autowired
	private LaboratoryRepository repository;

	public void registerLaboratory(String name) {

		if (name == null || name == "") {
			throw new IllegalArgumentException("Undefined laboratory name.");
		}

		if (repository.existWithName(name)) {
			throw new IllegalArgumentException("Laboratory name already exist.");
		}

		repository.insert(new Laboratory(name));
	}

	public ListLaboratoriesResponse listLaboratories() {

		List<ListLaboratoriesResponseItem> laboratories = new ArrayList<ListLaboratoriesResponseItem>();
		ListLaboratoriesResponseItem item;

		for (Laboratory laboratory : repository.listAll()) {
			item = new ListLaboratoriesResponseItem(laboratory.getName());
			laboratories.add(item);
		}

		return new ListLaboratoriesResponse(laboratories);
	}
}
