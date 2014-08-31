package org.smal2.infrastructure.viewJsonOverHTTP.test;

import java.util.ArrayList;
import java.util.List;

public class TestObject {

	private String text;
	private Integer number;
	private List<String> words;

	public TestObject() {
		words = new ArrayList<String>();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public List<String> getWords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}
}
