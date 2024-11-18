package fr.diginamic;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnexionMongoDB {

    public static MongoDatabase getDatabase(String databaseName) {
        try {
            String connectionString = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(connectionString);
            System.out.println("Connexion réussie à la base de données : " + databaseName);
            return mongoClient.getDatabase(databaseName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la connexion à MongoDB");
        }
    }
}
