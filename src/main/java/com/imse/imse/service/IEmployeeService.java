package com.imse.imse.service;

import com.imse.imse.EmployeeUpdatePayload;
import com.imse.imse.domain.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IEmployeeService {

    Employee findById(String id) ;

    Employee findBySurname(String surname);

    void saveEmployee(Employee employee) throws SQLException;

    void updateEmployee(EmployeeUpdatePayload employeeUpdatePayload) throws SQLException;

    void deleteEmployeeById(int id) throws SQLException;
}
