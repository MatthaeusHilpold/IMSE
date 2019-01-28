package com.imse.imse.service;



import com.imse.imse.EmployeeUpdatePayload;
import com.imse.imse.domain.Employee;

import java.sql.SQLException;

public interface IEmployeeService {

    String findById(int id) throws SQLException;

    String findBySurname(String surname) throws SQLException;

    String getAllEmployees() throws SQLException;

    void saveEmployee(Employee employee) throws SQLException;

    void updateEmployee(EmployeeUpdatePayload employeeUpdatePayload) throws SQLException;

    void deleteEmployeeById(int id) throws SQLException;
}
