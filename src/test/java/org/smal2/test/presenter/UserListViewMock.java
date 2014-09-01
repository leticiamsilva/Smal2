package org.smal2.test.presenter;

import java.util.ArrayList;

import org.smal2.presenter.view.I_UserListView;
import org.smal2.service.user.ListUsersResponse;
import org.smal2.service.user.ListUsersResponseItem;

public class UserListViewMock implements I_UserListView {

	private ListUsersResponse users;

	public UserListViewMock() {
		users = new ListUsersResponse(new ArrayList<ListUsersResponseItem>());
	}

	@Override
	public ListUsersResponse getUsers() {
		return users;
	}

	@Override
	public void setUsers(ListUsersResponse users) {
		this.users.clear();
		this.users.addAll(users);
	}
}
