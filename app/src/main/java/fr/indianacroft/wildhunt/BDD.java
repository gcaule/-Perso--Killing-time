package fr.indianacroft.wildhunt;

/**
 * Created by wilder on 27/09/17.
 */

public class BDD {

    private String Nom;
    private String theme;
    private int id_party;

    // Constructor
    public BDD() {
        // Needed for Firebase
    }


    public BDD(String nom, String theme, int id_party) {
        this.Nom = nom;
        this.theme = theme;
        this.id_party = id_party;
    }

    // GEtter & Setter
    public int getId_party() {
        return id_party;
    }

    public void setId_party(int id_party) {
        this.id_party = id_party;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        this.Nom = nom;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }







}
