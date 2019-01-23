package com.imse.imse;

import com.imse.imse.config.DbConnection;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImseApplication {

	public static void main(String[] args) {

		DbConnection.establish_Connection();
		try {
			Initializer.initializeDb();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SpringApplication.run(ImseApplication.class, args);

	}

}

