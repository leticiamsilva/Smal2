package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.util.OperationRequest;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.AuthUserPresenter;
import org.smal2.presentation.view.IAuthUserView;
import org.smal2.presentation.view.basic.AuthUserViewMock;
import org.smal2.service.auth.AuthService;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.auth.LoginUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/auth")
public class AuthUserViewJSON {
	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<LoginUserResponse> login(
			@RequestBody OperationRequest<LoginUserRequest> request) {
		OperationResponse<LoginUserResponse> response = new OperationResponse<LoginUserResponse>();

		try {
			// [CMP] spring controllers are singleton (as common servlet)
			// so we can't implements IView because his properties are shared
			IAuthUserView view = new AuthUserViewMock();

			view.setRequest(request.getRequest());
			new AuthUserPresenter(view, authService);
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
