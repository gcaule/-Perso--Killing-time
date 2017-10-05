package fr.indianacroft.wildhunt;

/**
 * Created by wilder on 27/09/17.
 */

public class BDD {

    private String quest_name;
    private String quest_description;
    private int id_party;

    // Constructor
    public BDD() {
        // Needed for Firebase
    }


    public BDD(String questname, String quest_description, int id_party) {
        this.quest_name = questname;
        this.quest_description = quest_description;
        this.id_party = id_party;
    }

    // GEtter & Setter
    public int getId_party() {
        return id_party;
    }

    public void setId_party(int id_party) {
        this.id_party = id_party;
    }

    public String getQuest_name() {
        return quest_name;
    }

    public void setQuest_name(String quest_name) {
        this.quest_name = quest_name;
    }

    public String getQuest_description() {
        return quest_description;
    }

    public void setQuest_description(String quest_description) {
        this.quest_description = quest_description;
    }







}
