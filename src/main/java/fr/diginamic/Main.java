package fr.diginamic;

import com.mongodb.client.MongoDatabase;

public class Main {
    public static void main(String[] args) {

        ConnexionMongoDB connexionMongoDB = new ConnexionMongoDB();
        try {
            MongoDatabase database = connexionMongoDB.getDatabase();

            GestionRessources ressources = new GestionRessources(database);
            ressources.ajouterRessource("or", 200);
            ressources.ajouterRessource("pierre", 1000);
            ressources.ajouterRessource("bois", 2000);
            ressources.afficherRessources();
            ressources.mettreAJourRessource("or", 1000);

            GestionCitoyens citoyens = new GestionCitoyens(database);
            citoyens.ajouterCitoyen("Villageois",10, "apporter nourriture");
            citoyens.afficherCitoyens();
            citoyens.mettreAJourCitoyen("Villageois", "apporter bois", "apporter pierre");
            citoyens.supprimerCitoyen("Villageois");

            GestionBatiments gestionBatiments = new GestionBatiments(database, ressources);
            gestionBatiments.construireBatiment("Ferme", 400, 300, "Production de nourriture");
            gestionBatiments.ameliorerBatiment("Ferme");
            gestionBatiments.afficherBatiments();
            gestionBatiments.supprimerBatiment("Ferme");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}