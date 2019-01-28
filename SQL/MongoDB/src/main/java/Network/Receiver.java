package Network;

import Migration.Migrator;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
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
    @RequestMapping(value = "/deleteDB", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> delete(@RequestParam String name) {

        try {
            mongo = MongoClients.create("mongodb://localhost:27017");
            Database_Init db = new Database_Init(mongo, name);
            db.delete();
            log.info("DB deleted");
            return new ResponseEntity<String>("DB deleted", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Could not delete DB", e);
            return new ResponseEntity<String>("Could not delete new DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/migrateData", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> migrate() {

        mongo = MongoClients.create("mongodb://localhost:27017");
        Database_Init db = new Database_Init(mongo, "InsuranceCompanyMigrated");

        Migrator.migrateEmployees(db.getDb());
        Migrator.migrateCustomers(db.getDb());
        Migrator.migrateSchoolings(db.getDb());
        return new ResponseEntity<String>("DB migrated succesfully", HttpStatus.OK);
    }
}