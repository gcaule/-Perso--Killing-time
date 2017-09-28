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
    public TextView mThemePartyLobby;

    public HomeJoueur_LobbyHolder(View itemView) {
        super(itemView);
        mNamePartyLobby = (Button) itemView.findViewById(R.id.buttonLobbyName);
        mThemePartyLobby = (TextView) itemView.findViewById(R.id.textViewLobbyDescription);
        mThemePartyLobby.setVisibility(View.GONE);

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



    public void setName(String name) {
        mNamePartyLobby.setText(name);
    }

    public void setTheme(String theme) {
        mThemePartyLobby.setText(theme);
    }
}