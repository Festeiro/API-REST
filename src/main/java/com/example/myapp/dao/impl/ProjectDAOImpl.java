package com.example.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.example.myapp.dao.ProjectDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Professor;
import com.example.myapp.model.Project;

	public class ProjectDAOImpl implements ProjectDAO{

		ProfessorDAOImpl professorDAOImpl = new ProfessorDAOImpl();
		
		public ProjectDAOImpl() {
		}
		
		@Override
		public void save(Project project) {
			
			String sql = "";
			boolean isUpdate = exists(project);
			if(!isUpdate) {
				sql = "INSERT INTO Projeto(Numero, Financiador, DataInicio, DataFim, Orcamento, MatGerente) VALUES(" + project.getProjectNumber() + ", '" + 
						project.getFinancier() + "', '" + project.getStartDate() + "', '" + project.getEndDate() + "', " + project.getBudget() + ", " + project.getProfLeader().getReg_number() + ")";
			}
			else {
				sql = "UPDATE Projeto"
						+ " SET Numero = " + project.getProjectNumber()
						+ " , Financiador = '" + project.getFinancier() + "'"
						+ " , Orcamento = " + project.getBudget()
						+ " , DataInicio = '" + project.getStartDate() + "'"
						+ " , DataFim = '" + project.getEndDate() + "'";
			}
			
			try(Connection connection = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstm = connection.prepareStatement(sql)){
				pstm.executeUpdate();
	     	   
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		@Override
		public boolean delete(Long id) {
			String sql = "DELETE FROM projeto WHERE Numero = " + id;

			try(Connection connection = DatabaseConnection.getInstance().getConnection();
			PreparedStatement pstm = connection.prepareStatement(sql)){
			ResultSet rs = pstm.executeQuery();
			
			return rs.rowDeleted();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		public ArrayList<Project> listAll(){
			String sql = "SELECT * FROM Projeto";
			ArrayList<Project> projects = new ArrayList<>();
			try(Connection connection = DatabaseConnection.getInstance().getConnection();
					PreparedStatement pstm = connection.prepareStatement(sql)){
					ResultSet rs = pstm.executeQuery();
					
					while(rs.next()) {
						Project project = new Project();
						Long projectNumber = rs.getLong("Numero");
						String financier = rs.getString("Financiador");
						Timestamp startDate = rs.getTimestamp("DataInicio");
						Timestamp endDate = rs.getTimestamp("DataFim");
						float budget = rs.getFloat("Orcamento");
						Long profLeaderRegNumber = rs.getLong("MatGerente");
						Professor profLeader = professorDAOImpl.listByRegNumber(profLeaderRegNumber);
						project.setProjectNumber(projectNumber);
						project.setFinancier(financier);
						project.setBudget(budget);
						project.setStartDate(startDate);
						project.setEndDate(endDate);
						project.setProfLeader(profLeader);
						projects.add(project);
					}
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return projects;
		}
		
		public Project listByProjectNumber(Long projectNumber){
			
			String sql = "SELECT * FROM Projeto WHERE Numero = " + projectNumber;
			Project project = new Project();
			try(Connection connection = DatabaseConnection.getInstance().getConnection();
					PreparedStatement pstm = connection.prepareStatement(sql)){
				
					ResultSet rs = pstm.executeQuery();
					
					if(rs.next()) {
						
						String financier = rs.getString("Financiador");
						Timestamp startDate = rs.getTimestamp("DataInicio");
						Timestamp endDate = rs.getTimestamp("DataFim");
						float budget = rs.getFloat("Orcamento");
						Long profLeaderRegNumber = rs.getLong("MatGerente");
						Professor profLeader = professorDAOImpl.listByRegNumber(profLeaderRegNumber);
						project.setProjectNumber(projectNumber);
						project.setFinancier(financier);
						project.setBudget(budget);
						project.setStartDate(startDate);
						project.setEndDate(endDate);
						project.setProfLeader(profLeader);
					}
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return project;
		}
		
		private boolean exists(Project project) {
			
			String sql = "SELECT * FROM Projeto WHERE Numero = " + project.getProjectNumber();
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


}
