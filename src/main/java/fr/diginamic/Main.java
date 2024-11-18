package fr.diginamic;
import com.mongodb.client.MongoDatabase;

public class Main {
    public static void main(String[] args) {
        try {
            MongoDatabase database = ConnexionMongoDB.getDatabase("Royaume");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}