package com.imse.imse;

import com.imse.imse.config.DbConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImseApplication {

	public static void main(String[] args) {

		DbConnection.establish_Connection();

		SpringApplication.run(ImseApplication.class, args);
	}

}

