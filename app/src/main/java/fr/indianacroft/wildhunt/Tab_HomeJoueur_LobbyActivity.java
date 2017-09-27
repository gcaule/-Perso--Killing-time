package fr.indianacroft.wildhunt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pierre on 9/26/17.
 */

public class Tab_HomeJoueur_LobbyActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_homejoueur_lobbyactivity, container, false);
        return rootView;

        // Get the RecyclerView from Resource System
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.homeJoueurLobbyRecyclerView);

        // Stuff from Android doc to improve performances
        mRecyclerView.setHasFixedSize(true);

        // Create the Layout for the RecyclerView to display the Cards
        // ... A Grid Layout, with 2 Columns
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Set the Custom Adapter
        mRecyclerView.setAdapter(new HomeJoueurLobbyAdapter(rootView.getContext()));
    }
}
