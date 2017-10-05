package fr.indianacroft.wildhunt;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class testJoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_join);

        final String name = getIntent().getStringExtra("quest_name");

        TextView text = (TextView) findViewById(R.id.testText);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userid = preferences.getString("userid", "");

        text.setText(String.valueOf(userid)); // j'ai recuperé le pseudo de l'user


        // je recupere la KEY de la quête choisi
        DatabaseReference refQuestName = FirebaseDatabase.getInstance().getReference().child("Quest");

        refQuestName.orderByChild("quest_name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String key = child.getKey();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }
}
