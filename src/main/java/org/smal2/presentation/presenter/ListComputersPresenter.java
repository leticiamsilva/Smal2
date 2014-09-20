package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListComputersView;
import org.smal2.service.computer.ComputerService;

public class ListComputersPresenter {

	private IListComputersView view;
	private ComputerService service;

	public ListComputersPresenter(IListComputersView view,
			ComputerService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doListComputers();
			}
		});
	}

	private void doListComputers() {
		try {
			view.setResponse(service.listComputers(view.getRequest()));
		} catch (Exception ex) {
			view.setError("List computers error:\n" + ex.getMessage());
		}
	}
}
