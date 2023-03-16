package com.example.simplemicroservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.SelfManagedAuthentication;
import io.camunda.tasklist.exception.TaskListException;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;

@RestController
public class simpleMicroserviceController {
	@Autowired
	private ZeebeClient zeebeClient;

	@GetMapping("/")
	public String runInstance() {

		System.out.println("It went here! runInstance!");

		final ProcessInstanceEvent event = zeebeClient.newCreateInstanceCommand()
				.bpmnProcessId("call-microservice-process").latestVersion()
				.variables(Map.of("message_content", "Hello from the Spring Boot get started")).send().join();

		return "Instance run successfully";
	}

	@GetMapping("/getTaskList")
	public String getTastkList() throws TaskListException {

		System.out.println("It went here! getTaskList!");

		SelfManagedAuthentication sma = new SelfManagedAuthentication().clientId("tasklist")
				.clientSecret("XALaRPl5qwTEItdwCMiPS62nVpKs7dL7").keycloakUrl("http://localhost:18080")
				.keycloakRealm("camunda-platform");

		System.out.println("yayyyyyyyyyyyyyyyyyyyyyyyyyyy");

		CamundaTaskListClient client = new CamundaTaskListClient.Builder().shouldReturnVariables()
				.taskListUrl("http://localhost:8082/").authentication(sma).build();

//		TaskList tasks = client.getAssigneeTasks("demo", TaskState.CREATED, null);
		// System.out.println(client.getAssigneeTasks("demo", TaskState.CREATED, 50));

		System.out.println(client.getTask("1"));

//		for (Task task : tasks) {
//			client.unclaim(task.getId());
//			System.out.println("task ID: " + task.getId());
//		}

		return "Tasklist get successfully";
	}
}
