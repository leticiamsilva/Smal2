package org.smal2.service.user;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ListUsersResponse extends ArrayList<ListUsersResponseItem> {

	public ListUsersResponse(List<ListUsersResponseItem> users) {
		addAll(users);
	}
}
