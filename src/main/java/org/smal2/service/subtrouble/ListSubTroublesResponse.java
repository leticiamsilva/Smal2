package org.smal2.service.subtrouble;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ListSubTroublesResponse extends
		ArrayList<ListSubTroublesResponseItem> {

	public ListSubTroublesResponse(List<ListSubTroublesResponseItem> subTroubles) {
		addAll(subTroubles);
	}
}
