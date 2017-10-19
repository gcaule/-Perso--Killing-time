package fr.indianacroft.wildhunt;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ChallengeToValidateActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imageViewAvatar;
    private String mUserId;
    private String mUser_name;
    private String mUser_quest;
    private String mQuest_name;
    private String mQuest_description;
    private String mLife_duration;
    private String mName_challenge;
    private String mDiff_challenge;
    private String mHint_challenge;
    private String mKey_challenge;
    private String mCreatorId;
    private String mQuestId;
    private int mUser_score;
    private String mUser_indice;
    private int mNbrePoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_to_validate);

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
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        View headerview = navigationView.getHeaderView(0);
        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PlayerActivity.class));
            }
        });

        // Bottom Navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_validate);
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setAnimation(null);
        bottomNavigationView.setSelectedItemId(R.id.navigation_accept);

        // Avatar
        imageViewAvatar = (ImageView) findViewById(R.id.imageViewAvatar);
        imageViewAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });

        // On appele les methodes declarées plus bas (pour chercher l'user et le challenge)
        searchUser();
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
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_rules) {
            Intent intent = new Intent(getApplicationContext(), RulesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_play) {
            Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_lobby) {
            Intent intent = new Intent(getApplicationContext(), LobbyActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_create) {
            startActivity(new Intent(getApplicationContext(), CreateQuestActivity.class));
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(getApplicationContext(), ValidateQuestActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_delete) {
            startActivity(new Intent(getApplicationContext(), ConnexionActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // METHODE POUR TROUVER USER
    private void searchUser() {

        String userId = getIntent().getStringExtra("UserToValidate");
        // On recupere toutes les données de l'user à valider
        final DatabaseReference refUser =
                FirebaseDatabase.getInstance().getReference().child("User").child(userId);
        refUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                mUser_name = user.getUser_name();
                mUser_quest = user.getUser_quest();
                mUser_score = user.getScore();
                mUser_indice = user.getUser_indice();

                TextView userName = (TextView) findViewById(R.id.validateUserName);
                userName.setText(mUser_name);

                searchChallenges();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ChallengeToValidateActivity.this, "Vous n'avez crée aucune partie", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ChallengesActivity.class));
            }
        });
    }

    // METHODE POUR TROUVER CHALLENGE
    private void searchChallenges() {
        final String challengeId = getIntent().getStringExtra("ToValidate");
        final String userId = getIntent().getStringExtra("UserToValidate");
        // On recupere les données des challenges
        DatabaseReference refUserChallenge = FirebaseDatabase.getInstance().getReference().child("Challenge").child(mUser_quest).child(challengeId);
        refUserChallenge.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Challenge challenge = dataSnapshot.getValue(Challenge.class);
                String name = challenge.getChallenge_name();
                mNbrePoints = challenge.getChallenge_nbrePoints();

                TextView challengeName = (TextView) findViewById(R.id.validateChallenge);
                challengeName.setText(name);

                // Pour recuperer la key d'un user (pour le lier a une quête)
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                mUserId = sharedPreferences.getString("mUserId", mUserId);
                Log.d("key", mUserId);

                // Reference to an image file in Firebase Storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference("User").child(userId).child("QuestToBeValidated").child(mUser_quest).child(challengeId);
                // ImageView in your Activity
                ImageView imagePlayerSolution = (ImageView) findViewById(R.id.imageViewPlayerSolution);
                // Load the image using Glide
//                if (storageReference.getDownloadUrl().isSuccessful()){
                Glide.with(getApplicationContext())
                        .using(new FirebaseImageLoader())
                        .load(storageReference)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(imagePlayerSolution);


                // Au clic du bouton "REFUSER"
                View navigation_refuse = findViewById(R.id.navigation_refuse);
                navigation_refuse.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO envoyer une notification au joueur
                        // Supprimer sa photo ?
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(ChallengeToValidateActivity.this, R.style.MyDialog);
                        } else {
                            builder = new AlertDialog.Builder(ChallengeToValidateActivity.this);
                        }
                        builder.setTitle("REFUSER cette solution")
                                .setMessage("Etes vous sur de vouloir REFUSER cette solution ?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(ChallengeToValidateActivity.this, "Défi refusé!", Toast.LENGTH_SHORT).show();
                                        // Modifier le champ dans le user Creator pour le mettre en true !
                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                        ref.child("User").child(mUserId).child("aValider").child(mUser_quest).child(challengeId).child(userId).setValue(null);

                                        startActivity(new Intent(getApplicationContext(), ValidateQuestActivity.class));
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();




                    }
                });

                // Au click du bouton "ACCEPTER"
                View navigation_validate = findViewById(R.id.navigation_accept);
                navigation_validate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO envoyer une notification au joueur quand son challenge est validé
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(ChallengeToValidateActivity.this, R.style.MyDialog);
                        } else {
                            builder = new AlertDialog.Builder(ChallengeToValidateActivity.this);
                        }
                        builder.setTitle("Valider ce défi")
                                .setMessage("Etes vous sur de vouloir VALIDER ce défi ?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(ChallengeToValidateActivity.this, "Défi validé!", Toast.LENGTH_SHORT).show();
                                        // Modifier le champ dans le user Creator pour le mettre en true !
                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                        ref.child("User").child(mUserId).child("aValider").child(mUser_quest).child(challengeId).child(userId).setValue(true);

                                        // Modifier le champ dans le user Player pour mettre le challenge en done !
                                        //ref.child("User").child(userId).child("challenge_done").child(challengeId).child("state").setValue("true");

                                        // On update son score !!
                                        if (mUser_indice.equals("true")) {
                                            mNbrePoints = mNbrePoints / 2;
                                        }
                                        mNbrePoints = mUser_score + mNbrePoints;
                                        ref.child("User").child(userId).child("score").setValue(mNbrePoints);

                                        // On retourne a la page de correction
                                        startActivity(new Intent(getApplicationContext(), ValidateQuestActivity.class));

                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                });


                // Au click du bouton "SOLUTION"
                View navigation_solution = findViewById(R.id.navigation_solution);
                navigation_solution.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(),ChallengeToValidateActivity_PopUp.class);
                        intent.putExtra("Quest", mUser_quest);
                        intent.putExtra("Challenge", challengeId);
                        startActivity(intent);
                    }
                });





                return;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
