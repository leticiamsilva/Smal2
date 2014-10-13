package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListSubTroublesView;
import org.smal2.service.subtrouble.SubTroubleService;

public class ListSubTroublesPresenter {

	private IListSubTroublesView view;
	private SubTroubleService service;

	public ListSubTroublesPresenter(IListSubTroublesView view,
			SubTroubleService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doListSubTroubles();
			}
		});
	}

	private void doListSubTroubles() {
		try {
			view.setResponse(service.listSubTroubles(view.getRequest()));
		} catch (Exception ex) {
			view.setError("List sub-troubles error:\n" + ex.getMessage());
		}
	}
}
