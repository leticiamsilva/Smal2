package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IAuthUserView;
import org.smal2.service.auth.AuthService;

public class AuthUserPresenter {

	private IAuthUserView view;
	private AuthService service;

	public AuthUserPresenter(IAuthUserView view, AuthService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doLogin();
			}
		});
	}

	private void doLogin() {
		try {
			view.setResponse(service.loginUser(view.getRequest()));
		} catch (Exception ex) {
			view.setError("Login user error:\n" + ex.getMessage());
		}
	}
}
