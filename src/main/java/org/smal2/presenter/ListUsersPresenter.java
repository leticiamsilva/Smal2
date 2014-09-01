package org.smal2.presenter;

import org.smal2.presenter.view.IListUsersView;
import org.smal2.service.user.UserService;

public class ListUsersPresenter {

	public ListUsersPresenter(IListUsersView view, UserService userService) {

		view.setResponse(userService.listUser());
	}
}
