package com.imse.imse.DAO;



import com.imse.imse.EmployeeUpdatePayload;
import com.imse.imse.config.DbConnection;
import com.imse.imse.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;


public class EmployeeDAO implements IEmployeeDAO {


    private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);

    @Override
    public String findById(int id) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Employee WHERE EmployeeId = ");
        query.append(id);
        return DbConnection.executeSelectQuery(query.toString(),"Employee");
    }

    @Override
    public String findBySurnmame(String surname) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Employee WHERE EmployeeSurname = ");
        query.append("'");
        query.append(surname);
        query.append("'");
        return DbConnection.executeSelectQuery(query.toString(),"Employee");
    }

    @Override
    public String getAllEmployees() throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Employee");
        return DbConnection.executeSelectQuery(query.toString(), "Employee");
    }


    @Override
    public void saveEmployee(Employee employee) throws SQLException {

        StringBuilder saveUserQuery = new StringBuilder();
        saveUserQuery.append("INSERT INTO Employee( EmployeeName, EmployeeSurname, BaseSalary," +
                " TelephoneNumber, SupervisorId, CompanyUIDNumber) VALUES( ");
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
        saveUserQuery.append(employee.getTelephoneNumber());
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
