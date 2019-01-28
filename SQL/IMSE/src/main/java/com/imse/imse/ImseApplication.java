package com.imse.imse;

import com.imse.imse.DataInsert.DataInsert;
import com.imse.imse.config.DbConnection;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImseApplication {

	public static void main(String[] args) {

		DbConnection.establish_Connection();


		DataInsert.addCompany();
		try {
			Initializer.initializeDb();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SpringApplication.run(ImseApplication.class, args);

	}

}

