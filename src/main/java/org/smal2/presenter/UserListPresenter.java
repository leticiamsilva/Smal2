package org.smal2.presenter;

import org.smal2.presenter.view.I_UserListView;
import org.smal2.service.UserService;

public class UserListPresenter {

	public UserListPresenter(I_UserListView view, UserService userService) {

		view.setUsers(userService.listUser());
	}
}
