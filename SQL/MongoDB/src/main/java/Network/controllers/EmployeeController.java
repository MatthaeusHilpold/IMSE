package Network.controllers;

import Network.Database_Init;
import Network.HTMLTableMapper;
import com.mongodb.BasicDBObject;
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
    private static AtomicInteger id = new AtomicInteger(50);

    @CrossOrigin
    @PostMapping(value = "/add")
    public ResponseEntity<String> ProceedRegister(@RequestBody String jsonString) {

        try {

            JSONObject json = new JSONObject(jsonString);
            init=new Database_Init(mongo,name);
            db=init.getDb();
            Employee newemployee = new Employee(json.getString("telephoneNumber"), json.getString("EmployeeName"), json.getString("EmployeeSurname"), json.getInt("baseSalary"), json.getInt("supervisorId"), json.getString("CompanyUIDNumber"));
            MongoCollection<Document> coll = db.getCollection("Employees");

            Document date = new Document()
                    .append("year", newemployee.getEmployeeSince().getYear())
                    .append("monthValue", newemployee.getEmployeeSince().getMonthValue())
                    .append("dayOfMonth", newemployee.getEmployeeSince().getDayOfMonth());
            Document person = new Document("_id", id.incrementAndGet())
                    .append("employeeId", id.get())
                    .append("telephoneNumber", newemployee.getTelephoneNumber())
                    .append("name", newemployee.getName())
                    .append("surname", newemployee.getSurname())
                    .append("companyUIDNUmber",newemployee.getCompanyUIDNumber())
                    .append("baseSalary", newemployee.getBaseSalary())
                    .append("employeeSince", date)
                    .append("supervisorId",newemployee.getSupervisorId());
            coll.insertOne(person);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") int id){

        init=new Database_Init(mongo,name);
        db=init.getDb();
        //Bson filter = Filters.eq("schooling_ID", id);
        MongoCollection<Document> coll = db.getCollection("Schoolings");
        coll.deleteOne(new Document("employeeId", id));
        return new ResponseEntity<String>(HttpStatus.OK);
    }


    @CrossOrigin
    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    public ResponseEntity<String> getAllEmployees(){
        String response=null;
        ArrayList<JSONObject> entities = new ArrayList<JSONObject>();
        try {
            init=new Database_Init(mongo,name);
            FindIterable<Document> docs=init.getAll("Employees");

            response = HTMLTableMapper.mapEmployeeToHTMLTable(docs);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!response.isEmpty())
            return new ResponseEntity<String>(response, HttpStatus.OK);
        else
            return new ResponseEntity<String>(response, HttpStatus.NO_CONTENT);

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
