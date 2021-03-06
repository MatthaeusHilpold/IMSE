package com.imse.imse.controllers;



import com.imse.imse.DataInsert.DataInsert;
import com.imse.imse.EmployeeUpdatePayload;
import com.imse.imse.config.DbConnection;
import com.imse.imse.domain.Customer;
import com.imse.imse.domain.Employee;
import com.imse.imse.service.EmployeeService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;


@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeService();


    @CrossOrigin
    @PostMapping(value = "/add")
    public ResponseEntity<String> ProceedRegister(@RequestBody String jsonString) {
        try {

            JSONObject json = new JSONObject(jsonString); //ein JSON objekt erstellen
            employeeService.saveEmployee(new Employee(
                    json.get("telephoneNumber").toString(),
                    json.get("EmployeeName").toString(),
                    json.get("EmployeeSurname").toString(),
                    Integer.parseInt(json.get("baseSalary").toString()),
                    Integer.parseInt(json.get("supervisorId").toString()),
                    json.get("CompanyUIDNumber").toString()
            ));
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (JSONException | SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") int id){

        try {
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<String> ProceedUpdate(@RequestBody String jsonString) {
        try {

            JSONObject json = new JSONObject(jsonString);
            employeeService.updateEmployee(new EmployeeUpdatePayload(
                    json.get("whatToUpdate").toString(),
                    json.get("whereToUpdate").toString()
            ));

            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (JSONException | SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }

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
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllEmployeesAsObject", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Employee>> getAllEmployeesAsObject(){

        ArrayList<Employee> list = new ArrayList<Employee>();
        try {
            list = DbConnection.getAllEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()!=0)
            return new ResponseEntity<ArrayList<Employee>>(list, HttpStatus.OK);
        else
            return new ResponseEntity<ArrayList<Employee>>(list, HttpStatus.NO_CONTENT);

    }

    @CrossOrigin
    @GetMapping(value = "fillWithData" )
    public ResponseEntity<String> fillTableWithData() {
        DataInsert.fillEmployeeTable();
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "getEmployee/{surnmame}" )
    public ResponseEntity<String> getEmployeeBySurname(@PathVariable(value = "surnmame") String surname){
        try {
            String result = employeeService.findBySurname(surname);
            return new ResponseEntity<String>(result,HttpStatus.OK);
        } catch (SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
