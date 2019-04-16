package com.project.incidentmanagement.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
//import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
//import com.uprr.app.bpm.worklist.common.exception.BPMException;
//import org.uprr.bpel.common.TaskDao;

//@Repository("taskDao")
public class TaskDaoImpl implements TaskDao {

  @Autowired
  private ProcessEngine processEngine;
  
  

  private static final String GET_COMMENTS =
      "select TASK_ID_ as taskId,  listagg(to_char"
          + "(USER_ID_ || ' : '  || MESSAGE_), ' , ') within group(order by TIME_ desc) comments "
          + "from   ACT_HI_COMMENT " + "where TYPE_ = 'comment' and TASK_ID_ in (:taskIds) " + "group by TASK_ID_";

  @Autowired
  private NamedParameterJdbcTemplate workflowNamedTemplate;

  //private static CommentRowMapper commentRowMapper = new CommentRowMapper();

  public final ProcessEngine getProcessEngine() {
    return processEngine;
  }

  public final void setProcessEngine(final ProcessEngine processEngine) {
    this.processEngine = processEngine;
  }

 /* public TaskDaoImpl() {
    super(TaskDaoImpl.class);
  }
*/
  @Override
  public final Map<String, String> getTaskComments(final List<String> taskIds) {
    if (CollectionUtils.isEmpty(taskIds)) {
      return null;
    }
    Map<String, List<String>> namedParameters = Maps.newHashMap();
    namedParameters.put("taskIds", taskIds);
    List<Comment> commentList = null;
   // try {
    //  commentList = getWorkflowNamedTemplate().query(GET_COMMENTS, namedParameters, commentRowMapper);
   // } 
      /*catch (DataAccessException e) {
      getLogger().error(
          "An error occurred getting comments for taskIds " + taskIds.toString() + " "
              + ExceptionUtils.getStackTrace(e));
      throw new BPMException(e);
    }
    getLogger().debug("The comment count is " + commentList.size());*/

    Map<String, String> comments = Maps.newLinkedHashMap();
    for (Comment comment : commentList) {
      comments.put(comment.getTaskId(), comment.getComments());
    }
    return comments;
  }

  public final NamedParameterJdbcTemplate getWorkflowNamedTemplate() {
    return workflowNamedTemplate;
  }

/*  protected static class CommentRowMapper implements ParameterizedRowMapper<Comment> {
    public final Comment mapRow(final ResultSet rs, final int rowNum) throws SQLException {
      return new Comment(rs.getString("taskId"), rs.getString("comments"));
    }
  }
 */

  protected static class Comment {

    private String startDate;
    
    private String owner;
    
    private Boolean approval;
    
    public Boolean getApproval() {
      return approval;
    }


    public void setApproval(Boolean approval) {
      this.approval = approval;
    }

    private Map<String, String> assigneetime;//tracking(String taskId)

    private String status;

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    private String taskId;

    private String comments;
    
    private String timeDuration;
    
    private String assignee;
    
  
    public String getAssignee() {
      return assignee;
    }


    public void setAssignee(String assignee) {
      this.assignee = assignee;
    }


    public String getTimeDuration() {
      return timeDuration;
    }


    public void setTimeDuration(String timeDuration) {
      this.timeDuration = timeDuration;
    }


    public Comment(final String taskId, final String comments) {
      this.taskId = taskId;
      this.comments = comments;
    }
    
    public Map<String, String> getAssigneetime(String taskid) {
      return assigneetime;
    }

    public List<Map<String, String>> getAssigneetimelist(String taskid) {
      
      List<Map<String, String>> assigneeTimeList = new ArrayList<Map<String, String>>();
      for(Map<String, String> singleassigneeTime: assigneeTimeList) {
        singleassigneeTime = this.getAssigneetime(taskid); 
      assigneeTimeList.add(singleassigneeTime);
      }
      return assigneeTimeList;
    }

    public void setAssigneetime(String assignee, String time) {
      Map<String, String> assigneetime = new HashMap();
      assigneetime.put(assignee, time);
      this.assigneetime = assigneetime;
      
    }

    public String getOwner() {
      return owner;
    }

    public void setOwner(String owner) {
      this.owner = owner;
    }

    public String getstartDate( String taskId) {
      return startDate;
    }

    public void setStartDate(String StartDate) {
      this.startDate = StartDate;
    }

    public final String getTaskId() {
      return taskId;
    }

    public final void setTaskId(final String taskId) {
      this.taskId = taskId;
    }

    public final String getComments() {
      return comments;
    }

    public final void setComments(final String comments) {
      this.comments = comments;
    }
  } 

/*  @Override
  public Map<String, String> getAssignee(String taskid) {
     return assignee;
  }*/

  public String checkStatus(boolean status) {
    String process;
    if (status) {
      process = "End";
    } else {
      process = "Continue";
    }
    return process;
  }
  @Override
  public String processStatus(String taskId, boolean status) {
    String result;
      result = checkStatus(status);
    if (result.equals("End")) {
      processEngine.getTaskService().complete(taskId); 
    } else if(result.equals("Continue")) {
      //taskids moved to que..
      processEngine.getTaskService().setAssignee(taskId, null);
      //processEngine.getTaskService().setVariableLocal(taskId, "assignee", null);
    }
    
    
    return result;
  }

@Override
public Map<String, String> getAssignee(String taskid) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Map<String, String> getAssigneetime(String taskid) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getOwner() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getDueDate(String taskId) {
	// TODO Auto-generated method stub
	return null;
}
    
  //}

}
