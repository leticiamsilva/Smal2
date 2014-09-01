package org.smal2.presenter;

import org.smal2.common.I_Command;
import org.smal2.presenter.view.I_RegisterUserView;
import org.smal2.service.user.UserService;

public class RegisterUserPresenter {

	private I_RegisterUserView view;
	private UserService userService;

	public RegisterUserPresenter(I_RegisterUserView view,
			UserService userService) {

		this.view = view;
		this.userService = userService;

		this.view.setCommand(new I_Command() {
			public void execute() {
				doRegisterUser();
			}
		});
	}

	private void doRegisterUser() {
		try {
			userService.registerUser(view.getRequest());
			view.setResponse("User registred successfully.");
		} catch (Exception ex) {
			view.setResponse("User register error:\n" + ex.getMessage());
		}
	}
}
