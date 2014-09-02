package org.smal2.presentation.presenter;

import org.smal2.presentation.view.IListLaboratoriesView;
import org.smal2.service.laboratory.LaboratoryService;

public class ListLaboratoriesPresenter {

	public ListLaboratoriesPresenter(IListLaboratoriesView view,
			LaboratoryService service) {

		view.setResponse(service.listLaboratories());
	}
}
