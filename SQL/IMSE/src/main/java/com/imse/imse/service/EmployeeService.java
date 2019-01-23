package com.imse.imse.service;



import com.imse.imse.DAO.EmployeeDAO;
import com.imse.imse.EmployeeUpdatePayload;
import com.imse.imse.domain.Employee;

import java.sql.SQLException;

public class EmployeeService implements IEmployeeService {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    public String findById(int id) throws SQLException {
        return employeeDAO.findById(id);
    }

    @Override
    public String getAllEmployees() throws SQLException {
        return employeeDAO.getAllEmployees();
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
