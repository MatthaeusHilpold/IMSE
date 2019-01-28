package Network;

import com.mongodb.client.FindIterable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import javax.print.Doc;
import javax.validation.constraints.Null;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class HTMLTableMapper {

    private static final Logger logger = LogManager.getLogger(HTMLTableMapper.class);

    private HTMLTableMapper(){}

    public static String mapEmployeeToHTMLTable(FindIterable<Document> docs) throws SQLException {

        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table style=\"width:70%\">");
        htmlTable.append(" <tr>" +
                "        <th>Id</th>" +
                "        <th>Name</th>" +
                "        <th>Surname</th>" +
                "        <th>Salary</th>" +
                "        <th>tel</th>" +
                "        <th>SupervisorID</th>\\n\""+
                "    </tr>");


        for(Document doc : docs) {
            htmlTable.append("<tr>");
            htmlTable.append("<th>");
            htmlTable.append(doc.getInteger("employeeId"));
            htmlTable.append("</th>");
            htmlTable.append("<th>");
            htmlTable.append(doc.getString("name"));
            htmlTable.append("</th> <th>");
            htmlTable.append(doc.getString("surname"));
            htmlTable.append("</th> <th>");
            htmlTable.append(doc.getInteger("baseSalary"));
            htmlTable.append("</th> <th>");
            htmlTable.append(doc.getString("telephoneNumber"));
            htmlTable.append("</th> <th>");
            htmlTable.append(doc.getInteger("supervisorId"));
            htmlTable.append("</th> </tr>");
        }
        logger.info("Html table: " + htmlTable.toString());
        return htmlTable.toString();
    }

    public static String mapCustomerToHTMLTable(FindIterable<Document> docs) throws SQLException {

        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table style=\"width:70%\">");
        htmlTable.append(" <tr>\n" +
                "        <th>Customer ID</th>\n" +
                "        <th>Customer Name</th>\n" +
                "        <th>Customer Surname</th>\n" +
                "        <th>Customer Join Date</th>\n" +
                "        <th>SupervisorID</th>\\n\"" +
                "        <th>Spouse Name </th>\\n\"" +
                "    </tr>");

        for(Document doc : docs) {
            htmlTable.append("<tr>");
            htmlTable.append("<th>");
            htmlTable.append(doc.getInteger("customerId"));
            htmlTable.append("</th> <th>");
            htmlTable.append(doc.getString("customerName"));
            htmlTable.append("</th> <th>");
            htmlTable.append(doc.getString("customerSurname"));
            htmlTable.append("</th> <th>");
            Document subdoc = (Document)doc.get("customerSince");
            htmlTable.append(subdoc.getInteger("year") + "-" + subdoc.getInteger("monthValue")+ "-" + subdoc.getInteger("dayOfMonth"));
            htmlTable.append("</th> <th>");
            htmlTable.append(doc.getInteger("employeeId"));
            htmlTable.append("</th> <th>");
            try {
                Document subdoc2 = (Document)doc.get("Spouse");
                htmlTable.append(subdoc2.getString("spouseName"));
            } catch (NullPointerException e) {
                htmlTable.append("No Spouse");
            }
            htmlTable.append("</th> </tr>");
        }
        logger.info("Html table: " + htmlTable.toString());
        return htmlTable.toString();
    }

    public static String mapSchoolingToHtmlTable(FindIterable<Document> docs) throws SQLException{
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table style=\"width:70%\">");
        htmlTable.append(" <tr>\n" +
                "        <th>Id</th>\n" +
                "        <th>Name</th>\n" +
                "        <th>Termin</th>\n" +
                "        <th>Company uid Number</th>\n" +
                "    </tr>");
        for (Document doc : docs){
            htmlTable.append("<tr>");
            htmlTable.append("<th>");
            htmlTable.append(doc.getInteger("schooling_ID"));
            htmlTable.append("</th>");
            htmlTable.append("<th>");
            htmlTable.append(doc.getString("schooling"));
            htmlTable.append("</th> <th>");
            htmlTable.append(doc.getString("termin"));
            htmlTable.append("</th> <th>");
            htmlTable.append(doc.getInteger("companyUIDNUmber"));
            htmlTable.append("</th> </tr>");
        }
        logger.info("Html table: " + htmlTable.toString());
        return htmlTable.toString();
    }

}
