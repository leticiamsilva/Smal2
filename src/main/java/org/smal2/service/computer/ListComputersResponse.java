package org.smal2.service.computer;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ListComputersResponse extends ArrayList<ListComputersResponseItem> {

	public ListComputersResponse(List<ListComputersResponseItem> computers) {
		addAll(computers);
	}
}
