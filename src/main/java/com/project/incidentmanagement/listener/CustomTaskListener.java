package com.project.incidentmanagement.listener;

import javax.management.NotificationBroadcaster;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomTaskListener implements TaskListener {

	
	@Override
	public void notify(DelegateTask task) {
		System.out.println("Task State : " + task.getEventName());
		 performAction(task, task.getEventName());
	}

	public void performAction(DelegateTask task, String eventName) {
		if (TaskListener.EVENTNAME_CREATE.equals(eventName)) {
			task.setVariableLocal("State", "assigned");
			System.out.println("Task is created");


		} else if (TaskListener.EVENTNAME_ASSIGNMENT.equals(eventName)) {
			task.getExecution().setVariable("userTaskClaimed", false);
			System.out.println("Task is assigned to " + task.getAssignee());


		} else if (TaskListener.EVENTNAME_COMPLETE.equals(eventName)) {
			System.out.println("Ess task is Completed");


		}
	}

	private String buildEmailMessage() {
		StringBuilder builder = new StringBuilder("<html> <body>");
		builder.append("<br>");
		builder.append("<br>For more information, visit the ...");
		builder.append("Activiti Worklist Application </a> </body></html>");
		return builder.toString();
	}

	
}
