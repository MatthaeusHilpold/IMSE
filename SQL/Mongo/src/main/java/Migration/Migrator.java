package Migration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dataClasses.Employee;
import dataClasses.SQLCustomer;
import dataClasses.Schooling;
import dataClasses.Spouse;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

public class Migrator {

    public static ArrayList<SQLCustomer> getCustomersFromSQL() {
        final String customerurl = "http://localhost:8080/customer/getAllCustomersAsObject";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SQLCustomer[]> cusresult = restTemplate.getForEntity(customerurl, SQLCustomer[].class);
        ArrayList<SQLCustomer> cuslist = new ArrayList<SQLCustomer>();
        int cuslen = cusresult.getBody().length;
        int i = 0;
        while(i < cuslen) {
            cuslist.add(cusresult.getBody()[i]);
            i++;
        }
        return cuslist;
    }

    public static ArrayList<Employee> getEmployeesFromSQL() {
        final String employeeurl = "http://localhost:8080/employee/getAllEmployeesAsObject";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> empresult = restTemplate.getForEntity(employeeurl, Employee[].class);
        ArrayList<Employee> emplist = new ArrayList<Employee>();
        int emplen = empresult.getBody().length;
        int i = 0;
        while(i < emplen) {
            emplist.add(empresult.getBody()[i]);
            i++;
        }
        return emplist;
    }

    public static ArrayList<Schooling> getSchoolingsFromSQL() {
        final String employeeurl = "http://localhost:8080/schooling/getAllSchoolingsAsObject";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Schooling[]> empresult = restTemplate.getForEntity(employeeurl, Schooling[].class);
        ArrayList<Schooling> emplist = new ArrayList<Schooling>();
        int emplen = empresult.getBody().length;
        int i = 0;
        while(i < emplen) {
            emplist.add(empresult.getBody()[i]);
            i++;
        }
        return emplist;
    }

    public static ArrayList<Spouse> getSpousesFromSQL() {
        final String spouseurl = "http://localhost:8080/customer/getAllSpouses";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Spouse[]> spouseresult = restTemplate.getForEntity(spouseurl, Spouse[].class);
        ArrayList<Spouse> spouselist = new ArrayList<Spouse>();
        int spouselen = spouseresult.getBody().length;
        int i = 0;
        while(i < spouselen) {
            spouselist.add(spouseresult.getBody()[i]);
            i++;
        }
        return spouselist;
    }

    public static void migrateCustomers(MongoDatabase database) {
        ArrayList<SQLCustomer> cuslist = Migrator.getCustomersFromSQL();
        ArrayList<Spouse> slist = Migrator.getSpousesFromSQL();
        MongoCollection<Document> coll = database.getCollection("Customers");
        for(SQLCustomer cust : cuslist) {
            ObjectMapper oMapper = new ObjectMapper();
            Map<String, Object> map = oMapper.convertValue(cust, Map.class);
            Document customer = new Document(map);
            for (Spouse s : slist) {
                if (s.getCustomerId() == cust.getCustomerId()) {
                    Map<String, Object> smap = oMapper.convertValue(s, Map.class);
                    Document spouse = new Document(smap);
                    customer.append("Spouse", spouse);
                }

            }
            coll.insertOne(customer);
        }

    }

    public static void migrateEmployees(MongoDatabase database) {
        ArrayList<Employee> employeelist = Migrator.getEmployeesFromSQL();
        MongoCollection<Document> coll = database.getCollection("Employees");
        for(Employee employee : employeelist) {
            ObjectMapper oMapper = new ObjectMapper();
            Map<String, Object> map = oMapper.convertValue(employee, Map.class);
            Document employeedoc = new Document(map);
            coll.insertOne(employeedoc);
        }

    }

    public static void migrateSchoolings(MongoDatabase database) {
        ArrayList<Schooling> schoolinglist = Migrator.getSchoolingsFromSQL();
        MongoCollection<Document> coll = database.getCollection("Schoolings");
        for(Schooling schooling : schoolinglist) {
            ObjectMapper oMapper = new ObjectMapper();
            Map<String, Object> map = oMapper.convertValue(schooling, Map.class);
            Document schoolingdoc = new Document(map);
            coll.insertOne(schoolingdoc);
        }

    }
}
