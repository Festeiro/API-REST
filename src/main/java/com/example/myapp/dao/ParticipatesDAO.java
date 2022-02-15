package com.example.myapp.dao;

import java.util.ArrayList;

import com.example.myapp.model.Participates;

public interface ParticipatesDAO {

	public void save(Participates participates);
	
	public boolean delete(Long projectNumber, Long professorRegNumber);
	
	public ArrayList<Participates> listByProjectNumber(Long projectNumber);
	
}
