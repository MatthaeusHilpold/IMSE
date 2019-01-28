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
    Database_Init init;
    MongoDatabase db;
    private static AtomicInteger id = new AtomicInteger(0);

    @CrossOrigin
    @PostMapping(value = "/add")
    public ResponseEntity<String> ProceedRegister(@RequestBody String jsonString,@RequestParam String name) {
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
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") int id,@RequestParam String name){

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
    public ResponseEntity<ArrayList<Employee>> getAllEmployees(@RequestParam String name){

        ArrayList<Employee> list = new ArrayList<Employee>();
        List<JSONObject> entities = new ArrayList<JSONObject>();
        try {
            init=new Database_Init(mongo,name);
            FindIterable<Document> docs=init.getAll("Customers");

            for (Document doc  : docs) {
                JSONObject entity = new JSONObject();
                entity.put("EmployeeId", doc.get("ID"));
                entity.put("telephoneNumber",doc.get("telephoneNumber"));
                entity.put("EmployeeName", doc.get("EmployeeName"));
                entity.put("EmployeeSurname",doc.get("EmployeeSurname"));
                entity.put("CompanyUIDNUmber",doc.get("CompanyUIDNumber"));
                entity.put("baseSalary", doc.get("baseSalary"));
                entity.put("supervisorId",doc.get("supervisorId"));
                entities.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(list.size()!=0)
            return new ResponseEntity<ArrayList<Employee>>(list, HttpStatus.OK);
        else
            return new ResponseEntity<ArrayList<Employee>>(list, HttpStatus.NO_CONTENT);

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
