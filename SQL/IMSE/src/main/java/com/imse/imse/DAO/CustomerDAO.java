package com.imse.imse.DAO;



import com.imse.imse.config.DbConnection;
import com.imse.imse.domain.Customer;
import com.imse.imse.domain.Spouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerDAO implements ICustomerDAO {


    private static final Logger logger = LogManager.getLogger(CustomerDAO.class);

    @Override
    public Customer findById(String id) {
        return null;
    }

    @Override
    public Customer findBySurname(String surname) {
        return null;
    }

    @Override
    public void saveCustomer(Customer customer, Spouse spouse) throws SQLException {

        StringBuilder saveUserQuery = new StringBuilder();
        saveUserQuery.append("INSERT INTO Customer(CustomerName, CustomerSurname, CustomerSince, EmployeeId) VALUES ( ");
        saveUserQuery.append("'");
        saveUserQuery.append(customer.getCustomerName());
        saveUserQuery.append("',");
        saveUserQuery.append("'");
        saveUserQuery.append(customer.getCustomerSurname());
        saveUserQuery.append("', '");
        saveUserQuery.append(customer.getCustomerSince());
        saveUserQuery.append("', ");
        saveUserQuery.append(customer.getEmployeeId());
        saveUserQuery.append(")");

        if(spouse == null) {
            DbConnection.executeQuery(saveUserQuery.toString());
        } else {
            int ID = DbConnection.executeQueryWithReturn(saveUserQuery.toString());

            StringBuilder saveSpouseQuery = new StringBuilder();
            saveUserQuery.append("INSERT INTO Sposue(CustomerID, SpouseName, HaveChildren) VALUES ( ");
            saveUserQuery.append("");
            saveUserQuery.append(ID);
            saveUserQuery.append(",");
            saveUserQuery.append("'");
            saveUserQuery.append(spouse.getSpouseName());
            saveUserQuery.append("',");
            saveUserQuery.append(",");
            saveUserQuery.append(spouse.getHasChildren());
            saveUserQuery.append(")");

            DbConnection.executeQuery(saveSpouseQuery.toString());
        }
    }
//
//    @Override
//    public void updateCustomer(CustomerUpdatePayload customerUpdatePayload) throws SQLException {
//
//        StringBuilder query = new StringBuilder();
//        query.append("UPDATE Customer SET ");
//        query.append(customerUpdatePayload.getWhatToUpdate());
//        query.append(" WHERE ");
//        query.append(customerUpdatePayload.getWhereToUpdate());
//
//        DbConnection.executeQuery(query.toString());
//    }

    @Override
    public void deleteCustomerById(int id) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM Customer WHERE CustomerId = ");
        query.append(id);

        DbConnection.executeQuery(query.toString());
    }

}

