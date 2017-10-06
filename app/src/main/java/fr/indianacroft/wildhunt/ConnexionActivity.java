package fr.indianacroft.wildhunt;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConnexionActivity extends AppCompatActivity {

    Button buttonConnexionPlay;
    Button buttonConnexionSignIn;
    Button buttonConnexionLogin;
    EditText editTextConnexionPseudo;
    EditText editTextConnexionPassword;
    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        //ON RECUPERE LES INFOS DE L'UTILISATEUR ENREGISTRE DANS LES SHARED PREFERENCES
        String userName = "default"; // ca c'est des tests
        String userPassword = "1234"; //




        // je recupere l'ID de l'user qu'on utilisera partout et qui est enregistré dans les sharedPreferences !!!
        DatabaseReference refQuestUser = FirebaseDatabase.getInstance().getReference().child("User");
        refQuestUser.orderByChild("user_name").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    mUserId = child.getKey();
                    Log.d("Userval", child.getKey());

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("mUserid", mUserId);
                    editor.apply();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


        //TOAST bouton sign in si il n'y a aucune partie en cours.

        buttonConnexionPlay = (Button) findViewById(R.id.buttonConnexionPlay);

        buttonConnexionPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnexionActivity.this, HomeJoueur.class);
                startActivity(intent);

            }
        });

        // Click du bouton sign in permettant d'acceder à la page inscription.

        buttonConnexionSignIn = (Button) findViewById(R.id.buttonConnexionSignIn);

        buttonConnexionSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConnexionActivity.this,InscriptionActivity.class);
                startActivity(intent);
            }
        });

        // Champs editText deviennent visible quand on click sur le bouton log in.

        editTextConnexionPseudo = (EditText) findViewById(R.id.editTextConnexionPseudo);
        editTextConnexionPassword = (EditText) findViewById(R.id.editTextConnexionPassword);
        buttonConnexionLogin = (Button) findViewById(R.id.buttonConnexionLogIn);

        editTextConnexionPseudo.setVisibility(View.GONE);
        editTextConnexionPassword.setVisibility(View.GONE);

        buttonConnexionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextConnexionPassword.getVisibility() == View.VISIBLE){
                    editTextConnexionPassword.setVisibility(View.GONE);
                    editTextConnexionPseudo.setVisibility(View.GONE);
                }else {
                    editTextConnexionPseudo.setVisibility(View.VISIBLE);
                    editTextConnexionPassword.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}

