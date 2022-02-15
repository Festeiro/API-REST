package com.example.myapp.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.dao.impl.ParticipatesDAOImpl;
import com.example.myapp.model.Participates;

@RestController
@RequestMapping(path="/participates")
public class ParticipatesController {

	ParticipatesDAOImpl participatesDAO = new ParticipatesDAOImpl();
	
	public ParticipatesController() {
	}

	@PostMapping(path="/save")
	public boolean save(@RequestBody Participates participates) {
		//TODO: check if this new user already exists
		
		participatesDAO.save(participates);
		return true;
	}
	
	@DeleteMapping(path="/delete")
	public boolean delete(@RequestParam Long projectNumber, @RequestParam Long professorRegNumber ) {
	
		participatesDAO.delete(projectNumber, professorRegNumber);
		return false;
	}
	
	@GetMapping(path="/listByProjectNumber")
	public ArrayList<Participates> listByProjectNumber(@RequestParam Long projectNumber){
		return participatesDAO.listByProjectNumber(projectNumber);
	}
}
