package org.smal2.infrastructure.presentation.view.http;

import org.smal2.common.ICommand;
import org.smal2.infrastructure.presentation.view.http.util.OperationRequest;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.RegisterLaboratoryPresenter;
import org.smal2.presentation.view.IRegisterLaboratoryView;
import org.smal2.service.laboratory.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/laboratory")
public class RegisterLaboratoryViewJSON implements IRegisterLaboratoryView {
	@Autowired
	private LaboratoryService laboratoryService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<String> registerLaboratory(
			@RequestBody OperationRequest<String> request) {
		OperationResponse<String> response = new OperationResponse<String>();

		// TODO [CMP] verify request.getSessionId() permission

		try {
			this.request = request.getRequest();
			new RegisterLaboratoryPresenter(this, laboratoryService);
			command.execute();
			response.setResponse(this.response);

		} catch (Exception ex) {
			response.setError("Error:\n" + ex.getMessage());
		}

		return response;
	}

	// IRegisterLaboratoryView implementation

	private String request;
	private ICommand command;
	private String response;

	@Override
	public String getRequest() {
		return request;
	}

	@Override
	public void setRequest(String request) {
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

	// end IRegisterLaboratoryView
}
