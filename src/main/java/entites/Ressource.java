package entites;

public class Ressource {
    private String type;
    private int quantite;

    public Ressource(String type, int quantite) {
        this.type = type;
        this.quantite = quantite;
    }

    public void afficherDetails() {
        System.out.println("Ressource: " + type + ", Quantité: " + quantite);
    }

    /**
     * Getter
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter
     *
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
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
        final StringBuffer sb = new StringBuffer("Ressource{");
        sb.append("type='").append(type).append('\'');
        sb.append(", quantite=").append(quantite);
        sb.append('}');
        return sb.toString();
    }
}