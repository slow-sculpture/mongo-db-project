import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.Arrays;


public class App {
    public static void main(String[] args) {


        String user = "sda";
        String databaseName = "admin";
        char[] password = "sda".toCharArray();

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoCredential credential = MongoCredential.createCredential(user, databaseName, password);

        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true)
                .codecRegistry(pojoCodecRegistry)
                .build();

        MongoClient mongoClient = new MongoClient(Arrays.asList(
                new ServerAddress("cluster0-shard-00-00-eos78.mongodb.net", 27017),
                new ServerAddress("cluster0-shard-00-01-eos78.mongodb.net", 27017),
                new ServerAddress("cluster0-shard-00-02-eos78.mongodb.net", 27017)),
                Arrays.asList(credential),
                options);

        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> coll = database.getCollection("restaurants");
        MongoCollection<Restaurant> coll2 = database.getCollection("restaurants", Restaurant.class);

        MongoCursor<Document> iterator = coll.find(eq("borough", "Poznań")).iterator();
        while (iterator.hasNext()) {
            String json = iterator.next().toJson();
            System.out.println(json);
        }
        MongoCursor<Restaurant> iterator2 = coll2.find(eq("borough", "Poznań")).iterator();
        while (iterator2.hasNext()) {
            Restaurant restaurant = iterator2.next();
            System.out.println(restaurant.getName());
        }

        Document document = new Document("borough", "Poznań")
                .append("cuisine", "Drinks")
                .append("name", "Polish wódkas")
                .append("grades", Arrays.asList(new Document("grade", "A"), new Document("score", 60)));

        coll.insertOne(document);

        mongoClient.close();
    }
}
