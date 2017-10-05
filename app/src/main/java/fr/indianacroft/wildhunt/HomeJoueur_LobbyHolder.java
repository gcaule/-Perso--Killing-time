package fr.indianacroft.wildhunt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by wilder on 27/09/17.
 */

public class HomeJoueur_LobbyHolder extends RecyclerView.ViewHolder {

    public Button mNamePartyLobby;
    public TextView mDescriptionPartyLobby;
    public Button mDiscoverPartyLobby;
    public Button mJoinPartyLobby;
    private String mUserId;

    public HomeJoueur_LobbyHolder(final View itemView) {
        super(itemView);

        // Pour recuperer la key d'un user (pour le lier a une quête)
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
        mUserId = preferences.getString("mUserid", "");
        /////////////////////////////////////////////////////////////////


        mNamePartyLobby = (Button) itemView.findViewById(R.id.buttonLobbyName);

        mDescriptionPartyLobby = (TextView) itemView.findViewById(R.id.textViewLobbyDescription);
        mDescriptionPartyLobby.setVisibility(View.GONE);

        mJoinPartyLobby = (Button) itemView.findViewById(R.id.buttonLobbyJoin);
        mJoinPartyLobby.setVisibility(View.GONE);


        mJoinPartyLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO directement recuperer l'ID de la quete selectionnée
                // je recupere le nom de la quete selectionné
                String quest_name = mNamePartyLobby.getText().toString();
                // je recupere la KEY de la quête choisi grâce a son nom
                DatabaseReference refQuestName = FirebaseDatabase.getInstance().getReference().child("Quest");
                refQuestName.orderByChild("quest_name").equalTo(quest_name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            String questKey = child.getKey();
                            DatabaseReference refUserQuest =
                                    FirebaseDatabase.getInstance().getReference().child(mUserId);
                            refUserQuest.child("user_quest").setValue(questKey);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                Intent intent = new Intent(view.getContext(), HomeJoueur.class); //TODO envoyer directement dans un fragment avec in put extra
                view.getContext().startActivity(intent);
            }
        });


                mDiscoverPartyLobby = (Button) itemView.findViewById(R.id.buttonLobbyDetails);
        mDiscoverPartyLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemView.callOnClick();
            }
        });

        //listener set on ENTIRE ROW, you may set on individual components within a row.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });
    }

    private static HomeJoueur_LobbyHolder.ClickListener mClickListener;

    //Interface to send callbacks...
    public interface ClickListener{
        public void onItemClick(View view, int position);
    }

    public static void setOnClickListener(HomeJoueur_LobbyHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

///////// POUR RENTRER LES DONNEES DANS LE RECYCLER VIEW \\\\\\\\\\\\\\\
    //______________________________________________________\\

    // Pour mettre le nom de la party / quête dans le reclycer view
    public void setQuest_name(String quest_name) {
        mNamePartyLobby.setText(quest_name);
    }
    // pour mettre la description de la party / quête dans le recycler view
    public void setQuest_description(String quest_description) {
        mDescriptionPartyLobby.setText(quest_description);
    }
}