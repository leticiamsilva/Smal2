package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IDeAuthUserView;
import org.smal2.service.auth.AuthService;

public class DeAuthUserPresenter {

	private IDeAuthUserView view;
	private AuthService service;

	public DeAuthUserPresenter(IDeAuthUserView view, AuthService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doLogout();
			}
		});
	}

	private void doLogout() {
		try {
			view.setResponse(service.logoutUser(view.getRequest()));
		} catch (Exception ex) {
			view.setError("Logout user error:\n" + ex.getMessage());
		}
	}
}
