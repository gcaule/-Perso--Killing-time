package fr.indianacroft.wildhunt;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button butPlay;

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
                Intent intent = new Intent(MainActivity.this, Tab_Activity.class);
                startActivity(intent);
            }
        });
    }
}
