package com.imse.imse;

import java.sql.SQLException;

import com.imse.imse.config.DbConnection;

public class Initializer {
	
	public static void initializeDb() throws SQLException {
		
		StringBuilder companyInserter = new StringBuilder();
        companyInserter.append("INSERT INTO Company( CompanyName, City, Street) SELECT ");
		companyInserter.append("'UniWienVersicherungen', ");
		companyInserter.append("'Schottentor 1', ");
		companyInserter.append("'Wien' ");
        companyInserter.append(" WHERE NOT EXISTS (SELECT * FROM Company)");
        DbConnection.executeQuery(companyInserter.toString());
		
        StringBuilder wanekInserter = new StringBuilder();
        wanekInserter.append("INSERT INTO Employee( EmployeeName, EmployeeSurname, BaseSalary, TelephoneNumber, SupervisorId, CompanyUIDNumber) SELECT ");
        wanekInserter.append("'Helmuth', ");
        wanekInserter.append("'Wanek', ");
        wanekInserter.append("12345, ");
        wanekInserter.append("06806666666, ");
        wanekInserter.append("null, ");
        wanekInserter.append("1 ");
        wanekInserter.append(" WHERE NOT EXISTS (SELECT * FROM Employee)");
        DbConnection.executeQuery(wanekInserter.toString());
	}

}
