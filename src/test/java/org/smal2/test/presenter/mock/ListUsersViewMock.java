package org.smal2.test.presenter.mock;

import java.util.ArrayList;

import org.smal2.presenter.view.IListUsersView;
import org.smal2.service.user.ListUsersResponse;
import org.smal2.service.user.ListUsersResponseItem;

public class ListUsersViewMock implements IListUsersView {

	private ListUsersResponse users;

	public ListUsersViewMock() {
		users = new ListUsersResponse(new ArrayList<ListUsersResponseItem>());
	}

	@Override
	public ListUsersResponse getResponse() {
		return users;
	}

	@Override
	public void setResponse(ListUsersResponse users) {
		this.users.clear();
		this.users.addAll(users);
	}
}
