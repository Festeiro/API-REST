package com.example.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.myapp.dao.ParticipatesDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Participates;
import com.example.myapp.model.Professor;
import com.example.myapp.model.Project;

public class ParticipatesDAOImpl implements ParticipatesDAO{

	public ParticipatesDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(Participates participates) {
		
		String sql = "INSERT INTO Participa (NumProjeto, MatProfessor)"
					+ " VALUES( " + participates.getProject().getProjectNumber() + ", " + participates.getProfessor().getReg_number() + " )";
		
		try(Connection connection = DatabaseConnection.getInstance().getConnection();
			PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.executeUpdate();
     	   
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean delete(Long projectNumber, Long professorRegNumber) {
		String sql = "DELETE FROM Participa WHERE NumProjeto = " + projectNumber + " AND MatProfessor = " + professorRegNumber;

		try(Connection connection = DatabaseConnection.getInstance().getConnection();
		PreparedStatement pstm = connection.prepareStatement(sql)){
		ResultSet rs =	pstm.executeQuery();
		
		return rs.rowDeleted();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
		
	@Override
	public ArrayList<Participates> listByProjectNumber(Long projectNumber){
		String sql = "SELECT * FROM Participa WHERE NumProjeto = " + projectNumber;
		ArrayList<Participates> participatess = new ArrayList<Participates>();
		try(Connection connection = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstm = connection.prepareStatement(sql)){
				ResultSet rs = pstm.executeQuery();
				
				while(rs.next()) {
					Participates participates = new Participates();
					Long professorRegNumber = rs.getLong("MatProfessor");
					
					ProjectDAOImpl projectDAOImpl = new ProjectDAOImpl();
					Project project = projectDAOImpl.listByProjectNumber(projectNumber);
					
					ProfessorDAOImpl professorDAOImpl = new ProfessorDAOImpl();
					Professor professor = professorDAOImpl.listByRegNumber(professorRegNumber);
					
					participates.setProject(project);
					participates.setProfessor(professor);
					
					participatess.add(participates);
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participatess;
	}
}
