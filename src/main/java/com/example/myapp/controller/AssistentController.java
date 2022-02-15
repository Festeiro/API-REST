package com.example.myapp.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.dao.impl.AssistentDAOImpl;
import com.example.myapp.dao.impl.ProfessorDAOImpl;
import com.example.myapp.model.Assistent;
@RestController
@RequestMapping(path="/assist")
public class AssistentController {

	AssistentDAOImpl assistDAO = new AssistentDAOImpl();
	ProfessorDAOImpl profDAO = new ProfessorDAOImpl();
	
	public AssistentController() {
	}

	@PostMapping(path="/save")
	public boolean save(@RequestBody Assistent assistent) {
		assistent.setProfessor(assistent.getProject().getProfLeader());
		assistDAO.save(assistent);
		return true;
	}
	
	@DeleteMapping(path="/delete")
	public boolean delete(@RequestParam Long studentRegNumber) {
		assistDAO.delete(studentRegNumber);
		return false;
	}
	
	@GetMapping(path="/listAssistents")
	public ArrayList<Assistent> listByStudentRegNumber(@RequestParam Long studentRegNumber){
		return assistDAO.listByStudentRegNumber(studentRegNumber);
	}
}
