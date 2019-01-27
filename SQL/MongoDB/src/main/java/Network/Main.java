package Network;


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
        Database_Init test=new Database_Init();
        MongoDatabase db=test.init(mongo,"abc");
        test.insert(db);
        test.delete(mongo,"abc");
        SpringApplication app = new SpringApplication(Main.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }
}