package org.smal2.test.presenter;

import org.smal2.presentation.presenter.ListLaboratoriesPresenter;
import org.smal2.presentation.view.IListLaboratoriesView;
import org.smal2.test.presenter.mock.ListLaboratoriesViewMock;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ListLaboratoriesPresenterTest extends ALaboratoriesPresenterTest {
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
