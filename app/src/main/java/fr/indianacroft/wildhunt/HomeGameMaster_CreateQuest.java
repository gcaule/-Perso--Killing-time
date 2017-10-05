package fr.indianacroft.wildhunt;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeGameMaster_CreateQuest extends Fragment {

    Button butNewChallenge;
    Button butNewChallenge2;
    Button butAddNewChallenge;
    Button button_create_quest;
    EditText name_quest;
    EditText description_quest;
    Spinner spinner_quest;
    FirebaseDatabase ref;
    DatabaseReference childRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.homegamemaster_createquest, container, false);

        // Spinner
        spinner_quest = (Spinner) rootView.findViewById(R.id.spinner_challenge);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.life_duration, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_quest.setAdapter(adapter);

        // Go to Challenge Activity on click
        butNewChallenge = (Button) rootView.findViewById(R.id.butNewChallenge);
        butNewChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChallengesActivity.class);
                startActivity(intent);
            }
        });
        butNewChallenge2 = (Button) rootView.findViewById(R.id.butNewChallenge2);
        butNewChallenge2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChallengesActivity.class);
                startActivity(intent);
            }
        });
        butAddNewChallenge = (Button) rootView.findViewById(R.id.butAddNewChallenge);
        butAddNewChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChallengesActivity.class);
                startActivity(intent);
            }
        });

        // Database
        name_quest = (EditText) rootView.findViewById(R.id.name_quest);
        description_quest = (EditText) rootView.findViewById(R.id.description_quest);
        button_create_quest = (Button) rootView.findViewById(R.id.button_create_quest);

        ref = FirebaseDatabase.getInstance();
        childRef = ref.getReference("Quest");

        button_create_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameContent = name_quest.getText().toString();
                String descriptionContent = description_quest.getText().toString();
                String spinnerContent = spinner_quest.getSelectedItem().toString();

                // childRef.push().getKey() is used to generate the different key
                String userId = ref.getReference("Quest").push().getKey();
                //  DatabaseReference childRef = ref.getReference("form");

                Quest quest = new Quest(nameContent, descriptionContent, spinnerContent);

                quest.setQuest_name(nameContent);
                quest.setQuest_description(descriptionContent);
                quest.setLife_duration(spinnerContent);

                childRef.child(userId).setValue(quest);

                name_quest.setText("");
                description_quest.setText("");
            }
        });

        return rootView;
    }
}
