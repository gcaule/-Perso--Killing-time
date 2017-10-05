package fr.indianacroft.wildhunt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class HomeJoueur_PlayerActivity extends Fragment {

    Button butNewChallenge;
    Button butNewChallenge2;
    ImageView imageViewCancel;
    ImageView imageViewCancel2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.homejoueur_playeractivity, container, false);

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
