package Network;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class Receiver {

    private static Logger log = LoggerFactory.getLogger(Receiver.class);

    MongoClient mongo;


    @CrossOrigin
    @RequestMapping(value = "/createDB", method = RequestMethod.POST,consumes="application/json")
    public ResponseEntity<String> initDB(@RequestParam String name ) {

        try{
            mongo= MongoClients.create("mongodb://localhost:27017");
            Database_Init test=new Database_Init();
            MongoDatabase db=test.init(mongo,name);
            log.info("DB created");
            return new ResponseEntity<String>("DB created",HttpStatus.OK);
        } catch (Exception e){
            log.error("Could not create DB", e);
            return new ResponseEntity<String>("Could not create new DB",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteDB", method = RequestMethod.POST,consumes="application/json")
    public ResponseEntity<String> delete(@RequestParam String name ) {

        try{
            mongo= MongoClients.create("mongodb://localhost:27017");
            Database_Init test=new Database_Init();
            test.delete(mongo,name);
            log.info("DB deleted");
            return new ResponseEntity<String>("DB deleted",HttpStatus.OK);
        } catch (Exception e){
            log.error("Could not delete DB", e);
            return new ResponseEntity<String>("Could not delete new DB",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST,consumes="application/json")
    public ResponseEntity<String> addCustomer(@RequestParam String name ) {

        try{
            mongo= MongoClients.create("mongodb://localhost:27017");
            Database_Init test=new Database_Init();
            MongoDatabase db = mongo.getDatabase(name);
            test.insert(db);
            log.info("New Customer created");
            return new ResponseEntity<String>("new Customer created",HttpStatus.OK);
        } catch (Exception e){
            log.error("Could not delete DB", e);
            return new ResponseEntity<String>("Could not create Customer",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
