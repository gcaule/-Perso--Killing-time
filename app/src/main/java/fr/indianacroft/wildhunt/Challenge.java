package fr.indianacroft.wildhunt;

/**
 * Created by apprenti on 10/4/17.
 */

public class Challenge {

    // Attributs
    private String name_challenge;
    private String difficulty_challenge;
    private String hint_challenge;

    // Constructors
    public Challenge(String name_challenge, String difficulty_challenge, String hint_challenge) {
        this.name_challenge = name_challenge;
        this.difficulty_challenge = difficulty_challenge;
        this.hint_challenge = hint_challenge;
    }

    // Getters & Setters
    public String getName_challenge() {
        return name_challenge;
    }
    public void setName_challenge(String name_challenge) {
        this.name_challenge = name_challenge;
    }
    public String getDifficulty_challenge() {
        return difficulty_challenge;
    }
    public void setDifficulty_challenge(String difficulty_challenge) {
        this.difficulty_challenge = difficulty_challenge;
    }
    public String getHint_challenge() {
        return hint_challenge;
    }
    public void setHint_challenge(String hint_challenge) {
        this.hint_challenge = hint_challenge;
    }
}
