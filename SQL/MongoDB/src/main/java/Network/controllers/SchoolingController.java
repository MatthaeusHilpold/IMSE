package Network.controllers;

import Network.Database_Init;
import Network.HTMLTableMapper;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
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
    private static AtomicInteger id = new AtomicInteger(0);

    @CrossOrigin
    @PostMapping(value = "/add")
    public ResponseEntity<String> newSchooling(@RequestBody String jsonString) {
        try {

            JSONObject json = new JSONObject(jsonString);
            init=new Database_Init(mongo,name);
            db=init.getDb();
            MongoCollection<Document> coll = db.getCollection("Schooling");
            Document schooling = new Document("_id", id.incrementAndGet())
                    .append("SchoolingName", json.get("SchoolingName").toString())
                    .append("Termin",json.get("Termin").toString())
                    .append("CompanyUIDNumber",Integer.parseInt(json.get("CompanyUIDNumber").toString()));
            coll.insertOne(schooling);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") int id){

        try {
            init=new Database_Init(mongo,name);
            db=init.getDb();
            Bson filter = Filters.eq("_id", id);
            DeleteResult deleteResult = db.getCollection("Schooling").deleteOne(filter);
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

}
