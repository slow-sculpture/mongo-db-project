import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.util.Arrays;

public class App {
    public static void main(String[] args) {


        String user = "sda";
        String databaseName = "admin";
        char[] password = "sda".toCharArray();

        MongoCredential credential = MongoCredential.createCredential(user, databaseName, password);

        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();

        MongoClient mongoClient = new MongoClient(new ServerAddress("cluster0-shard-00-00-eos78.mongodb.net", 27017),
                Arrays.asList(credential),
                options);

        MongoDatabase database = mongoClient.getDatabase("admin");
        MongoCollection<Document> coll = database.getCollection("restaurants");

        mongoClient.close();
    }
}
