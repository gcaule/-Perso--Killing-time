package fr.indianacroft.wildhunt;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConnexionActivity extends AppCompatActivity {

    Button play;
    Button signin;
    Button login;
    EditText pseudo;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        //TOAST bouton sign in si il n'y a aucune partie en court.

        play  = (Button) findViewById(R.id.button3);

                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ConnexionActivity.this, "NO GAME",Toast.LENGTH_LONG).show();

                    }
                });

        // Click du bouton sign in permettant d'acceder à la page inscription.

        signin = (Button) findViewById(R.id.button5);

                signin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ConnexionActivity.this,InscriptionActivity.class);
                        startActivity(intent);
                    }
                });

        // Champs editText deviennent visible quand on click sur le bouton log in.

        pseudo = (EditText) findViewById(R.id.editText5);
        password = (EditText) findViewById(R.id.editText4);
        login = (Button) findViewById(R.id.button4);

                pseudo.setVisibility(View.GONE);
                password.setVisibility(View.GONE);

                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                        public void onClick(View view) {
                            pseudo.setVisibility(View.VISIBLE);
                            password.setVisibility(View.VISIBLE);
                }
            });
        }

}

