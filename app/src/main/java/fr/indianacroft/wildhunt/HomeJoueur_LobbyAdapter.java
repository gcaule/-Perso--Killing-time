package fr.indianacroft.wildhunt;

import android.content.Context;
import android.nfc.Tag;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by pierre on 27/09/17.
 */

public class HomeJoueur_LobbyAdapter extends FirebaseRecyclerAdapter<BDD, HomeJoueur_LobbyHolder>{
    private static final String TAG = HomeJoueur_LobbyAdapter.class.getSimpleName();
    private Context context;
    public HomeJoueur_LobbyAdapter(Class<BDD> modelClass, int modelLayout, Class<HomeJoueur_LobbyHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
    }
    @Override
    protected void populateViewHolder(HomeJoueur_LobbyHolder viewHolder, BDD model, int position) {
        viewHolder.mNamePartyLobby.setText(model.getNom());
        viewHolder.mThemePartyLobby.setText(model.getTheme());
        //TODO pour mettre image ?
        // Glide.with(context).load(model.getRecipeImageUrl()).into(viewHolder.recipeImage);
    }
}