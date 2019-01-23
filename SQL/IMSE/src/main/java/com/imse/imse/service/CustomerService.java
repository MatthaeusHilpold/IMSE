package com.imse.imse.service;



import com.imse.imse.DAO.CustomerDAO;
import com.imse.imse.domain.Customer;
import com.imse.imse.domain.Spouse;

import java.sql.SQLException;


public class CustomerService implements ICustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    public Customer findById(String id) {
        return customerDAO.findById(id);
    }

    @Override
    public Customer findBySurname(String surname) {
        return customerDAO.findBySurname(surname);
    }

    @Override
    public void saveCustomer(Customer customer, Spouse spouse) throws SQLException {
        customerDAO.saveCustomer(customer, spouse);
    }

//    @Override
//    public void updateCustomer(CustomerUpdatePayload customerUpdatePayload) throws SQLException{
//        customerDAO.updateCustomer(customerUpdatePayload);
//    }

    @Override
    public void deleteCustomerById(int id) throws SQLException{
        customerDAO.deleteCustomerById(id);
    }
}
