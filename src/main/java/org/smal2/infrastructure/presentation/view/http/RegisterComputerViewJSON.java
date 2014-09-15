package org.smal2.infrastructure.presentation.view.http;

import org.smal2.common.ICommand;
import org.smal2.infrastructure.presentation.view.http.util.JSONResponse;
import org.smal2.presentation.presenter.RegisterComputerPresenter;
import org.smal2.presentation.view.IRegisterComputerView;
import org.smal2.service.computer.RegisterComputerRequest;
import org.smal2.service.computer.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/computer")
public class RegisterComputerViewJSON implements IRegisterComputerView {
	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public JSONResponse registerComputer(
			@RequestBody RegisterComputerRequest request) {
		try {
			this.request = request;
			new RegisterComputerPresenter(this, computerService);
			command.execute();

			return new JSONResponse(true, response);

		} catch (Exception ex) {
			return new JSONResponse(false, "Error:\n" + ex.getMessage());
		}
	}

	// IRegisterComputerView implementation

	private RegisterComputerRequest request;
	private ICommand command;
	private String response;

	@Override
	public RegisterComputerRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(RegisterComputerRequest request) {
		this.request = request;
	}

	@Override
	public ICommand getCommand() {
		return command;
	}

	@Override
	public void setCommand(ICommand command) {
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

	// end IRegisterComputerView
}
