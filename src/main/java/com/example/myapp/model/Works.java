package com.example.myapp.model;

public class Works {

	private Professor professor;
	private Department department;
	private double timePercentage;
	
	public Works() {
	
	}
	
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public double getTimePercentage() {
		return timePercentage;
	}
	public void setTimePercentage(double timePercentage) {
		this.timePercentage = timePercentage;
	}	
}
