package org.smal2.service.laboratory;

public class ListLaboratoriesResponseItem {
	private long id;
	private String name;

	public ListLaboratoriesResponseItem(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
