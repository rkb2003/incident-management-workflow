package com.project.incidentmanagement.model;

import java.util.List;
import java.util.Map;

public interface TaskDao {

  Map<String, String> getTaskComments(List<String> taskIds);
  Map<String, String> getAssignee(String taskid);
  Map<String, String> getAssigneetime(String taskid);
  String getOwner();
  String getDueDate(String taskId);
  String processStatus(String taskId, boolean status);
  
}
