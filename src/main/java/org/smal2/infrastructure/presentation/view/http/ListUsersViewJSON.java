package org.smal2.infrastructure.presentation.view.http;

import org.smal2.common.ICommand;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public OperationResponse<ListUsersResponse> listUsers() {
		OperationResponse<ListUsersResponse> response = new OperationResponse<ListUsersResponse>();

		try {
			new ListUsersPresenter(this, userService);
			command.execute();
			response.setResponse(this.response);

		} catch (Exception ex) {
			response.setError("Error:\n" + ex.getMessage());
		}

		return response;
	}

	// IListUsersView implementation

	private ICommand command;
	private ListUsersResponse response;

	@Override
	public ICommand getCommand() {
		return command;
	}

	@Override
	public void setCommand(ICommand command) {
		this.command = command;
	}

	@Override
	public ListUsersResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(ListUsersResponse users) {
		this.response = users;
	}

	// end IListUsersView
}
