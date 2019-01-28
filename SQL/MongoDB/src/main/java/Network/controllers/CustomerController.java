package Network.controllers;


import Network.Database_Init;
import Network.HTMLTableMapper;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import dataClasses.Customer;
import dataClasses.Spouse;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@CrossOrigin
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    MongoClient mongo= MongoClients.create("mongodb://localhost:27017");
    Database_Init init;
    MongoDatabase db;
    String name = "InsuranceCompanyMigrated";
    private static AtomicInteger id = new AtomicInteger(50);

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> ProceedRegister(@RequestBody String jsonString) {
        System.out.println("Adding customer with json: \n" + jsonString);
        try {
            JSONObject json = new JSONObject(jsonString);
            init=new Database_Init(mongo,name);
            db=init.getDb();
            if(!json.has("Spouse"))
            {
                Customer newcustomer = new Customer(json.get("CustomerName").toString(), json.get("CustomerSurname").toString(), json.getInt("employeeId"));
                MongoCollection<Document> coll = db.getCollection("Customers");
                Document date = new Document()
                        .append("year", newcustomer.getCustomerSince().getYear())
                        .append("monthValue", newcustomer.getCustomerSince().getMonthValue())
                        .append("dayOfMonth", newcustomer.getCustomerSince().getDayOfMonth());
                Document person = new Document("_id", id.incrementAndGet())
                        .append("customerId", id.get())
                        .append("customerSince", date)
                        .append("customerName", newcustomer.getCustomerName())
                        .append("customerSurname", newcustomer.getCustomerSurname())
                        .append("employeeId", newcustomer.getEmployeeId());
                coll.insertOne(person);
                System.out.println("Customer Surname: " + person.getString("CustomerName"));
                return new ResponseEntity<String>(HttpStatus.CREATED);
            } else {
                /*JSONObject spouse = new JSONObject(json.getJSONObject("Spouse"));
                MongoCollection<Document> coll = db.getCollection("Customers");
                Spouse NewSpouse=new Spouse(spouse.get("SpouseName").toString(),
                        (byte)spouse.getInt("HasChildren"));
                Document addSpouse = new Document()
                                     .append("SpouseName",NewSpouse.getSpouseName())
                                     .append("HasChildren",NewSpouse.getHasChildren())
                                     .append("spouseSince",NewSpouse.getSpouseSince());
                Document person = new Document("_id", id.incrementAndGet())
                        .append("name", json.get("CustomerName").toString())
                        .append("surname", json.get("CustomerSurname").toString())
                        .append("EmployeeId", Integer.parseInt(json.get("EmployeeId").toString()))
                        .append("Spouse",addSpouse);
                coll.insertOne(person);*/
                return new ResponseEntity<String>(HttpStatus.CREATED);
            }
        } catch (Exception exc)
        {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCustomer(@PathVariable(value = "id") int id){
        init=new Database_Init(mongo,name);
        db=init.getDb();
        //Bson filter = Filters.eq("schooling_ID", id);
        MongoCollection<Document> coll = db.getCollection("Customers");
        coll.deleteOne(new Document("customerId", id));
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    public ResponseEntity<String> getAllCustomers(){
        String response=null;
        ArrayList<JSONObject> entities = new ArrayList<JSONObject>();
        try {
            init=new Database_Init(mongo,name);
            FindIterable<Document> docs=init.getAll("Customers");

            response = HTMLTableMapper.mapCustomerToHTMLTable(docs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!response.isEmpty())
            return new ResponseEntity<String>(response, HttpStatus.OK);
        else
            return new ResponseEntity<String>(response, HttpStatus.NO_CONTENT);

    }

    @CrossOrigin
    @GetMapping(value = "getCustomer/{surname}")
    public ResponseEntity<String> getAllEmployees(@PathVariable(value = "surname") String surname){
        String response=null;
        ArrayList<JSONObject> entities = new ArrayList<JSONObject>();
        try {
            init=new Database_Init(mongo,name);
            FindIterable<Document> docs=init.findById((Object) surname, "Customers", "customerSurname");

            response = HTMLTableMapper.mapCustomerToHTMLTable(docs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!response.isEmpty())
            return new ResponseEntity<String>(response, HttpStatus.OK);
        else
            return new ResponseEntity<String>(response, HttpStatus.NO_CONTENT);

    }

}

