package com.example.simplemicroservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = "classpath:call-microservice-process.bpmn")
public class SimpleMicroserviceApplication {

	@Autowired
	private ZeebeClient client;

	public static void main(String[] args) {
		SpringApplication.run(SimpleMicroserviceApplication.class, args);
	}

	@JobWorker(type = "call-microservice", autoComplete = true)
	public Map<String, Object> orchestrateService(final ActivatedJob job) {
		System.out.println("Task is being fetched");

		// Execute any business code I want

		HashMap<String, Object> variables = new HashMap<>();
		variables.put("message", "Hello World, yayyyyy !!!!!");
		return variables;

	}

}
