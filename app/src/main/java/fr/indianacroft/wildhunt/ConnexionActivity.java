package fr.indianacroft.wildhunt;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ConnexionActivity extends AppCompatActivity {

    FirebaseDatabase ref;
    DatabaseReference childRef;
    EditText user_name;
    EditText user_password;
    Button nardin;
    final String MyPREFERENCES = "MyPrefs" ;
    final String Name = "NameKey";
    final String Password = "PasswordKey";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        user_name = (EditText) findViewById(R.id.user_name);
        user_password = (EditText) findViewById(R.id.user_password);
        nardin = (Button) findViewById(R.id.nardin);
        ref = FirebaseDatabase.getInstance();
        childRef = ref.getReference("User");

        final SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String name = sharedpreferences.getString(Name, "");
        final String password = sharedpreferences.getString(Password, "");

        if( !name.isEmpty() && !password.isEmpty()) {
            user_name.setText(name);
            user_password.setText(password);
        }


        nardin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameContent = user_name.getText().toString();
                String passwordContent = user_password.getText().toString();
                String userId = ref.getReference("User").push().getKey();



                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, nameContent);
                editor.putString(Password, passwordContent);
                editor.commit();


                if(user_name.equals(name) && user_password.equals(password)){
                        Toast.makeText(ConnexionActivity.this, "Thanks", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ConnexionActivity.this, Tab_HomeJoueur.class);
                        startActivity(intent);
                    }else{
                    User user = new User(nameContent, passwordContent);

                    user.setUser_name(nameContent);
                    user.setUser_password(passwordContent);

                    childRef.child(userId).setValue(user);

                    Intent intent = new Intent(ConnexionActivity.this, Tab_HomeJoueur.class);
                    startActivity(intent);

                }





                /*user_name.setText("");
                user_password.setText("");*/






            }
        });
    }
}












