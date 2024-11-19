package fr.diginamic;

import com.mongodb.client.MongoDatabase;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ConnexionMongoDB connexionMongoDB = new ConnexionMongoDB();
        try {
            MongoDatabase database = connexionMongoDB.getDatabase();

            // Initialisation des gestionnaires
            GestionRessources ressources = new GestionRessources(database);
            GestionCitoyens citoyens = new GestionCitoyens(database);
            GestionBatiments batiments = new GestionBatiments(database, ressources);
            GestionMissions missions = new GestionMissions(database, ressources, citoyens);

            // Menu principal
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n--- Menu Principal ---");
                System.out.println("1. Gérer les ressources");
                System.out.println("2. Gérer les citoyens");
                System.out.println("3. Gérer les bâtiments");
                System.out.println("4. Gérer les missions");
                System.out.println("5. Quitter");
                System.out.print("Choisissez une option : ");

                int choix = scanner.nextInt();
                scanner.nextLine(); // Consommer la ligne restante

                switch (choix) {
                    case 1 -> gererRessources(ressources, scanner);
                    case 2 -> gererCitoyens(citoyens, scanner);
                    case 3 -> gererBatiments(batiments, scanner);
                    case 4 -> gererMissions(missions, scanner);
                    case 5 -> {
                        System.out.println("Merci d'avoir joué !");
                        running = false;
                    }
                    default -> System.out.println("Option invalide. Veuillez réessayer.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connexionMongoDB.fermerConnexion();
        }
    }

    // Gestion des ressources
    private static void gererRessources(GestionRessources ressources, Scanner scanner) {
        System.out.println("\n--- Gestion des Ressources ---");
        System.out.println("1. Ajouter une ressource");
        System.out.println("2. Afficher toutes les ressources");
        System.out.println("3. Retour au menu principal");
        System.out.print("Choisissez une option : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> {
                System.out.print("Entrez le type de ressource : ");
                String type = scanner.nextLine();
                System.out.print("Entrez la quantité : ");
                int quantite = scanner.nextInt();
                scanner.nextLine();
                ressources.ajouterRessource(type, quantite);
            }
            case 2 -> ressources.afficherRessources();
            case 3 -> System.out.println("Retour au menu principal.");
            default -> System.out.println("Option invalide. Veuillez réessayer.");
        }
    }

    // Gestion des citoyens
    private static void gererCitoyens(GestionCitoyens citoyens, Scanner scanner) {
        System.out.println("\n--- Gestion des Citoyens ---");
        System.out.println("1. Ajouter un citoyen");
        System.out.println("2. Afficher tous les citoyens");
        System.out.println("3. Mettre à jour un citoyen");
        System.out.println("4. Supprimer un citoyen");
        System.out.println("5. Retour au menu principal");
        System.out.print("Choisissez une option : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> {
                System.out.print("Entrez le nom du citoyen : ");
                String nom = scanner.nextLine();
                System.out.print("Entrez le rôle du citoyen : ");
                String role = scanner.nextLine();
                System.out.print("Entrez la quantité : ");
                int quantite = scanner.nextInt();
                scanner.nextLine();
                citoyens.ajouterCitoyen(nom, quantite, role);
            }
            case 2 -> citoyens.afficherCitoyens();
            case 3 -> {
                System.out.print("Entrez l'ancien rôle : ");
                String ancienRole = scanner.nextLine();
                System.out.print("Entrez le nouveau rôle : ");
                String nouveauRole = scanner.nextLine();
                System.out.print("Entrez le nom du citoyen : ");
                String nom = scanner.nextLine();
                citoyens.mettreAJourCitoyen(nom, ancienRole, nouveauRole);
            }
            case 4 -> {
                System.out.print("Entrez le nom du citoyen à supprimer : ");
                String nom = scanner.nextLine();
                citoyens.supprimerCitoyen(nom);
            }
            case 5 -> System.out.println("Retour au menu principal.");
            default -> System.out.println("Option invalide. Veuillez réessayer.");
        }
    }

    // Gestion des bâtiments
    private static void gererBatiments(GestionBatiments batiments, Scanner scanner) {
        System.out.println("\n--- Gestion des Bâtiments ---");
        System.out.println("1. Construire un bâtiment");
        System.out.println("2. Améliorer un bâtiment");
        System.out.println("3. Afficher tous les bâtiments");
        System.out.println("4. Supprimer un bâtiment");
        System.out.println("5. Retour au menu principal");
        System.out.print("Choisissez une option : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> {
                System.out.print("Entrez le type du bâtiment : ");
                String type = scanner.nextLine();
                System.out.print("Entrez le coût en bois : ");
                int bois = scanner.nextInt();
                System.out.print("Entrez le coût en pierre : ");
                int pierre = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Entrez la fonction du bâtiment : ");
                String fonction = scanner.nextLine();
                batiments.construireBatiment(type, bois, pierre, fonction);
            }
            case 2 -> {
                System.out.print("Entrez le type du bâtiment à améliorer : ");
                String type = scanner.nextLine();
                batiments.ameliorerBatiment(type);
            }
            case 3 -> batiments.afficherBatiments();
            case 4 -> {
                System.out.print("Entrez le type du bâtiment à supprimer : ");
                String type = scanner.nextLine();
                batiments.supprimerBatiment(type);
            }
            case 5 -> System.out.println("Retour au menu principal.");
            default -> System.out.println("Option invalide. Veuillez réessayer.");
        }
    }

    // Gestion des missions
    private static void gererMissions(GestionMissions missions, Scanner scanner) {
        System.out.println("\n--- Gestion des Missions ---");
        System.out.println("1. Ajouter une mission");
        System.out.println("2. Préparer une mission");
        System.out.println("3. Envoyer une mission");
        System.out.println("4. Afficher toutes les missions");
        System.out.println("5. Retour au menu principal");
        System.out.print("Choisissez une option : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> {
                System.out.print("Entrez le nom de la mission : ");
                String nom = scanner.nextLine();
                System.out.print("Entrez l'objectif : ");
                String objectif = scanner.nextLine();
                System.out.print("Entrez le statut : ");
                String statut = scanner.nextLine();
                System.out.print("Entrez la récompense en or : ");
                int recompense = scanner.nextInt();
                scanner.nextLine();
                missions.ajouterMission(nom, objectif, statut, recompense);
            }
            case 2 -> {
                System.out.print("Entrez le nom de la mission : ");
                String nom = scanner.nextLine();
                System.out.print("Entrez le nombre de soldats nécessaires : ");
                int soldats = scanner.nextInt();
                System.out.print("Entrez la quantité de bois requise : ");
                int bois = scanner.nextInt();
                System.out.print("Entrez la quantité de nourriture requise : ");
                int nourriture = scanner.nextInt();
                scanner.nextLine();
                boolean prete = missions.preparerMission(nom, soldats, bois, nourriture);
                if (prete) System.out.println("Mission prête !");
            }
            case 3 -> {
                System.out.print("Entrez le nom de la mission : ");
                String nom = scanner.nextLine();
                System.out.print("Entrez le nombre de soldats à envoyer : ");
                int soldats = scanner.nextInt();
                scanner.nextLine();
                missions.envoyerEnMission(nom, soldats);
            }
            case 4 -> missions.afficherMissions();
            case 5 -> System.out.println("Retour au menu principal.");
            default -> System.out.println("Option invalide. Veuillez réessayer.");
        }
    }
}
