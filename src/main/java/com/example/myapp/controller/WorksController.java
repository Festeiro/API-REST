package com.example.myapp.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.dao.impl.WorksDAOImpl;
import com.example.myapp.model.Works;

@RestController
@RequestMapping(path="/works")
public class WorksController {

	WorksDAOImpl worksDAO = new WorksDAOImpl();
	
	public WorksController() {
	}

	@PostMapping(path="/save")
	public boolean save(@RequestBody Works works) {
		//TODO: check if this new user already exists
		
		worksDAO.save(works);
		return true;
	}
	
	@DeleteMapping(path="/delete")
	public boolean delete(@RequestParam Long professorRegNumber, @RequestParam Long departmentDepNumber ) {
	
		worksDAO.delete(professorRegNumber, departmentDepNumber);
		return false;
	}
	
	@GetMapping(path="/listByProfessorRegNumber")
	public ArrayList<Works> listByProfessorRegNumber(@RequestParam Long professorRegNumber){
		return worksDAO.listByProfessorRegNumber(professorRegNumber);
	}
}
