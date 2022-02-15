package com.example.myapp;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(MyappApplication.class, args);
		
		//test
//		UserDAOImpl userDAO = new UserDAOImpl();
//		userDAO.saveUser(22, "teste2");
	}

}
