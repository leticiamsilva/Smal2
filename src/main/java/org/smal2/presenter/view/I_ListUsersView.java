package org.smal2.presenter.view;

import org.smal2.service.user.ListUsersResponse;

public interface I_ListUsersView {

	ListUsersResponse getUsers();

	void setUsers(ListUsersResponse users);
}
