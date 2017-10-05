package fr.indianacroft.wildhunt;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wilder on 27/09/17.
 */

public class HomeJoueur_LobbyHolder extends RecyclerView.ViewHolder {


    public Button mNamePartyLobby;
    public TextView mDescriptionPartyLobby;
    public Button mDiscoverPartyLobby;
    public Button mJoinPartyLobby;

    public HomeJoueur_LobbyHolder(final View itemView) {
        super(itemView);
        mNamePartyLobby = (Button) itemView.findViewById(R.id.buttonLobbyName);

        mDescriptionPartyLobby = (TextView) itemView.findViewById(R.id.textViewLobbyDescription);
        mDescriptionPartyLobby.setVisibility(View.GONE);

        mJoinPartyLobby = (Button) itemView.findViewById(R.id.buttonLobbyJoin);
        mJoinPartyLobby.setVisibility(View.GONE);

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