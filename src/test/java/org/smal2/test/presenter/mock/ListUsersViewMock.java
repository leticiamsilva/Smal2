package org.smal2.test.presenter.mock;

import java.util.ArrayList;

import org.smal2.presenter.view.I_ListUsersView;
import org.smal2.service.user.ListUsersResponse;
import org.smal2.service.user.ListUsersResponseItem;

public class ListUsersViewMock implements I_ListUsersView {

	private ListUsersResponse users;

	public ListUsersViewMock() {
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
