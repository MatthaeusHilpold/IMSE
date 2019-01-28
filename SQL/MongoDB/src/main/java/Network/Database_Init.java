package Network;


import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;


public class Database_Init {


    private MongoClient mongo;
    private String name;
    private MongoDatabase db;

    public Database_Init(MongoClient mongo,String name)
    {
        this.mongo=mongo;
        this.name=name;
        db = mongo.getDatabase(name);
    }

    public MongoDatabase getDb()
    {
        return db;
    }

    public void delete()
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

    public FindIterable<Document> getAll(String name)
    {
        MongoCollection<Document> col=db.getCollection(name);
        FindIterable<Document> docs=col.find();
        return docs;
    }

    public FindIterable<Document> findBySurname(String object,String name, String identifyier)
    {
        MongoCollection<Document> col=db.getCollection(name);
        FindIterable<Document> docs=col.find(new BasicDBObject(identifyier,object));
        return docs;
    }

    public FindIterable<Document> findById(int object,String name, String identifyier)
    {
        MongoCollection<Document> col=db.getCollection(name);
        FindIterable<Document> docs=col.find(new BasicDBObject(identifyier,object));
        return docs;
    }

}

