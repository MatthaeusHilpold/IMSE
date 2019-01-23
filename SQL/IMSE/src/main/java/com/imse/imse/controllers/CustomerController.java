package com.imse.imse.controllers;



import com.imse.imse.domain.Customer;
import com.imse.imse.domain.Spouse;
import com.imse.imse.service.CustomerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@CrossOrigin
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService = new CustomerService();


    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> ProceedRegister(@RequestBody String jsonString) {
        System.out.println("Adding customer with json: \n" + jsonString);
        try {
            JSONObject json = new JSONObject(jsonString);
            System.out.println("Customer Surname: " + json.get("CustomerSurname".toString()));
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







}

