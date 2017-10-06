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
    private String mUser_name;
    private String mUser_quest;
    private String mQuest_name;
    private String mQuest_description;
    private String mLife_duration;
    private String mName_challenge;
    private String mDiff_challenge;
    private String mHint_challenge;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.homejoueur_playeractivity, container, false);

        final TextView textViewPlayerActivityHint = (TextView) rootView.findViewById(R.id.textViewPlayerActivityHint);
        // Pour recuperer la key d'un user (pour le lier a une quête)
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
        mUserId = preferences.getString("mUserid", "");
        /////////////////////////////////////////////////////////////////


        // On recupere toutes les données de l'user actuel
        DatabaseReference refUser =
                FirebaseDatabase.getInstance().getReference().child("User").child(mUserId);
        refUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                mUser_name = user.getUser_name();
                mUser_quest = user.getUser_quest();
                Toast.makeText(getContext(), mUser_name + mUser_quest, Toast.LENGTH_SHORT).show();

                // On recupere toutes les données de la quete de l'user
                DatabaseReference refUserQuest =
                        FirebaseDatabase.getInstance().getReference().child("Quest").child(mUser_quest);
                refUserQuest.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Quest questUser = dataSnapshot.getValue(Quest.class);
                        mQuest_name = questUser.getQuest_name();
                        mQuest_description = questUser.getQuest_description();
                        mLife_duration = questUser.getLife_duration();

                        final TextView playerActivityQuestName = (TextView) rootView.findViewById(R.id.playerActivityNameQuestTitle);
                        playerActivityQuestName.setText(mQuest_name);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // On recupere les données des challenges
        DatabaseReference refUserChallenge = FirebaseDatabase.getInstance().getReference().child("Challenge");
        refUserChallenge.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Challenge challenge = dsp.getValue(Challenge.class);
                    // On recupere les challenges qui correspondent a la qûete
                    if (challenge.getIdquest_challenge().equals(mUser_quest)) {
                        mName_challenge = challenge.getChallenge_name();
                        Log.d(mName_challenge, "tag");
                        mHint_challenge = challenge.getHint_challenge();
                        mDiff_challenge = challenge.getChallenge_difficulty();


                        // On change la page dynamiquement !!
                        final Button playerActivityNumChallenge = (Button) rootView.findViewById(R.id.playerActivityNumChallenge);


                        textViewPlayerActivityHint.setText(mHint_challenge);
                        playerActivityNumChallenge.setText(mName_challenge);
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button buttonHint = (Button) rootView.findViewById(R.id.buttonHomeJoueurHint);

        buttonHint.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(!isClicked){
                    isClicked = true;
                    Toast.makeText(getContext(), R.string.warning_hint, Toast.LENGTH_SHORT).show();
                }
                else if(isClicked) {
                    textViewPlayerActivityHint.setVisibility(View.VISIBLE);
                }






            }
        });



        Button buttonSendSolution = (Button) rootView.findViewById(R.id.buttonHomeJoueurSendSolution);

        buttonSendSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeJoueur_PlayerPopUp.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
}
