package com.imse.imse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class HTMLTableMapper {

    private static final Logger logger = LogManager.getLogger(HTMLTableMapper.class);

    private HTMLTableMapper(){}

    public static String mapEmployeeToHTMLTable(ResultSet resultSet) throws SQLException {

        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table style=\"width:70%\">");
        htmlTable.append(" <tr>\n" +
                "        <th>Id</th>\n" +
                "        <th>Name</th>\n" +
                "        <th>Surname</th>\n" +
                "        <th>Salary</th>\n" +
                "        <th>tel</th>\n" +
                "        <th>SupervisorID</th>\\n\""+
                "        <th>Customers</th>\\n\""+
                "        <th>Delete</th>\\n\""+
                "    </tr>");
        while (resultSet.next()) {
            htmlTable.append("<tr>");
            htmlTable.append("<th>");
            htmlTable.append(resultSet.getString("EmployeeId"));
            htmlTable.append("</th>");
            htmlTable.append("<th>");
            htmlTable.append(resultSet.getString("EmployeeName"));
            htmlTable.append("</th> <th>");
            htmlTable.append(resultSet.getString("EmployeeSurname"));
            htmlTable.append("</th> <th>");
            htmlTable.append(resultSet.getInt("BaseSalary"));
            htmlTable.append("</th> <th>");
            htmlTable.append(resultSet.getString("TelephoneNumber"));
            htmlTable.append("</th> <th>");
            htmlTable.append(resultSet.getString("SupervisorId"));
            htmlTable.append("</th> <th>");
            htmlTable.append("<a href=\"http://localhost:63342/IMSE3/Server/static/deleteCustomer.html\">Show his customers</a>\n");
            htmlTable.append("</th> <th>");
            htmlTable.append("<a href=\"http://localhost:63342/IMSE3/Server/static/deleteCustomer.html/\">Delete</a>\n");
            htmlTable.append("</th> </tr>");
        }
        logger.info("Html table: " + htmlTable.toString());
        return htmlTable.toString();
    }

    public static String mapCustomerToHTMLTable(ResultSet resultSet) throws SQLException {
        resultSet.next();
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table style=\"width:70%\">");
        htmlTable.append(" <tr>\n" +
                "        <th>Customer ID</th>\n" +
                "        <th>Customer Name</th>\n" +
                "        <th>Customer Surname</th>\n" +
                "        <th>Customer Join Date</th>\n" +
                "        <th>SupervisorID</th>\\n\"" +
                "    </tr>");
        htmlTable.append("<tr>");
        htmlTable.append("<th>");
        htmlTable.append(resultSet.getString("CustomerId"));
        htmlTable.append("</th> <th>");
        htmlTable.append(resultSet.getString("CustomerName"));
        htmlTable.append("</th> <th>");
        htmlTable.append(resultSet.getString("CustomerSurname"));
        htmlTable.append("</th> <th>");
        htmlTable.append(resultSet.getDate("CustomerSince"));
        htmlTable.append("</th> <th>");
        htmlTable.append(resultSet.getInt("EmployeeId"));
        htmlTable.append("</th> <th>");

        logger.info("Html table: " + htmlTable.toString());
        return htmlTable.toString();
    }

    public static String mapSchoolingToHtmlTable(ResultSet resultSet) throws SQLException{
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table style=\"width:70%\">");
        htmlTable.append(" <tr>\n" +
                "        <th>Id</th>\n" +
                "        <th>Name</th>\n" +
                "        <th>Termin</th>\n" +
                "        <th>Company uid Number</th>\n" +
                "    </tr>");
        while (resultSet.next()) {
            htmlTable.append("<tr>");
            htmlTable.append("<th>");
            htmlTable.append(resultSet.getString("SchoolingId"));
            htmlTable.append("</th>");
            htmlTable.append("<th>");
            htmlTable.append(resultSet.getString("SchoolingName"));
            htmlTable.append("</th> <th>");
            htmlTable.append(resultSet.getString("Termin"));
            htmlTable.append("</th> <th>");
            htmlTable.append(resultSet.getInt("CompanyUIDNUmber"));
            htmlTable.append("</th> </tr>");
        }
        logger.info("Html table: " + htmlTable.toString());
        return htmlTable.toString();
    }

}
