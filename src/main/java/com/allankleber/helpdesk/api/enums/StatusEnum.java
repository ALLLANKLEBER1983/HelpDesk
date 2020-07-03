package com.allankleber.helpdesk.api.enums;

public enum StatusEnum {
	
	New,
	Assigned,
	Resolved,
	Approved,
	Desapproved,
	Closed;
	
	private static StatusEnum getStatus(String status) 
	{
		switch (status) {
		case "New":return New;
		case "Assigned": return Assigned;
		case "Resolved":return Resolved;
		case "Approved":return Approved;
		case "Desapproved":return Desapproved;
		case "Closed":return Closed;
		default:return New;
			
		}
	}
	

}
