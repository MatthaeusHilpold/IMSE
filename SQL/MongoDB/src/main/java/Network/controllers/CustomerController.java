package Network.controllers;


import Network.Database_Init;
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
    private static AtomicInteger id = new AtomicInteger(0);

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> ProceedRegister(@RequestBody String jsonString, @RequestParam String name) {
        System.out.println("Adding customer with json: \n" + jsonString);
        try {
            JSONObject json = new JSONObject(jsonString);
            init=new Database_Init(mongo,name);
            db=init.getDb();
            System.out.println("Customer Surname: " + json.get("CustomerSurname".toString()));
            if(!json.has("Spouse"))
            {
                MongoCollection<Document> coll = db.getCollection("Customer");
                Document person = new Document("_id", 1)
                        .append("name", json.get("CustomerName").toString())
                        .append("surname", json.get("CustomerSurname").toString())
                        .append("employee", Integer.parseInt(json.get("employeeId").toString()));
                coll.insertOne(person);

                return new ResponseEntity<String>(HttpStatus.CREATED);
            } else {
                JSONObject spouse = new JSONObject(json.getJSONObject("Spouse"));
                MongoCollection<Document> coll = db.getCollection("Customer");
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
                return new ResponseEntity<String>(HttpStatus.CREATED);
            }
        } catch (Exception exc)
        {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCustomer(@PathVariable(value = "id") int id,@RequestParam String name){

        try {
            init=new Database_Init(mongo,name);
            db=init.getDb();
            Bson filter = Filters.eq("_id", id);
            DeleteResult deleteResult = db.getCollection("Customer").deleteOne(filter);
            int count = (int) deleteResult.getDeletedCount();
            if(count==1)
                return new ResponseEntity<String>(HttpStatus.OK);
            else
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } catch (Exception exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Customer>> getAllCustomers(@RequestParam String name){

        ArrayList<Customer> list = new ArrayList<Customer>();
        List<JSONObject> entities = new ArrayList<JSONObject>();
        try {
             init=new Database_Init(mongo,name);
            FindIterable<Document> docs=init.getAll("Customers");

             for (Document doc  : docs) {
                JSONObject entity = new JSONObject();
                entity.put("CustomerId", doc.get("ID"));
                entity.put("CustomerName", doc.getString("name"));
                entity.put("CustomerSurname", doc.getString("surname"));
                entity.put("CustomerSince", doc.getString("CustomerSince"));
                entity.put("EmployeeId", doc.getInteger("EmployeeId"));
                entities.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(list.size()!=0)
            return new ResponseEntity<ArrayList<Customer>>(list, HttpStatus.OK);
        else
            return new ResponseEntity<ArrayList<Customer>>(list, HttpStatus.NO_CONTENT);

    }

}

