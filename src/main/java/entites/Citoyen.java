package entites;

public class Citoyen {
    private String nom;
    private String role;
    private int quantite;

    public Citoyen(String nom, String role, int quantite) {
        this.nom = nom;
        this.role = role;
        this.quantite = quantite;
    }

    public void afficherDetails() {
        System.out.println("Nom: " + nom + ", Role: " + role + ", Quantité: " + quantite);
    }

    /**
     * Getter
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     *
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     *
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter
     *
     * @param role role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Getter
     *
     * @return quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Setter
     *
     * @param quantite quantite
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Citoyen{");
        sb.append("nom='").append(nom).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", quantite=").append(quantite);
        sb.append('}');
        return sb.toString();
    }
}