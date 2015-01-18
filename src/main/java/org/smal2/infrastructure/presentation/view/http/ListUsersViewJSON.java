package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.util.OperationRequest;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.ListUsersPresenter;
import org.smal2.presentation.view.IListUsersView;
import org.smal2.presentation.view.basic.ListUsersViewMock;
import org.smal2.service.user.ListUsersRequest;
import org.smal2.service.user.ListUsersResponse;
import org.smal2.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/user")
public class ListUsersViewJSON {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<ListUsersResponse> listUsers(
			@RequestBody OperationRequest<ListUsersRequest> request) {
		OperationResponse<ListUsersResponse> response = new OperationResponse<ListUsersResponse>();

		try {
			// [CMP] spring controllers are singleton (as common servlet)
			// so we can't implements IView because his properties are shared
			IListUsersView view = new ListUsersViewMock();

			view.setRequest(request.getRequest());
			new ListUsersPresenter(view, userService);
			view.getCommand().execute();

			if (view.getError() != null) {
				response.setError(view.getError());
			} else {
				response.setResponse(view.getResponse());
			}
		} catch (Exception ex) {
			response.setError("Unexpected error:\n" + ex.getMessage());
		}

		return response;
	}
}
