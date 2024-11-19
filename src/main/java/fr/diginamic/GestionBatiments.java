package fr.diginamic;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;

public class GestionBatiments {

    private MongoCollection<Document> batiments;
    private GestionRessources gestionRessources;

    public GestionBatiments(MongoDatabase database, GestionRessources gestionRessources) {
        this.batiments = database.getCollection("Batiments");
        this.gestionRessources = gestionRessources;
    }

    public void ajouterBatiment(String type, int niveau, String fonction) {
        Document nouveauBatiment = new Document("type", type).append("niveau", niveau).append("fonction", fonction);
        batiments.insertOne(nouveauBatiment);
        System.out.println("Bâtiment ajouté : " + type);
    }

    public void construireBatiment(String type, int coutBois, int coutPierre, String fonction) {
        Document batiment = batiments.find(new Document("type", type)).first();
        if (batiment!= null && gestionRessources.verifierRessource("bois", coutBois) && gestionRessources.verifierRessource("pierre", coutPierre)) {
            gestionRessources.supprimerRessource("bois", coutBois);
            gestionRessources.supprimerRessource("pierre", coutPierre);

            ajouterBatiment(type, 1, fonction);
            System.out.println("Bâtiment construit : " + type);

        } else {
            System.out.println("Pas assez de ressources pour construire le bâtiment : " + type);
        }
    }

    public void afficherBatiments() {
        JsonWriterSettings settings = JsonWriterSettings.builder()
                .indent(true)
                .build();
        System.out.println("Liste des bâtiments :");
        for (Document batiment : batiments.find()) {
            System.out.println(batiment.toJson(settings));
        }
    }

    public void mettreAJourBatiment(String type, int nouveauNiveau) {

        batiments.updateOne(new Document("type", type), new Document("$set", new Document("niveau", nouveauNiveau)));
        System.out.println("Bâtiment mis à jour : " + type + " au niveau " + nouveauNiveau);
    }

    public void ameliorerBatiment(String type) {
        Document batiment = batiments.find(new Document("type", type)).first();
        int coutBois = 100;
        int coutPierre = 50;

        if (batiment != null && gestionRessources.verifierRessource("bois", coutBois) && gestionRessources.verifierRessource("pierre", coutPierre)) {
            gestionRessources.supprimerRessource("bois", coutBois);
            gestionRessources.supprimerRessource("pierre", coutPierre);
            int niveauActuel = batiment.getInteger("niveau");
            mettreAJourBatiment(type, niveauActuel + 1);
            System.out.println("Bâtiment amélioré : " + type + " au niveau " + (niveauActuel + 1));
        } else {
            System.out.println("Ressources insuffisantes");
        }
    }

    public void supprimerBatiment(String type) {
        batiments.deleteOne(new Document("type", type));
        System.out.println("Bâtiment supprimé : " + type);
    }
}
