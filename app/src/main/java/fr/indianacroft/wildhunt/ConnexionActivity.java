package fr.indianacroft.wildhunt;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConnexionActivity extends AppCompatActivity {

    FirebaseDatabase ref;
    DatabaseReference childRef;
    final String myPreferences = "MyPrefs" ;
    final String userName = "NameKey";
    final String userPassword = "PasswordKey";

    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        //ON RECUPERE LES INFOS DE L'UTILISATEUR ENREGISTRE DANS LES SHARED PREFERENCES
//        String userName = "default"; // ca c'est des tests
//        String userPassword = "1234"; //







        final EditText editTextConnexionUserName = (EditText) findViewById(R.id.connexionUserName);
        final EditText editTextConnexionUserPassword = (EditText) findViewById(R.id.connexionUserPassword);
        Button buttonConnexionSend = (Button) findViewById(R.id.buttonConnexionSend);


        ref = FirebaseDatabase.getInstance();
        childRef = ref.getReference("User");

        final SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String sharedPrefUserName = sharedpreferences.getString(userName, "");
        final String sharedPrefUserPassword = sharedpreferences.getString(userPassword, "");

        if( !sharedPrefUserName.isEmpty() && !sharedPrefUserPassword.isEmpty()) {
            editTextConnexionUserName.setText(sharedPrefUserName);
            editTextConnexionUserPassword.setText(sharedPrefUserPassword);
        }


        buttonConnexionSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameContent = editTextConnexionUserName.getText().toString();
                String passwordContent = editTextConnexionUserPassword.getText().toString();
                String userId = ref.getReference("User").push().getKey();
                String questContent = "Pas de qûete pour l'instant";



                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(userName, nameContent);
                editor.putString(userPassword, passwordContent);
                editor.putString("mUserid", userId);
                editor.apply();


                if(editTextConnexionUserName.equals(sharedPrefUserName) && editTextConnexionUserPassword.equals(sharedPrefUserPassword)){
                        Toast.makeText(ConnexionActivity.this, "Thanks", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ConnexionActivity.this, HomeJoueur.class);
                        startActivity(intent);
                    }else{
                    User user = new User(nameContent, passwordContent, questContent);

                    user.setUser_name(nameContent);
                    user.setUser_password(passwordContent);

                    childRef.child(userId).setValue(user);

                    Intent intent = new Intent(ConnexionActivity.this, HomeJoueur.class);
                    startActivity(intent);

                }

//                // je recupere l'ID de l'user qu'on utilisera partout et qui est enregistré dans les sharedPreferences !!!
//                DatabaseReference refQuestUser = FirebaseDatabase.getInstance().getReference().child("User");
//                refQuestUser.orderByChild("user_name").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot child : dataSnapshot.getChildren()) {
//                            mUserId = child.getKey();
//                            Log.d("Userval", child.getKey());
//
//                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                            SharedPreferences.Editor editor = preferences.edit();
//                            editor.putString("mUserid", mUserId);
//                            editor.apply();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//
//                });




                /*user_name.setText("");
                user_password.setText("");*/






            }
        });
    }
}












