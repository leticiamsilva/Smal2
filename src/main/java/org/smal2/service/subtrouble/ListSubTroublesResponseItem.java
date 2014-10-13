package org.smal2.service.subtrouble;

public class ListSubTroublesResponseItem {
	private String name;
	private String trouble_name;

	public ListSubTroublesResponseItem(String name, String trouble_name) {
		this.name = name;
		this.trouble_name = trouble_name;
	}

	public String getName() {
		return name;
	}

	public String getTrouble_name() {
		return trouble_name;
	}
}
