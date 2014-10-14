package org.smal2.service.ticket;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ListTicketsResponse extends ArrayList<ListTicketsResponseItem> {

	public ListTicketsResponse(List<ListTicketsResponseItem> tickets) {
		addAll(tickets);
	}
}
