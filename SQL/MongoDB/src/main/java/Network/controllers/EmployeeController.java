package Network.controllers;

import Network.Database_Init;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import dataClasses.Employee;
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
@RequestMapping("/employee")
public class EmployeeController {

    MongoClient mongo= MongoClients.create("mongodb://localhost:27017");
    String name = "InsuranceCompanyMigrated";
    Database_Init init;
    MongoDatabase db;
    private static AtomicInteger id = new AtomicInteger(0);

    @CrossOrigin
    @PostMapping(value = "/add")
    public ResponseEntity<String> ProceedRegister(@RequestBody String jsonString) {
        try {

            JSONObject json = new JSONObject(jsonString);
            init=new Database_Init(mongo,name);
            db=init.getDb();
            MongoCollection<Document> coll = db.getCollection("Employee");
            Document person = new Document("_id", id.incrementAndGet())
                    .append("telephoneNumber", json.get("telephoneNumber").toString())
                    .append("EmployeeName", json.get("EmployeeName").toString())
                    .append("EmployeeSurname",json.get("EmployeeSurname").toString())
                    .append("CompanyUIDNUmber",json.get("CompanyUIDNumber").toString())
                    .append("baseSalary", Integer.parseInt(json.get("baseSalary").toString()))
                    .append("supervisorId",Integer.parseInt(json.get("supervisorId").toString()));
            coll.insertOne(person);
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
            DeleteResult deleteResult = db.getCollection("Employee").deleteOne(filter);
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
    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<JSONObject>> getAllEmployees(){

        ArrayList<JSONObject> entities = new ArrayList<JSONObject>();
        try {
            init=new Database_Init(mongo,name);
            FindIterable<Document> docs=init.getAll("Employees");

            for (Document doc  : docs) {
                JSONObject entity = new JSONObject();
                entity.put("EmployeeId", doc.getInteger("ID"));
                entity.put("telephoneNumber",doc.getString("telephoneNumber"));
                entity.put("name", doc.getString("name"));
                entity.put("surname",doc.getString("surname"));
                entity.put("CompanyUIDNUmber",doc.getString("CompanyUIDNumber"));
                entity.put("baseSalary", doc.getInteger("baseSalary"));
                entity.put("supervisorId",doc.getInteger("supervisorId"));
                entities.add(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(entities.size()!=0)
            return new ResponseEntity<ArrayList<JSONObject>>(entities, HttpStatus.OK);
        else
            return new ResponseEntity<ArrayList<JSONObject>>(entities, HttpStatus.NO_CONTENT);

    }

   /* @CrossOrigin
    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<String> ProceedUpdate(@RequestBody String jsonString) {
        try {

            JSONObject json = new JSONObject(jsonString);
            employeeService.updateEmployee(new EmployeeUpdatePayload(
                    json.get("whatToUpdate").toString(),
                    json.get("whereToUpdate").toString()
            ));


            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception exc ) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }*/

    /*
    @CrossOrigin
    @GetMapping(value = "get/{id}" )
    public ResponseEntity<String> getEmployee(@PathVariable(value = "id") int id){

        try {
            String result = employeeService.findById(id);
            return new ResponseEntity<String>(result,HttpStatus.OK);
        } catch (SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping(value = "getAllEmployees" )
    public ResponseEntity<String> getAllEmployees(){

        try {
            String result = employeeService.getAllEmployees();
            return new ResponseEntity<String>(result,HttpStatus.OK);
        } catch (SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }*/
}
