package com.imse.imse.DAO;



import com.imse.imse.EmployeeUpdatePayload;
import com.imse.imse.domain.Employee;

import java.sql.SQLException;

public interface IEmployeeDAO {

    String findById(int id) throws SQLException;

    String getAllEmployees() throws SQLException;

    void saveEmployee(Employee employee) throws SQLException;

    void updateEmployee(EmployeeUpdatePayload employeeUpdatePayload) throws SQLException;

    void deleteEmployeeById(int id) throws SQLException;

}
