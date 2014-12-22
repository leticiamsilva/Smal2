package org.smal2.presentation.view;

import org.smal2.presentation.view.util.IGenericView;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.auth.LoginUserResponse;

public interface IAuthUserView extends
		IGenericView<LoginUserRequest, LoginUserResponse> {
}
