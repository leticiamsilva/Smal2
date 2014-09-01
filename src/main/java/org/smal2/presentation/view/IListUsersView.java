package org.smal2.presentation.view;

import org.smal2.service.user.ListUsersResponse;

public interface IListUsersView {

	ListUsersResponse getResponse();

	void setResponse(ListUsersResponse users);
}
