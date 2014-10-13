package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IRegisterSubTroubleView;
import org.smal2.service.subtrouble.SubTroubleService;

public class RegisterSubTroublePresenter {

	private IRegisterSubTroubleView view;
	private SubTroubleService service;

	public RegisterSubTroublePresenter(IRegisterSubTroubleView view,
			SubTroubleService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doRegisterSubTrouble();
			}
		});
	}

	private void doRegisterSubTrouble() {
		try {
			view.setResponse(service.registerSubTrouble(view.getRequest()));
		} catch (Exception ex) {
			view.setError("Register sub-trouble error:\n" + ex.getMessage());
		}
	}
}
