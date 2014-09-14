package org.smal2.test.presenter;

import org.smal2.presentation.presenter.ListLaboratoriesPresenter;
import org.smal2.presentation.view.IListLaboratoriesView;
import org.smal2.test.presenter.mock.ListLaboratoriesViewMock;
import org.smal2.test.service.ALaboratoryServiceTest;
import org.junit.Assert;
import org.junit.Test;

public class ListLaboratoriesPresenterTest extends ALaboratoryServiceTest {
	@Test
	public void listMustReturnAllLaboratories() {
		// Arrange
		IListLaboratoriesView view = new ListLaboratoriesViewMock();

		// Act
		new ListLaboratoriesPresenter(view, laboratoryService);

		// Assert
		Assert.assertEquals(laboratoryRepository.listAll().size(), view
				.getResponse().size());
	}
}
