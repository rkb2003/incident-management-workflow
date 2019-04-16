package com.project.incidentmanagement.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.incidentmanagement.model.Person;
import com.project.incidentmanagement.model.incident.Ticket;
import com.project.incidentmanagement.repo.PersonRepository;

@Service
//@Transactional
public class MyService {

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	//@Autowired
	private PersonRepository personRepository;
	
	public String InvokeBusinessProcess() {
		Ticket ticket = new Ticket();

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("Ticket", ticket);

		runtimeService.startProcessInstanceByKey("IncidentManagement", variables);

		return processInfo();
	}
	

	public String startProcess(String assignee) {
		Person person = personRepository.findByName(assignee);

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("person", person);

		runtimeService.startProcessInstanceByKey("simpleProcess", variables);

		return processInfo();
	}
	
	public boolean claimTask(String taskId, String assignee) {
		taskService.claim(taskId, assignee);
		return true;
	}
	
	public boolean approve(String taskId) {
				return true;
	}

	public List<Task> getTasks(String assignee) {
		return taskService.createTaskQuery().taskAssignee(assignee).list();
	}
	
	public void completeTask(String taskId) {
		taskService.complete(taskId);
	}

	public void createPersons() {
		if (personRepository.findAll().size() == 0) {

			personRepository.save(new Person("David Browser", new Date(01/01/1928)));
		
			personRepository.save(new Person("Tom Browser", new Date(01/01/1938)));
			personRepository.save(new Person("Sony Browser", new Date(01/01/1948)));
		}
	}

	private String processInfo() {
		List<Task> tasks = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();
		
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("Number of process definitions : "
				+ repositoryService.createProcessDefinitionQuery().count() + "--> Tasks >> ");

		for (Task task : tasks) {
			stringBuilder
					.append(task + " | Assignee: " + task.getAssignee() + " | Description: " + task.getDescription());
		}

		return stringBuilder.toString();
	}
}