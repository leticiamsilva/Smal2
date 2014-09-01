package org.smal2.presenter;

import org.smal2.presenter.view.I_ListUsersView;
import org.smal2.service.UserService;

public class ListUsersPresenter {

	public ListUsersPresenter(I_ListUsersView view, UserService userService) {

		view.setUsers(userService.listUser());
	}
}
