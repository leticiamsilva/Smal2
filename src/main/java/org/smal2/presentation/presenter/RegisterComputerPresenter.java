package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IRegisterComputerView;
import org.smal2.service.computer.ComputerService;

public class RegisterComputerPresenter {

	private IRegisterComputerView view;
	private ComputerService service;

	public RegisterComputerPresenter(IRegisterComputerView view,
			ComputerService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doRegisterComputer();
			}
		});
	}

	private void doRegisterComputer() {
		try {
			view.setResponse(service.registerComputer(view.getRequest()));
		} catch (Exception ex) {
			view.setError("Register computer error:\n" + ex.getMessage());
		}
	}
}
