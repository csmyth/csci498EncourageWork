package csci498.csmyth.encouragework;

import java.util.Date;

public class Assignment {
	private String name = "";
	private String notes = "";
	private Integer year = 2012;
	private Integer month = 1;
	private Integer day_of_month = 1;
	private Boolean complete = false;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Integer getMonth() {
		return month;
	}
	
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	public Integer getDay_of_month() {
		return day_of_month;
	}
	
	public void setDay_of_month(Integer day_of_month) {
		this.day_of_month = day_of_month;
	}
	
	public Date getDate() {
		return new Date(year, month, day_of_month);
	}
	
	public void setDate(Date date) {
		this.year = date.getYear();
		this.month = date.getMonth();
		this.day_of_month = date.getDate();
	}
	
	public Boolean getComplete() {
		return complete;
	}
	
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	
	public String toString() {
		return getName();
	}
}
