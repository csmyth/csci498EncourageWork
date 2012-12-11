package csci498.csmyth.encouragework;

import java.util.Date;

public class Assignment {
	private String name = "";
	private Date due_date = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	
}
