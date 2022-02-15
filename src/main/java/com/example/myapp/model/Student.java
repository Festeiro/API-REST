package com.example.myapp.model;

public class Student {

	private Long reg_number;

	private String name;
	private Integer age;
	private String course;
	private Department department;
	private Student advisor;

	public Student() {
	}
	
	public Student(String name, Integer age, String course, Long reg_number) {
		this.name = name;
		this.age = age;
		this.course = course;
		this.reg_number = reg_number;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getCourse() {
		return course;
	}

	public Long getReg_number() {
		return reg_number;
	}
	
	public void setReg_number(Long reg_number) {
		this.reg_number = reg_number;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Student getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Student advisor) {
		this.advisor = advisor;
	}
	
}

