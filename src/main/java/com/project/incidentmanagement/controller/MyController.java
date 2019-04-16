package com.project.incidentmanagement.controller;

import java.util.List;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.incidentmanagement.service.MyService;

import io.swagger.annotations.Api;


@RestController
@Api(tags = "my-controller")
public class MyController {

	@Autowired
	private MyService myService;
	
	@RequestMapping(value = "/invokeProcess")
	public String InvokeBusinessProcess() {
		return myService.InvokeBusinessProcess();
		
	}

	@RequestMapping(value = "/process")
	public String startProcessInstance(@RequestParam String assignee) {
		return myService.startProcess(assignee);
	}
	
	//notify
	
	
	//assign or claim
	@RequestMapping(value = "/claimtask/{assignee}")
	public boolean claimTask(@RequestParam String id, @PathVariable("assignee") String assignee) {
		return myService.claimTask(id, assignee);
		
	}
	
	//approve
	

	@RequestMapping(value = "/tasks/{assignee}")
	public String getTasks(@PathVariable("assignee") String assignee) {
		List<Task> tasks = myService.getTasks(assignee);
		return tasks.toString();
	}

	@RequestMapping(value = "/completetask")
	public String completeTask(@RequestParam String id) {
		myService.completeTask(id);
		return "Task with id " + id + " has been completed!";
	}

}
