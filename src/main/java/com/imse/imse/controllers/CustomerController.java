package com.imse.imse.controllers;


import com.imse.imse.CustomerUpdatePayload;
import com.imse.imse.domain.Customer;
import com.imse.imse.domain.Spouse;
import com.imse.imse.service.CustomerService;
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
    "CustomerName": "Helmut ",
    "CustomerSurname": "Wanek",
    "baseSalary": "2500",
    "supervisorId": "1",
    "CompanyUIDNumber": "12345"
}
Dann erstelle ich ein JSON objekt und rufe ich den customerService auf. CustomerService ruft dann CustomerDAO (
Data Acces Obeject )auf, und dort greife ich mit JDBC auf datenbank zu.
*
*
* */

@CrossOrigin
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService = new CustomerService();


    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> ProceedRegister(@RequestBody String jsonString) {
        try {
	        JSONObject json = new JSONObject(jsonString);
	        if(!json.has("Spouse")) {
	            customerService.saveCustomer(new Customer(
	                    json.get("CustomerName").toString(),
	                    json.get("CustomerSurname").toString(),
	                    Integer.parseInt(json.get("employeeId").toString())
	            ), null);
	            return new ResponseEntity<String>(HttpStatus.CREATED);
            } else {
            	JSONObject spouse = new JSONObject(json.getJSONObject("Spouse"));
            	customerService.saveCustomer(
            	new Customer(
	                    json.get("CustomerName").toString(),
	                    json.get("CustomerSurname").toString(),
	                    Integer.parseInt(json.get("employeeId").toString())
            	), new Spouse(
	            		spouse.get("SpouseName").toString(),
	            		(byte)spouse.getInt("HasChildren")
            	));
	            return new ResponseEntity<String>(HttpStatus.CREATED);
            }
        } catch (JSONException | SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCustomer(@PathVariable(value = "id") int id){

        try {
            customerService.deleteCustomerById(id);
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
            customerService.updateCustomer(new CustomerUpdatePayload(
                    json.get("whatToUpdate").toString(),
                    json.get("whereToUpdate").toString()
            ));

            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (JSONException | SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }




}
