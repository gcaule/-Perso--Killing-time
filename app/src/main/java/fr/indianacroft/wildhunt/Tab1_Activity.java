package fr.indianacroft.wildhunt;

/**
 * Created by apprenti on 9/26/17.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class Tab1_Activity extends Fragment {

    Button butNewChallenge;
    Button butNewChallenge2;
    ImageView imageViewCancel;
    ImageView imageViewCancel2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        // Spinner
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.life_duration, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Change ImageView on click
        butNewChallenge = (Button) rootView.findViewById(R.id.butNewChallenge);
        imageViewCancel = (ImageView) rootView.findViewById(R.id.imageViewCancel);
        butNewChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewCancel.setImageResource(R.drawable.checked);
            }
        });
        butNewChallenge2 = (Button) rootView.findViewById(R.id.butNewChallenge2);
        imageViewCancel2 = (ImageView) rootView.findViewById(R.id.imageViewCancel2);
        butNewChallenge2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewCancel2.setImageResource(R.drawable.checked);
            }
        });

        return rootView;
    }
}
