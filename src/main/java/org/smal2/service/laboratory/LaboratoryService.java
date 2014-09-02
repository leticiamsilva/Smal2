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

	public ListLaboratoriesResponse listLaboratories() {

		List<ListLaboratoriesResponseItem> laboratories = new ArrayList<ListLaboratoriesResponseItem>();
		ListLaboratoriesResponseItem item;

		for (Laboratory laboratory : repository.listAll()) {
			item = new ListLaboratoriesResponseItem(laboratory.getId(),
					laboratory.getName());
			laboratories.add(item);
		}

		return new ListLaboratoriesResponse(laboratories);
	}
}
