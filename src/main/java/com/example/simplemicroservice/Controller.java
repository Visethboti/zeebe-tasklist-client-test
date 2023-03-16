package com.example.simplemicroservice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.util.JsonToHtml;

@RestController
public class Controller {

	@GetMapping("/run")
	public String run() throws JSONException, IOException {

		String json = Files.readString(Paths.get("src/main/resources/Test-Form1.json"));

		// String json = "{\"components\": [{ \"text\": \"# Please enter your age:\"},
		// {\"text\": \"# Please enter your age:\"} ] }";

		JsonToHtml jsonToHtml = new JsonToHtml();
		String htmlSTR = jsonToHtml.getHtml(json);

		return htmlSTR;
	}

	@PostMapping("/Submit")
	public String handleSubmittedForm(@RequestBody Map<String, Object> map) {

		// System.out.println("Request Body: " + map.toString());

		return "Success!";
	}
}
