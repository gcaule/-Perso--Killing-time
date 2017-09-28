package fr.indianacroft.wildhunt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by pierre on 9/26/17.
 */

//public class HomeJoueur_LobbyActivity extends Fragment {
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.homejoueur_lobby, container, false);
//
//
//        RecyclerView mRecyclerViewLobby = (RecyclerView) rootView.findViewById(R.id.homeJoueurLobbyRecyclerView);
//
//        // Stuff from Android doc to improve performances
//        mRecyclerViewLobby.setHasFixedSize(true);
//        mRecyclerViewLobby.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
//
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//
//        return rootView;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        private final Button mNameParty;
//        private final TextView mTheme;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            mNameParty = (Button) itemView.findViewById(R.id.buttonLobbyName);
//            mTheme = (TextView) itemView.findViewById(R.id.textViewLobbyDescription);
//        }
//
//        public void setName(String name) {
//            mNameParty.setText(name);
//        }
//
//        public void setTheme(String theme) {
//            mTheme.setText(theme);
//        }
//    }
//
//}

public class HomeJoueur_LobbyActivity extends Fragment {
    private static final String TAG = HomeJoueur_LobbyActivity.class.getSimpleName();
    private DatabaseReference childRef;
    public HomeJoueur_LobbyActivity() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homejoueur_lobbyactivity, container, false);
        //TODO titre ?
        // getActivity().setTitle(getString(R.string.recipe_categories));
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        RecyclerView lobbyRecyclerview = (RecyclerView)view.findViewById(R.id.recyclerViewHomeJoueurLobby);
//
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        lobbyRecyclerview.setLayoutManager(linearLayoutManager);
//
//        lobbyRecyclerview.setHasFixedSize(true);
//        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
//        childRef = mDatabaseRef.child("pushld");
//        mLobbyAdapter = new HomeJoueur_LobbyAdapter(BDD.class, R.layout.homejoueur_lobby, HomeJoueur_LobbyHolder.class, childRef, getContext());
//
//        lobbyRecyclerview.setAdapter(mLobbyAdapter);

        RecyclerView recyclerViewLobby = (RecyclerView) view.findViewById(R.id.recyclerViewHomeJoueurLobby);
        recyclerViewLobby.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        FirebaseRecyclerAdapter mAdapter = new FirebaseRecyclerAdapter<BDD, HomeJoueur_LobbyHolder>(
                BDD.class,
                R.layout.homejoueur_lobby,
                HomeJoueur_LobbyHolder.class,
                ref) {
            @Override
            public void populateViewHolder(HomeJoueur_LobbyHolder holder, BDD bdd, int position) {
                holder.setName(bdd.getNom());
                holder.setTheme(bdd.getTheme());
            }
        };

        recyclerViewLobby.setAdapter(mAdapter);





        return view;
    }


}
