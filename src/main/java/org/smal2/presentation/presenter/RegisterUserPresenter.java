package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IRegisterUserView;
import org.smal2.service.user.UserService;

public class RegisterUserPresenter {

	private IRegisterUserView view;
	private UserService userService;

	public RegisterUserPresenter(IRegisterUserView view,
			UserService userService) {

		this.view = view;
		this.userService = userService;

		this.view.setCommand(new ICommand() {
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
