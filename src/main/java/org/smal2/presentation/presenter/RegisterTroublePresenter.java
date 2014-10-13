package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IRegisterTroubleView;
import org.smal2.service.trouble.TroubleService;

public class RegisterTroublePresenter {

	private IRegisterTroubleView view;
	private TroubleService service;

	public RegisterTroublePresenter(IRegisterTroubleView view,
			TroubleService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doRegisterTrouble();
			}
		});
	}

	private void doRegisterTrouble() {
		try {
			view.setResponse(service.registerTrouble(view.getRequest()));
		} catch (Exception ex) {
			view.setError("Register trouble error:\n" + ex.getMessage());
		}
	}
}
