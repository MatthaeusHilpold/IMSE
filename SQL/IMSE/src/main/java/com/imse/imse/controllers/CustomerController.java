package com.imse.imse.controllers;



import com.imse.imse.DataInsert.DataInsert;
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
    @RequestMapping(value = "/getAllCustomersAsObject", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Customer>> getAllCustomersAsObject(){

        ArrayList<Customer> list = new ArrayList<Customer>();
       // List<JSONObject> entities = new ArrayList<JSONObject>();
        try {
            list = DbConnection.getAllCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()!=0)
            return new ResponseEntity<ArrayList<Customer>>(list, HttpStatus.OK);
        else
            return new ResponseEntity<ArrayList<Customer>>(list, HttpStatus.NO_CONTENT);

    }

    @CrossOrigin
    @GetMapping(value = "getAllCustomers" )
    public ResponseEntity<String> getAllCustomers(){
        try {
            String result = customerService.getAllCustomers();
            return new ResponseEntity<String>(result,HttpStatus.OK);
        } catch (SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllSpouses", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Spouse>> getAllSpouses(){

        ArrayList<Spouse> list = new ArrayList<Spouse>();
        try {
            list = DbConnection.getAllSpouses();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()!=0)
            return new ResponseEntity<ArrayList<Spouse>>(list, HttpStatus.OK);
        else
            return new ResponseEntity<ArrayList<Spouse>>(list, HttpStatus.NO_CONTENT);

    }

    @CrossOrigin
    @GetMapping(value = "getCustomer/{surnmame}" )
    public ResponseEntity<String> getCustomerBySurname(@PathVariable(value = "surnmame") String surname){
        try {
            String result = customerService.findBySurname(surname);
            return new ResponseEntity<String>(result,HttpStatus.OK);
        } catch (SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @GetMapping(value = "fillWithData" )
    public ResponseEntity<String> fillTableWithData() {
        DataInsert.fillCustomerTable();
        return new ResponseEntity<String>(HttpStatus.OK);
    }







}

