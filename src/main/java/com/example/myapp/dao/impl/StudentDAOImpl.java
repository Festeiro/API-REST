package com.example.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.myapp.dao.StudentDAO;
import com.example.myapp.factory.DatabaseConnection;
import com.example.myapp.model.Department;
import com.example.myapp.model.Student;

public class StudentDAOImpl implements StudentDAO{

	public StudentDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save(Student student) {
		
		String sql = "";
		Long matAconselhador = null;
		Long numDepartamento = null;
		
		if(student.getAdvisor() != null) {
			matAconselhador = student.getAdvisor().getReg_number();
		}
		
		if(student.getDepartment() != null) {
			numDepartamento = student.getDepartment().getDep_number();
		}
		
		
		if(student.getReg_number() == null) {
			sql = "INSERT INTO Estudante (Nome, Idade, TipoCurso, MatAconselhador, NumDepartamento)"
					+ " VALUES('" + student.getName() + "', " + student.getAge() + ", '" + student.getCourse() + "'"
							+ ", " + matAconselhador + ", " + numDepartamento + ")";
		}
		else {
			sql = "UPDATE Estudante"
					+ " SET Nome = '" + student.getName() + "'"
					+ " , Idade = " + student.getAge()
					+ " , TipoCurso = '" + student.getCourse() + "'"
					+ " , MatAconselhador = " + matAconselhador
					+ " , NumDepartamento = " + numDepartamento
					+ " WHERE Matricula = " + student.getReg_number();
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
		String sql = "DELETE FROM Estudante WHERE Matricula = " + id;

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
	public ArrayList<Student> listAll(){
		String sql = "SELECT * FROM Estudante";
		ArrayList<Student> students = new ArrayList<Student>();
		try(Connection connection = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstm = connection.prepareStatement(sql)){
				ResultSet rs = pstm.executeQuery();
				
				while(rs.next()) {
					Student student = new Student();
					Long reg_number = rs.getLong("Matricula");
					String name = rs.getString("Nome");
					int age = rs.getInt("Idade");
					Long matAconselhador = rs.getLong("MatAconselhador");
					Long numDepartamento = rs.getLong("NumDepartamento");
					Student advisor = listByRegNumber(matAconselhador);
					DepartmentDAOImpl departmentDAOImpl = new DepartmentDAOImpl();
					Department department = departmentDAOImpl.listByDepNumber(numDepartamento);
					
					String course = rs.getString("TipoCurso");
					student.setReg_number(reg_number);
					student.setName(name);
					student.setAge(age);
					student.setCourse(course);
					if(advisor.getReg_number() != null) {
						student.setAdvisor(advisor);						
					}
					if(department.getDep_number() != null) {
						student.setDepartment(department);
					}
					students.add(student);
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public Student listByRegNumber(Long reg_number){
		
		String sql = "SELECT * FROM Estudante WHERE matricula = " + reg_number;
		Student student = new Student();
		try(Connection connection = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstm = connection.prepareStatement(sql)){
			
				ResultSet rs = pstm.executeQuery();
				
				if(rs.next()) {
					
					String name = rs.getString("Nome");
					int age = rs.getInt("Idade");
					Long numDepartamento = rs.getLong("NumDepartamento");
					DepartmentDAOImpl departmentDAOImpl = new DepartmentDAOImpl();
					Department department = departmentDAOImpl.listByDepNumber(numDepartamento);
					String course = rs.getString("TipoCurso");
					student.setReg_number(reg_number);
					student.setName(name);
					student.setAge(age);
					student.setCourse(course);
					if(department.getDep_number() != null) {
						student.setDepartment(department);
					}
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
}
