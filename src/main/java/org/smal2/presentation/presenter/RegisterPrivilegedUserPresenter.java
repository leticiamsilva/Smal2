package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IRegisterPrivilegedUserView;
import org.smal2.service.user.UserService;

public class RegisterPrivilegedUserPresenter {

	private IRegisterPrivilegedUserView view;
	private UserService service;

	public RegisterPrivilegedUserPresenter(IRegisterPrivilegedUserView view,
			UserService userService) {

		this.view = view;
		this.service = userService;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doRegisterPrivilegedUser();
			}
		});
	}

	private void doRegisterPrivilegedUser() {
		try {
			view.setResponse(service.registerPrivilegedUser(view.getRequest()));
		} catch (Exception ex) {
			view.setResponse("User register error:\n" + ex.getMessage());
		}
	}
}
