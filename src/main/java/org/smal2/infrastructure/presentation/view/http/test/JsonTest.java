package org.smal2.infrastructure.presentation.view.http.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/test/json")
public class JsonTest {

	@RequestMapping(value = "/string_request", method = RequestMethod.GET)
	public @ResponseBody
	String getStringRequest() {

		return "simple_string_response";
	}

	@RequestMapping(value = "/list_string_request", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getListStringRequest() {

		List<String> list = new ArrayList<String>();
		list.add("simple");
		list.add("string");
		list.add("list");
		list.add("response");

		return list;
	}

	@RequestMapping(value = "/object_request", method = RequestMethod.GET)
	public @ResponseBody
	TestObject getObjectRequest() {

		TestObject obj = new TestObject();
		obj.setText("TestObject");
		obj.getWords().add("simple");
		obj.getWords().add("string");
		obj.getWords().add("list");
		obj.getWords().add("response");
		obj.setNumber(obj.getWords().size());

		return obj;
	}
}
