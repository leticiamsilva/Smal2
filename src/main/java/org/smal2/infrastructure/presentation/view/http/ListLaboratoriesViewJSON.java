package org.smal2.infrastructure.presentation.view.http;

import org.smal2.common.ICommand;
import org.smal2.infrastructure.presentation.view.http.util.OperationRequest;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.ListLaboratoriesPresenter;
import org.smal2.presentation.view.IListLaboratoriesView;
import org.smal2.service.laboratory.ListLaboratoriesResponse;
import org.smal2.service.laboratory.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/laboratory")
public class ListLaboratoriesViewJSON implements IListLaboratoriesView {
	@Autowired
	private LaboratoryService laboratoryService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<ListLaboratoriesResponse> listLaboratories(
			@RequestBody OperationRequest<String> request) {
		OperationResponse<ListLaboratoriesResponse> response = new OperationResponse<ListLaboratoriesResponse>();

		// TODO [CMP] verify request.getSessionId() permission

		try {
			new ListLaboratoriesPresenter(this, laboratoryService);
			command.execute();
			response.setResponse(this.response);

		} catch (Exception ex) {
			response.setError("Error:\n" + ex.getMessage());
		}

		return response;
	}

	// IListLaboratoriesView implementation

	private ICommand command;
	private ListLaboratoriesResponse response;

	@Override
	public ICommand getCommand() {
		return command;
	}

	@Override
	public void setCommand(ICommand command) {
		this.command = command;
	}

	@Override
	public ListLaboratoriesResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(ListLaboratoriesResponse laboratories) {
		this.response = laboratories;
	}

	// end IListLaboratoriesView
}
