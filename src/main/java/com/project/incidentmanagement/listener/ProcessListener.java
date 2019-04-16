package com.project.incidentmanagement.listener;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;


public class ProcessListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		execution.setVariable("userTaskClaimed", true);
		 Map<String,Object> request = execution.getVariables();
		if(request!=null){
		} 
		
	}

}
