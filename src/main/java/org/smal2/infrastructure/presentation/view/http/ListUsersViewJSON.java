package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.JSONResponse;
import org.smal2.presentation.presenter.ListUsersPresenter;
import org.smal2.presentation.view.IListUsersView;
import org.smal2.service.user.ListUsersResponse;
import org.smal2.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/user")
public class ListUsersViewJSON implements IListUsersView {
	@Autowired
	private UserService userService;

	private ListUsersResponse users;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public JSONResponse listUsers() {
		try {
			new ListUsersPresenter(this, userService);

			return new JSONResponse(true, users);

		} catch (Exception ex) {
			return new JSONResponse(false, "Error:\n" + ex.getMessage());
		}
	}

	@Override
	public ListUsersResponse getResponse() {
		return users;
	}

	@Override
	public void setResponse(ListUsersResponse users) {
		this.users = users;
	}
}
