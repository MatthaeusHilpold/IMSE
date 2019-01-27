package Migration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dataClasses.Customer;
import dataClasses.SQLCustomer;
import dataClasses.Spouse;
import jdk.nashorn.internal.parser.JSONParser;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Migrator {

    public static ArrayList<SQLCustomer> getCustomersFromSQL() {
        final String customerurl = "http://localhost:8080/customer/getAllCustomers";

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
}
