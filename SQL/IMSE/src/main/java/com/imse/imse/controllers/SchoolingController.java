package com.imse.imse.controllers;

import com.imse.imse.config.DbConnection;
import com.imse.imse.domain.Employee;
import com.imse.imse.domain.Schooling;
import com.imse.imse.service.SchoolingService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping(value = "/schooling")
public class SchoolingController {
    private final SchoolingService schoolingService = new SchoolingService();

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> newSchooling(@RequestBody String jsonString) {
        try {

            JSONObject json = new JSONObject(jsonString); //ein JSON objekt erstellen
            schoolingService.saveSchooling(new Schooling(
                    json.get("Schooling_Name").toString(),
                    json.get("Termin").toString(),
                    Integer.parseInt(json.get("CompanyUIDNumber").toString())
            ));
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (JSONException | SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSchooling(@PathVariable(value = "id") int id){

        try {
            schoolingService.deleteSchooling(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (SQLException exc) {
            return new ResponseEntity<String>(exc.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllSchoolingsAsObject", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Schooling>> getAllSchoolingsAsObject(){

        ArrayList<Schooling> list = new ArrayList<Schooling>();
        try {
            list = DbConnection.getAllSchoolings();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()!=0)
            return new ResponseEntity<ArrayList<Schooling>>(list, HttpStatus.OK);
        else
            return new ResponseEntity<ArrayList<Schooling>>(list, HttpStatus.NO_CONTENT);

    }
}
