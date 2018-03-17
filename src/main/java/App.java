import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;


import java.util.Arrays;

public class App {
    public static void main(String[] args) {


        String user = "sda";
        String database = "admin";
        char[] password = "sda".toCharArray();

        MongoCredential credential = MongoCredential.createCredential(user, database, password);

        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();

        MongoClient mongoClient = new MongoClient(new ServerAddress("cluster0-shard-00-00-eos78.mongodb.net", 27017),
                Arrays.asList(credential),
                options);
    }
}
