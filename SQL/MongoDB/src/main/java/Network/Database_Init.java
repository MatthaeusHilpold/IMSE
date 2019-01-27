package Network;


import com.mongodb.client.*;
import dataClasses.Customer;
import org.bson.Document;
import org.json.JSONObject;


public class Database_Init {

    public MongoDatabase init(MongoClient mongoClient,String Name)
    {
            MongoDatabase database = mongoClient.getDatabase(Name);
            System.out.println(mongoClient.listDatabases().first());
            return database;
    }

    public void delete(MongoClient mongo,String name)
    {
        MongoIterable<String> list=mongo.listDatabaseNames();

        MongoCursor<String> cursor = list.iterator();
        while(cursor.hasNext())
        {
            String next=cursor.next();
            if(next.equals(name))
            {
                mongo.getDatabase(next).drop();
                break;
            }
        }

    }

    public String insert(MongoDatabase database)
    {

        Customer cust=new Customer("Gunther","Dreibein",123);
        MongoCollection<Document> coll = database.getCollection("TestCollection");
        JSONObject json=new JSONObject(cust);
        Document person = new Document("_id", 1)
                .append("name", "Gunther")
                .append("surname", "Dreibein")
                .append("employee", "123");
        coll.insertOne(person);


        return json.toString();
    }

}

