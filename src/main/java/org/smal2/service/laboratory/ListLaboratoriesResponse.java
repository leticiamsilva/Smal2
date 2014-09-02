package org.smal2.service.laboratory;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ListLaboratoriesResponse extends
		ArrayList<ListLaboratoriesResponseItem> {

	public ListLaboratoriesResponse(
			List<ListLaboratoriesResponseItem> laboratories) {
		addAll(laboratories);
	}
}
