package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListLaboratoriesView;
import org.smal2.service.laboratory.LaboratoryService;

public class ListLaboratoriesPresenter {

	private IListLaboratoriesView view;
	private LaboratoryService service;

	public ListLaboratoriesPresenter(IListLaboratoriesView view,
			LaboratoryService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doListLaboratories();
			}
		});
	}

	public void doListLaboratories() {
		try {
			view.setResponse(service.listLaboratories(view.getRequest()));
		} catch (Exception ex) {
			view.setError("List laboratories error:\n" + ex.getMessage());
		}
	}
}
