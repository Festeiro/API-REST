package com.example.myapp.dao;

import java.util.ArrayList;

import com.example.myapp.model.Works;

public interface WorksDAO {

	public void save(Works works);
	
	public boolean delete(Long professorRegNumber, Long departmentDepNumber);
	
	public ArrayList<Works> listByProfessorRegNumber(Long professorRegNumber);
	
}
