package com.imse.imse.service;

import com.imse.imse.DAO.EmployeeDAO;
import com.imse.imse.EmployeeUpdatePayload;
import com.imse.imse.domain.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeService implements IEmployeeService {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    public Employee findById(String id) {
        return employeeDAO.findById(id);
    }

    @Override
    public Employee findBySurname(String surname) {
        return employeeDAO.findBySurname(surname);
    }

    @Override
    public void saveEmployee(Employee employee) throws SQLException {
      employeeDAO.saveEmployee(employee);
    }

    @Override
    public void updateEmployee(EmployeeUpdatePayload employeeUpdatePayload) throws SQLException{
      employeeDAO.updateEmployee(employeeUpdatePayload);
    }

    @Override
    public void deleteEmployeeById(int id) throws SQLException{
       employeeDAO.deleteEmployeeById(id);
    }
}
