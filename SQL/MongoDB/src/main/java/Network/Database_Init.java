package Network;


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
            mongoClient.close();
            return database;
    }

    public String insert(MongoDatabase database)
    {

        Customer cust=new Customer("Gunther","Dreibein",123);
        MongoCollection<Document> coll = database.getCollection("myTestCollection");
        JSONObject json=new JSONObject(cust);



        return json.toString();
    }

}

