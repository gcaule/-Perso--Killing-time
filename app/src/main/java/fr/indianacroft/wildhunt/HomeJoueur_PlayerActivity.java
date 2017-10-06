package fr.indianacroft.wildhunt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeJoueur_PlayerActivity extends Fragment {

    Button butNewChallenge;
    Button butNewChallenge2;
    ImageView imageViewCancel;
    ImageView imageViewCancel2;
    private String mUserId;
    private String mQuestKey;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View rootView = inflater.inflate(R.layout.homejoueur_playeractivity, container, false);

        final TextView playerActivityQuestName = (TextView) rootView.findViewById(R.id.playerActivityNameQuestTitle);

        // Pour recuperer la key d'un user (pour le lier a une quête)
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
        mUserId = preferences.getString("mUserid", "");
        /////////////////////////////////////////////////////////////////


        // On recupere la qûete dans laquel il est
        // je recupere la KEY de la quête choisi grâce a son nom
        DatabaseReference refUserQuest =
                FirebaseDatabase.getInstance().getReference().child("User").child(mUserId);
        refUserQuest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d(mQuestKey,"mQuestKey");




        Button buttonSendSolution = (Button) rootView.findViewById(R.id.buttonHomeJoueurSendSolution);

        buttonSendSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getActivity(), HomeJoueur_PlayerPopUp.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
}
