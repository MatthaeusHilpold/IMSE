package com.imse.imse.config;
import com.imse.imse.DAO.EmployeeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/*  dataBase user und password musst ihr beim instalation von mySql server angeben, wenn anders dann diese
 * parameter andern. databaseURL wirds gleich sein */

public final class DbConnection {

    private static final Logger logger = LogManager.getLogger(DbConnection.class);

    private static final String  databaseURL = "jdbc:mysql://localhost:3306/InsurnaceCompany" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String user = "root";

    private static final String password = "farciarz16";

    private static Connection connection = null;

    private DbConnection() { }

    public static void establish_Connection(){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
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


    public static void executeQuery(String query) throws SQLException{
        logger.info("Query on execute: " + query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }
}
