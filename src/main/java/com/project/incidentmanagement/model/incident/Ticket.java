package com.project.incidentmanagement.model.incident;

import java.util.Date;

public class Ticket {

	private String owner;

	private String Dept;
	private priority priority;
	private Date Date_opened;
	private Date Date_closed;
	private String approver;
	private String reason;
	public Ticket() {
		}
	private String assignee;
	
	public Ticket(String dept, com.project.incidentmanagement.model.incident.priority priority, Date date_opened) {
		super();
		this.Dept = dept;
		this.priority = priority;
		this.Date_opened = date_opened;
	}
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDept() {
		return Dept;
	}
	public void setDept(String dept) {
		Dept = dept;
	}
	public priority getPriority() {
		return priority;
	}
	public void setPriority(priority priority) {
		this.priority = priority;
	}
	public Date getDate_opened() {
		return Date_opened;
	}
	public void setDate_opened(Date date_opened) {
		Date_opened = date_opened;
	}
	public Date getDate_closed() {
		return Date_closed;
	}
	public void setDate_closed(Date date_closed) {
		Date_closed = date_closed;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	
}
