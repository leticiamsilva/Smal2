package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IRegisterLaboratoryView;
import org.smal2.service.laboratory.LaboratoryService;

public class RegisterLaboratoryPresenter {

	private IRegisterLaboratoryView view;
	private LaboratoryService service;

	public RegisterLaboratoryPresenter(IRegisterLaboratoryView view,
			LaboratoryService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doRegisterLaboratory();
			}
		});
	}

	private void doRegisterLaboratory() {
		try {
			view.setResponse(service.registerLaboratory(view.getRequest()));
		} catch (Exception ex) {
			view.setResponse("Laboratory register error:\n" + ex.getMessage());
		}
	}
}
