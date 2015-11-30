/**
 * Created by Dhaval on 11/30/2015.
 */

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.util.*;

import java.util.ArrayList;

public class Test2 {

    public static void main(String[] args) {
        try {

            MongoClient mongoClient = new MongoClient("localhost");

            List <String> databases = mongoClient.getDatabaseNames();

            for (String dbName : databases) {
                System.out.println("- Database: " + dbName);

                DB db = mongoClient.getDB(dbName);

                Set<String> collections = db.getCollectionNames();
                for (String colName : collections) {
                    System.out.println("\t + Collection: " + colName);
                }
            }

            mongoClient.close();

        } catch (Exception ex) {
            System.out.println("I M Herre..");
            ex.printStackTrace();
        }

    }

}
