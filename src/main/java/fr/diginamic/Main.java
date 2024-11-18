package fr.diginamic;
import entites.Citoyen;
import entites.Ressource;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {

        ConnexionMongoDB connexionMongoDB = new ConnexionMongoDB();
        try {
            MongoDatabase database = connexionMongoDB.getDatabase();

            MongoCollection<Document> Ressources = database.getCollection("ressources");

            Ressources.insertOne(new Document("type", "Pierre").append("quantite", 200));
            System.out.println("Ressource 'Pierre' ajoutée");

            System.out.println("Lecture de toutes les ressources : " + Ressources.find());

            Ressources.updateOne(
                    new Document("type", "Bois"),
                    new Document("$set", new Document("quantite", 350))
            );
            System.out.println("Mise à jour de la quantité de 'Bois'");

            Ressources.deleteOne(new Document("type", "Pierre"));
            System.out.println("Suppression de la ressource 'Pierre'");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}