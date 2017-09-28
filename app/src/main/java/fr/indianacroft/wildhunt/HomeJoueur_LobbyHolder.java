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
    }

    public void setName(String name) {
        mNamePartyLobby.setText(name);
    }

    public void setTheme(String theme) {
        mThemePartyLobby.setText(theme);
    }
}