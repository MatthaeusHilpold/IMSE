package Network.controllers;

import Network.Database_Init;
import Network.HTMLTableMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import dataClasses.Schooling;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@CrossOrigin
@RestController
@RequestMapping(value = "/schooling")
public class SchoolingController {
    MongoClient mongo= MongoClients.create("mongodb://localhost:27017");
    Database_Init init;
    MongoDatabase db;
    String name = "InsuranceCompanyMigrated";
    private static AtomicInteger id = new AtomicInteger(50);

    @CrossOrigin
    @PostMapping(value = "/add")
    public ResponseEntity<String> newSchooling(@RequestBody String jsonString) {
        try {

            JSONObject json = new JSONObject(jsonString);
            init=new Database_Init(mongo,name);
            db=init.getDb();
            Schooling newschooling = new Schooling(json.get("Schooling_Name").toString(), json.get("Termin").toString(), json.getInt("CompanyUIDNumber"));
            MongoCollection<Document> coll = db.getCollection("Schoolings");
            Document schooling = new Document("_id", id.incrementAndGet())
                    .append("schooling", newschooling.getSchooling())
                    .append("schooling_ID", id)
                    .append("termin", newschooling.getTermin())
                    .append("companyUIDNUmber", newschooling.getCompanyUIDNUmber());
            coll.insertOne(schooling);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteSchooling(@PathVariable(value = "id") int id){
        //try {
            init=new Database_Init(mongo,name);
            db=init.getDb();
            //Bson filter = Filters.eq("schooling_ID", id);
            BasicDBObject document = new BasicDBObject();
            document.put("schooling_ID", id);
            MongoCollection<Document> coll = db.getCollection("Schoolings");
            coll.deleteOne(new Document("schooling_ID", id));
            return new ResponseEntity<String>(HttpStatus.OK);
          /* int count = (int) deleteResult.getDeletedCount();
            if(count==1)
                return new ResponseEntity<String>(HttpStatus.OK);
            else
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } catch (Exception exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }*/

    }

    @CrossOrigin
    @RequestMapping(value = "/getAllSchoolings", method = RequestMethod.GET)
    public ResponseEntity<String> getAllSchoolings(){
        String response=null;
        ArrayList<JSONObject> entities = new ArrayList<JSONObject>();
        try {
            init=new Database_Init(mongo,name);
            FindIterable<Document> docs=init.getAll("Schoolings");

            response = HTMLTableMapper.mapSchoolingToHtmlTable(docs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!response.isEmpty())
            return new ResponseEntity<String>(response, HttpStatus.OK);
        else
            return new ResponseEntity<String>(response, HttpStatus.NO_CONTENT);

    }

    @CrossOrigin
    @GetMapping(value = "getSchooling/{id}")
    public ResponseEntity<String> getAllSchoolings(@PathVariable(value = "id") int id){
        String response=null;
        ArrayList<JSONObject> entities = new ArrayList<JSONObject>();
        try {
            init=new Database_Init(mongo,name);
            Integer sid = new Integer(id);
            FindIterable<Document> docs=init.findById((Object)sid, "Customers", "schooling_ID");

            response = HTMLTableMapper.mapSchoolingToHtmlTable(docs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!response.isEmpty())
            return new ResponseEntity<String>(response, HttpStatus.OK);
        else
            return new ResponseEntity<String>(response, HttpStatus.NO_CONTENT);

    }

}
