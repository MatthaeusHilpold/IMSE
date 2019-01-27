package com.imse.imse.config;
import com.imse.imse.DAO.EmployeeDAO;
import com.imse.imse.HTMLTableMapper;
import com.imse.imse.domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

/*  dataBase user und password musst ihr beim instalation von mySql server angeben, wenn anders dann diese
 * parameter andern. databaseURL wirds gleich sein */

public final class DbConnection {

    private static final Logger logger = LogManager.getLogger(DbConnection.class);

    private static final String databaseURL = "jdbc:mysql://localhost:3306/InsurnaceCompany" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    private static final String user = "root";

    private static final String password = "NeuesPassword";

    private static Connection connection = null;

    private DbConnection() {
    }

    public static void establish_Connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(databaseURL, user, password);
            logger.info(" DB Connection established");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void executeQuery(String query) throws SQLException {
        logger.info("Query on execute: " + query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    public static String executeSelectQuery(String query, String objectType) throws SQLException{
        logger.info("Query on execute" + query);
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if(objectType.equalsIgnoreCase("Employee")) return HTMLTableMapper.mapEmployeeToHTMLTable(rs);


        return rs.toString();
    }

    public static int executeQueryWithReturn(String query) throws SQLException{
        logger.info("Query on execute: " + query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = statement.getGeneratedKeys();
        int ID = 0;
        ID = rs.getInt(1);
        logger.info("ID: " + ID);
        statement.close();
        return ID;
    }

    private static ResultSet executeSelectAllQuery(String query) throws SQLException{
        logger.info("Query on execute: " + query);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public static ArrayList<Customer> getAllCustomers() throws SQLException {
        ResultSet rs = executeSelectAllQuery("Select * from Customer");
        ArrayList<Customer> rsarray = new ArrayList<Customer>();
        while (rs.next()) {
            Customer newcustomer = new Customer();
            newcustomer.setCustomerName(rs.getString("CustomerName"));
            newcustomer.setCustomerSurname(rs.getString("CustomerSurname"));
            newcustomer.setCustomerSince(rs.getDate("CustomerSince").toLocalDate());
            newcustomer.setEmployeeId(rs.getInt("EmployeeId"));
            newcustomer.setCustomerId(rs.getInt("CustomerId"));

            rsarray.add(newcustomer);
        }

        return rsarray;
    }
}
