package com.imse.imse.DAO;

import com.imse.imse.EmployeeUpdatePayload;
import com.imse.imse.config.DbConnection;
import com.imse.imse.domain.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 Bei jeder methode erstelle ich mit StringBuilder entsprechende query und rufe ich DbConnection.executeQuery(query) auf

 */


public class EmployeeDAO implements IEmployeeDAO {


    private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);

    @Override
    public Employee findById(String id) {
        return null;
    }

    @Override
    public Employee findBySurname(String surname) {
        return null;
    }

    @Override
    public void saveEmployee(Employee employee) throws SQLException {

        StringBuilder saveUserQuery = new StringBuilder();
        saveUserQuery.append("INSERT INTO Employee(TelephoneNumber, EmployeeName, EmployeeSurname, BaseSalary," +
                " SupervisorId, CompanyUIDNumber) VALUES( ");
        saveUserQuery.append("'");
        saveUserQuery.append(employee.getTelephoneNumber());
        saveUserQuery.append("',");
        saveUserQuery.append("'");
        saveUserQuery.append(employee.getName());
        saveUserQuery.append("',");
        saveUserQuery.append("'");
        saveUserQuery.append(employee.getSurname());
        saveUserQuery.append("',");
        saveUserQuery.append("'");
        saveUserQuery.append(employee.getBaseSalary());
        saveUserQuery.append("',");
        saveUserQuery.append("'");
        saveUserQuery.append(employee.getSupervisorId());
        saveUserQuery.append("'");
        saveUserQuery.append(",'");
        saveUserQuery.append(employee.getCompanyUIDNumber());
        saveUserQuery.append("')");

        DbConnection.executeQuery(saveUserQuery.toString());
    }

    @Override
    public void updateEmployee(EmployeeUpdatePayload employeeUpdatePayload) throws SQLException {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE Employee SET ");
        query.append(employeeUpdatePayload.getWhatToUpdate());
        query.append(" WHERE ");
        query.append(employeeUpdatePayload.getWhereToUpdate());

        DbConnection.executeQuery(query.toString());
    }

    @Override
    public void deleteEmployeeById(int id) throws SQLException {
       StringBuilder query = new StringBuilder();
       query.append("DELETE FROM Employee WHERE EmployeeId = ");
       query.append(id);

       DbConnection.executeQuery(query.toString());
    }

}
