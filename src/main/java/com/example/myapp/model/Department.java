package com.example.myapp.model;

public class Department {

	private Long dep_number;
	private String name;
	private String centralOffice;
	private Professor profLeader;
	
	public Department() {
		
	}
	
	public Department(Long dep_number, String name, String centralOffice) {
		this.dep_number = dep_number;
		this.name = name;
		this.centralOffice = centralOffice;
	}
	
	public Long getDep_number() {
		return dep_number;
	}
	public void setDep_number(Long dep_number) {
		this.dep_number = dep_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String departmentName) {
		this.name = departmentName;
	}
	public String getCentralOffice() {
		return centralOffice;
	}
	public void setCentralOffice(String centralOffice) {
		this.centralOffice = centralOffice;
	}

	public Professor getProfLeader() {
		return profLeader;
	}

	public void setProfLeader(Professor profLeader) {
		this.profLeader = profLeader;
	}
	
}
