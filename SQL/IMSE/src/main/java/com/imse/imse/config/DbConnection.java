package com.imse.imse.config;
import com.imse.imse.HTMLTableMapper;
import com.imse.imse.domain.Customer;
import com.imse.imse.domain.Employee;
import com.imse.imse.domain.Schooling;
import com.imse.imse.domain.Spouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        else if(objectType.equalsIgnoreCase("Customer")) return HTMLTableMapper.mapCustomerToHTMLTable(rs);
        else if(objectType.equalsIgnoreCase("Schooling")) return HTMLTableMapper.mapSchoolingToHtmlTable(rs);

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

    public static ArrayList<Spouse> getAllSpouses() throws SQLException {
        ResultSet rs = executeSelectAllQuery("Select * from Spouse");
        ArrayList<Spouse> rsarray = new ArrayList<Spouse>();
        while (rs.next()) {
            Spouse newspouse = new Spouse();
            newspouse.setCustomerId(rs.getInt("CustomerID"));
            newspouse.setSpouseName(rs.getString("SpouseName"));
            newspouse.setHasChildren(rs.getByte("HaveChildren"));
            newspouse.setSpouseSince(rs.getDate("SpouseSince").toLocalDate());
            rsarray.add(newspouse);
        }

        return rsarray;
    }

    public static ArrayList<Employee> getAllEmployees() throws SQLException {
        ResultSet rs = executeSelectAllQuery("Select * from Employee");
        ArrayList<Employee> rsarray = new ArrayList<Employee>();
        while (rs.next()) {
            Employee newemployee = new Employee();
            newemployee.setName(rs.getString("EmployeeName"));
            newemployee.setSurname(rs.getString("EmployeeSurname"));
            //newemployee.setEmployeeSince(rs.getDate("EmployeeSince").toLocalDate());
            newemployee.setTelephoneNumber(rs.getString("TelephoneNumber"));
            newemployee.setBaseSalary(rs.getInt("BaseSalary"));
            newemployee.setSupervisorId(rs.getInt("SupervisorId"));
            newemployee.setEmployeeId(rs.getInt("EmployeeId"));
            newemployee.setCompanyUIDNumber(rs.getString("CompanyUIDNumber"));

            rsarray.add(newemployee);
        }

        return rsarray;
    }

    public static ArrayList<Schooling> getAllSchoolings() throws SQLException {
        ResultSet rs = executeSelectAllQuery("Select * from Schooling");
        ArrayList<Schooling> rsarray = new ArrayList<Schooling>();
        while (rs.next()) {
            Schooling newschooling = new Schooling();
            newschooling.setSchooling_Name(rs.getString("SchoolingName"));
            newschooling.setTermin(rs.getString("Termin"));
            newschooling.setSchooling_ID(rs.getInt("SchoolingId"));
            newschooling.setCompanyUIDNUmber(rs.getInt("CompanyUIDNumber"));

            rsarray.add(newschooling);
        }

        return rsarray;
    }
}
