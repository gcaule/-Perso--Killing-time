package fr.indianacroft.wildhunt;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button butPlay;
    Button but2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Button Play
        butPlay = (Button) findViewById(R.id.butPlay);
        butPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tab_HomeJoueur.class);
                startActivity(intent);
            }
        });//Button Play
        but2 = (Button) findViewById(R.id.button2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tab_Activity.class);
                startActivity(intent);
            }
        });
    }
}
