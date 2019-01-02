package com.imse.imse.DAO;

import com.imse.imse.EmployeeUpdatePayload;
import com.imse.imse.domain.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IEmployeeDAO {

    Employee findById(String id);

    Employee findBySurname(String surname);

    void saveEmployee(Employee employee) throws SQLException;

    void updateEmployee(EmployeeUpdatePayload employeeUpdatePayload) throws SQLException;

    void deleteEmployeeById(int id) throws SQLException;

}
