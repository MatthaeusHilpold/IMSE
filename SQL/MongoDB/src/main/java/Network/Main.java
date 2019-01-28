package Network;


import Migration.Migrator;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    static Logger logger = LoggerFactory.getLogger(Receiver.class);

    public static void main(String[] args) {
        logger.info("Starting Server!");


        MongoClient mongo= MongoClients.create("mongodb://localhost:27017");
        Database_Init db_init = new Database_Init(mongo, "InsuranceCompanyMigrated");
        MongoDatabase db = db_init.getDb();
        Migrator.migrateCustomers(db);
        Migrator.migrateEmployees(db);
        Migrator.migrateSchoolings(db);

        SpringApplication app = new SpringApplication(Main.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
