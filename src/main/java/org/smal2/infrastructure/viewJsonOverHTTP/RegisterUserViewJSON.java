package org.smal2.infrastructure.viewJsonOverHTTP;

import org.smal2.common.I_Command;
import org.smal2.infrastructure.viewJsonOverHTTP.JSONResponse;
import org.smal2.presenter.RegisterUserPresenter;
import org.smal2.presenter.view.I_RegisterUserView;
import org.smal2.service.user.RegisterUserRequest;
import org.smal2.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/user")
public class RegisterUserViewJSON implements I_RegisterUserView {
	@Autowired
	private UserService userService;

	private RegisterUserRequest request;
	private I_Command command;
	private String response;

	// TODO [CMP]
	// o problema inicia com os casos de uso que definem várias operaçõe de
	// sistema,
	// ie. várias iterações entre usuário e sistema
	// até agora funciona bem porque só existe uma operação de sistema,
	// com somente um conjunto de entradas e um conjunto de saidas
	// (request-response)
	// porém os casos de uso não prevem entradas e saidas predefinidas para o
	// caso de uso inteiro
	// mas por cada operação de sistema

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public JSONResponse registerUser(@RequestBody RegisterUserRequest request) {
		try {
			new RegisterUserPresenter(this, userService);
			command.execute();

			return new JSONResponse(true, response);

		} catch (Exception ex) {
			return new JSONResponse(false, "Error:\n" + ex.getMessage());
		}
	}

	@Override
	public RegisterUserRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(RegisterUserRequest request) {
		this.request = request;
	}

	@Override
	public I_Command getRegisterUserCommand() {
		return command;
	}

	@Override
	public void setCommand(I_Command command) {
		this.command = command;
	}

	@Override
	public String getResponse() {
		return response;
	}

	@Override
	public void setResponse(String response) {
		this.response = response;
	}
}
