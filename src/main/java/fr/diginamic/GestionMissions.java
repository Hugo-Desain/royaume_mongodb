package fr.diginamic;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import java.util.Random;


public class GestionMissions {

    private MongoCollection<Document> missions;
    private GestionRessources gestionRessources;
    private GestionCitoyens gestionCitoyens;
    private Random random;


    public GestionMissions(MongoDatabase database, GestionRessources gestionRessources, GestionCitoyens gestionCitoyens) {
        this.missions = database.getCollection("Missions");
        this.gestionRessources = gestionRessources;
        this.gestionCitoyens = gestionCitoyens;
        this.random = new Random();
    }

    // Ajouter une mission
    public void ajouterMission(String nom, String objectif, String statut, int recompenseOr) {
        Document nouvelleMission = new Document("nom", nom)
                .append("objectif", objectif)
                .append("statut", statut)
                .append("recompenseOr", recompenseOr);
        missions.insertOne(nouvelleMission);
        System.out.println("Mission ajoutée : " + nom);
    }

    // Afficher toutes les missions
    public void afficherMissions() {
        JsonWriterSettings settings = JsonWriterSettings.builder()
                .indent(true) // Mise en forme lisible
                .build();
        System.out.println("Liste des missions :");
        for (Document mission : missions.find()) {
            System.out.println(mission.toJson(settings));
        }
    }

    // Méthode pour préparer une mission
    public boolean preparerMission(String nomMission, int soldatsNecessaires, int boisRequis, int nourritureRequise) {
        // Vérification des ressources nécessaires
        if (gestionRessources.verifierRessource("Bois", boisRequis) &&
                gestionRessources.verifierRessource("Nourriture", nourritureRequise) &&
                gestionCitoyens.verifierCitoyens("Soldat", soldatsNecessaires)) {

            // Décrémente les ressources
            gestionRessources.supprimerRessource("Bois", boisRequis);
            gestionRessources.supprimerRessource("Nourriture", nourritureRequise);

            // Change le role des soldats
            gestionCitoyens.mettreAJourCitoyen("Villageois", "Soldat", "Soldat en mission");

            System.out.println("Mission prête : " + nomMission);
            return true;
        } else {
            System.out.println("Impossible de préparer la mission : ressources ou soldats insuffisants.");
            return false;
        }
    }

    public void envoyerEnMission(String nomMission, int soldatsNecessaires) {
        System.out.println("Envoie de la mission : " + nomMission);

        Document mission = new Document("nom", nomMission).append("soldatsEnvoyes", soldatsNecessaires).append("status", "En cours");
        missions.insertOne(mission);

        System.out.println("La mission " + nomMission + " a été envoyée avec " + soldatsNecessaires + " soldats.");
    }

    // Méthode pour calculer le gain en fonction de la mission
    public void calculerGain(String nomMission, boolean missionReussie, int soldatsEnvoyes) {
        if (missionReussie) {
            // Calcul des gains en fonction des soldats envoyés (exemple)
            int gainBois = soldatsEnvoyes * 20; // 20 unités de bois par soldat envoyé
            int gainNourriture = soldatsEnvoyes * 10; // 10 unités de nourriture par soldat envoyé

            // Mise à jour des ressources
            gestionRessources.mettreAJourRessource("Bois", gainBois);
            gestionRessources.mettreAJourRessource("Nourriture", gainNourriture);

            System.out.println("Mission réussie ! Gain : " + gainBois + " Bois, " + gainNourriture + " Nourriture.");
        } else {
            System.out.println("Mission échouée. Aucun gain obtenu.");
        }

        // Mettre à jour le statut de la mission en fonction du résultat
        missions.updateOne(new Document("nom", nomMission),
                new Document("$set", new Document("status", missionReussie ? "Réussie" : "Échouée")));
    }




}
