package org.smal2.presentation.presenter;

import org.smal2.presentation.view.IListUsersView;
import org.smal2.service.user.UserService;

public class ListUsersPresenter {

	public ListUsersPresenter(IListUsersView view, UserService userService) {

		view.setResponse(userService.listUser());
	}
}
