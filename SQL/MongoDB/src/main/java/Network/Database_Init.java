package Network;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dataClasses.Customer;
import org.bson.Document;
import org.json.JSONObject;


public class Database_Init {

    public MongoDatabase init(MongoClient mongoClient,String Name)
    {
            MongoDatabase database = mongoClient.getDatabase(Name);
            System.out.println(mongoClient.listDatabases().first());
           // mongoClient.close();
            return database;
    }

    public String insert(MongoDatabase database)
    {

        Customer cust=new Customer("Gunther","Dreibein",123);
        MongoCollection<Document> coll = database.getCollection("myTestCollection");
        JSONObject json=new JSONObject(cust);
        Document person = new Document("_id", 1)
                .append("name", "Gunther")
                .append("surname", "Dreibein")
                .append("employee", "123");
        coll.insertOne(person);


        return json.toString();
    }

}

