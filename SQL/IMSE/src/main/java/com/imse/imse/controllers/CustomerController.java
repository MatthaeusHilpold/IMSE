package com.imse.imse.controllers;



import com.imse.imse.config.DbConnection;
import com.imse.imse.domain.Customer;
import com.imse.imse.domain.Spouse;
import com.imse.imse.service.CustomerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    @CrossOrigin
    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Customer>> getAllCustomers(){

        ArrayList<Customer> list = new ArrayList<Customer>();
       // List<JSONObject> entities = new ArrayList<JSONObject>();
        try {
            list = DbConnection.getAllCustomers();
           /* for (Customer c : list) {
                JSONObject entity = new JSONObject();
                entity.put("CustomerId", c.getCustomerId());
                entity.put("CustomerName", c.getCustomerName());
                entity.put("CustomerSurname", c.getCustomerSurname());
                entity.put("CustomerSince", c.getCustomerSince());
                entity.put("EmployeeId", c.getEmployeeId());
                entities.add(entity);
            } */
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()!=0)
            return new ResponseEntity<ArrayList<Customer>>(list, HttpStatus.OK);
        else
            return new ResponseEntity<ArrayList<Customer>>(list, HttpStatus.NO_CONTENT);

    }







}

