import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

import java.util.Arrays;


public class App {
    public static void main(String[] args) {


        String user = "sda";
        String databaseName = "admin";
        char[] password = "sda".toCharArray();

        MongoCredential credential = MongoCredential.createCredential(user, databaseName, password);

        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();

        MongoClient mongoClient = new MongoClient(Arrays.asList(
                new ServerAddress("cluster0-shard-00-00-eos78.mongodb.net", 27017),
                new ServerAddress("cluster0-shard-00-01-eos78.mongodb.net", 27017),
                new ServerAddress("cluster0-shard-00-02-eos78.mongodb.net", 27017)),
                Arrays.asList(credential),
                options);

        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> coll = database.getCollection("restaurants");

        MongoCursor<Document> iterator = coll.find(eq("borough", "Poznań")).iterator();
        while (iterator.hasNext()) {
            String json = iterator.next().toJson();
            System.out.println(json);
        }

        Document document = new Document("borough", "Poznań")
                .append("cuisine", "Drinks")
                .append("name", "Polskie Napoje")
                .append("grades", Arrays.asList(new Document("grade", "A"), new Document("score", 60)));

        coll.insertOne(document);

        mongoClient.close();
    }
}
