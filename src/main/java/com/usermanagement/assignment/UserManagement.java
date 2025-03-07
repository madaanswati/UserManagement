package com.usermanagement.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @created 04/03/2025 - 15:54
 * @project assignment
 * @author Swati Madaan
 * Application trigger
 */
@SpringBootApplication
public class UserManagement {

	/**
	 * @param args
	 * Start point for User Management application
	 * A specific input format based on the sample given as been designed & used here
	 * If it always has to be in flat file format only as given
	 * in the sample. Then, we may add a process in between which converts
	 * the input data from flat file format to the input format required by this API
	 *
	 * User Authentication module may be added as per the requirements of the project
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserManagement.class, args);
	}

}
