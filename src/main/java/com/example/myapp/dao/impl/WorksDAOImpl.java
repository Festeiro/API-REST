package com.example.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.myapp.dao.WorksDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Department;
import com.example.myapp.model.Professor;
import com.example.myapp.model.Works;

public class WorksDAOImpl implements WorksDAO{

	public WorksDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private boolean exists(Works works) {
		
		String sql = "SELECT * FROM Trabalha WHERE NumDepartamento = " + works.getDepartment().getDep_number()
				+ " AND MatProfessor = " + works.getProfessor().getReg_number() ;
		boolean exists = false;
		
		try(Connection connection = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstm = connection.prepareStatement(sql)){
				ResultSet rs = pstm.executeQuery();
				
				if(rs.next()) {
					exists = true;
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exists;
	}
	
	@Override
	public void save(Works works) {
		
		String sql = "";		
		boolean isUpdate = exists(works);
		
		if(!isUpdate) {
			sql = "INSERT INTO Trabalha (MatProfessor, NumDepartamento, PercentagemTempo)"
					+ " VALUES( " + works.getProfessor().getReg_number() + ", " + works.getDepartment().getDep_number() + ", " + works.getTimePercentage() + " )";
		}
		else {
			sql = "UPDATE Trabalha"
					+ " SET PercentagemTempo = " + works.getTimePercentage()
					+ " WHERE MatProfessor = " + works.getProfessor().getReg_number()
					+ " AND NumDepartamento = " + works.getDepartment().getDep_number();
		}
		
		try(Connection connection = DatabaseConnection.getInstance().getConnection();
			PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.executeUpdate();
     	   
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean delete(Long professorRegNumber, Long departmentDepNumber) {
		String sql = "DELETE FROM Trabalha WHERE MatProfessor = " + professorRegNumber + " AND NumDepartamento = " + departmentDepNumber;

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
	public ArrayList<Works> listByProfessorRegNumber(Long professorRegNumber){
		String sql = "SELECT * FROM Trabalha WHERE MatProfessor = " + professorRegNumber;
		ArrayList<Works> workss = new ArrayList<Works>();
		try(Connection connection = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstm = connection.prepareStatement(sql)){
				ResultSet rs = pstm.executeQuery();
				
				while(rs.next()) {
					Works works = new Works();
					double timePercentage = rs.getDouble("PercentagemTempo");
					Long dep_number = rs.getLong("NumDepartamento");
					
					DepartmentDAOImpl departmentDAOImpl = new DepartmentDAOImpl();
					Department department = departmentDAOImpl.listByDepNumber(dep_number);
					
					ProfessorDAOImpl professorDAOImpl = new ProfessorDAOImpl();
					Professor professor = professorDAOImpl.listByRegNumber(professorRegNumber);
					
					works.setDepartment(department);
					works.setProfessor(professor);
					works.setTimePercentage(timePercentage);
					
					workss.add(works);
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return workss;
	}
}
