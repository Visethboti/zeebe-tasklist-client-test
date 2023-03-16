package com.example.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonToHtml {

	public JsonToHtml() {

	}

	public String getHtml(String strJsonData) throws JSONException {

		// System.out.println(new JSONObject(strJsonData).get("components").getClass());
		return jsonToHtml(new JSONObject(strJsonData).get("components"));
		// return "Hello world";
	}

	/**
	 * convert json Data to structured Html text
	 * 
	 * @param json
	 * @return string
	 * @throws JSONException
	 */
	private String jsonToHtml(Object obj) throws JSONException {
		StringBuilder html = new StringBuilder();

		String formHead, formEnd, label, input;

		formHead = "<form action=\"/nosubmit\">";
		formEnd = " <input type=\"submit\" value=\"Submit\"> </form>";

		html.append(formHead);

		JSONArray array = (JSONArray) obj;
		System.out.println("json array length:" + array.length());
		for (int i = 0; i < array.length(); i++) {
			JSONObject component = (JSONObject) array.get(i);
			System.out.println(component.get("type"));
			// System.out.println(array.get(i));
			label = "<label>" + component.get("label") + "</label>";
			input = "<input type=\"text\">";
			html.append(label);
			html.append(input);
		}

		html.append(formEnd);

		return html.toString();
	}

	private String componentToLabel(JSONObject component) {
		String label = "";

		return label;
	}

	private String componentToInput(JSONObject component) {
		String input = "";

		return input;
	}
}
