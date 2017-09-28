package fr.indianacroft.wildhunt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ChallengesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText editTextChallengeName;
    EditText editTextChallengeDescription;
    EditText editTextHint;
    String editTextChallengeNameContent;
    String editTextChallengeDescriptionContent;
    String editTextHintContent;
    Button butCreateChallenge;
    /*ImageView imageViewCancel;
    ImageView imageViewCancel2;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Drawer Menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Bottom Navigation Bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Avatar
        ImageView imageViewAvatar = (ImageView) findViewById(R.id.imageViewAvatar);
        imageViewAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChallengesActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.challenge_difficulty, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Button Actions / Change ImageView / Text in buttons
        // TODO : changer les challenges en list view
        editTextChallengeName = (EditText) findViewById(R.id.editTextChallengeName);
        editTextChallengeDescription = (EditText) findViewById(R.id.editTextChallengeDescription);
        editTextHint = (EditText) findViewById(R.id.editTextHint);
        butCreateChallenge = (Button) findViewById(R.id.butCreateChallenge);
        /*imageViewCancel = (ImageView) findViewById(R.id.imageViewCancel);
        imageViewCancel2 = (ImageView) findViewById(R.id.imageViewCancel2);*/
        butCreateChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextChallengeNameContent = editTextChallengeName.getText().toString();
                editTextChallengeDescriptionContent = editTextChallengeDescription.getText().toString();
                editTextHintContent = editTextHint.getText().toString();
                if ((!editTextChallengeNameContent.equals("")) && (!editTextChallengeDescriptionContent.equals("")) && (!editTextHintContent.equals(""))) {
                    /*imageViewCancel.setImageResource(R.drawable.checked);
                    imageViewCancel2.setImageResource(R.drawable.checked);
                    butNewChallenge.setText("Challenge 1 created");
                    butNewChallenge2.setText("Challenge 2 created");*/
                    Intent intent = new Intent(ChallengesActivity.this, HomeGameMaster.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ChallengesActivity.this, "Please enter all quest details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Drawer Menu
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // TODO : remplacer les toasts par des liens
        if (id == R.id.nav_home) {
            Toast.makeText(ChallengesActivity.this, "Créer lien page Home", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_rules) {
            Toast.makeText(ChallengesActivity.this, "Créer lien page Rules", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(ChallengesActivity.this, ProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_quests) {
            Toast.makeText(ChallengesActivity.this, "Créer lien page Quests", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_switch) {
            Toast.makeText(ChallengesActivity.this, "Créer lien page Switch", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_delete) {
            Toast.makeText(ChallengesActivity.this, "Déco joueur", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Bottom Navigation Bar
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(ChallengesActivity.this, "Créer lien page Home", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_camera:
                    Toast.makeText(ChallengesActivity.this, "Créer lien page camera", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_switch:
                    Toast.makeText(ChallengesActivity.this, "Créer lien page switch", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(ChallengesActivity.this, "Créer lien page Notifications", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };
}
