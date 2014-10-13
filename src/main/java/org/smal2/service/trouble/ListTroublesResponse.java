package org.smal2.service.trouble;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ListTroublesResponse extends ArrayList<ListTroublesResponseItem> {

	public ListTroublesResponse(List<ListTroublesResponseItem> troubles) {
		addAll(troubles);
	}
}
