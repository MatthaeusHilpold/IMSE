package com.imse.imse.service;


import com.imse.imse.domain.Customer;
import com.imse.imse.domain.Spouse;

import java.sql.SQLException;

public interface ICustomerService {

    Customer findById(String id) ;

    String findBySurname(String surname) throws SQLException;

    void saveCustomer(Customer customer, Spouse spouse) throws SQLException;

    //void updateCustomer(CustomerUpdatePayload customerUpdatePayload) throws SQLException;

    void deleteCustomerById(int id) throws SQLException;

    String getAllCustomers() throws SQLException;
}
