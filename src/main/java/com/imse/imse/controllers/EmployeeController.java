package com.imse.imse.controllers;


import com.imse.imse.EmployeeUpdatePayload;
import com.imse.imse.domain.Employee;
import com.imse.imse.service.EmployeeService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


/*
*
* Ich schicke mit hilfe von Advanced Rest Client den String in form von JSON an den endpoint zb: beim /add
* {
    "telephoneNumber": "+4851221645",
    "EmployeeName": "Helmut ",
    "EmployeeSurname": "Wanek",
    "baseSalary": "2500",
    "supervisorId": "1",
    "CompanyUIDNumber": "12345"
}
Dann erstelle ich ein JSON objekt und rufe ich den employeeService auf. EmployeeService ruft dann EmployeeDAO (
Data Acces Obeject )auf, und dort greife ich mit JDBC auf datenbank zu.
*
*
* */

@CrossOrigin
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeService();


    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
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
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") int id){

        try {
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
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




}
