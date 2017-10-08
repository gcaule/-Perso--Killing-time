package fr.indianacroft.wildhunt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;

public class ChallengesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText name_challenge;
    EditText hint_challenge;
    String name_challenge_content;
    String hint_challenge_content;
    Spinner spinner_challenge;
    ImageView imageViewInscriptionLogo;
    Button butLoad;
    Button butUpload;
    Button butCreateChallenge;
    FirebaseDatabase ref;
    DatabaseReference childRef;
    private String mUserId;
    private String mUserQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        // Pour recuperer la key d'un user (pour le lier a une quête)
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mUserId = sharedPreferences.getString("mUserId", mUserId);
        Log.d("key", mUserId);
        /////////////////////////////////////////////////////////////////

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
        spinner_challenge = (Spinner) findViewById(R.id.spinner_challenge);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.challenge_difficulty, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_challenge.setAdapter(adapter);

        // Button Actions / Change ImageView / Text in buttons
        // TODO : changer les challenges en list view
        name_challenge = (EditText) findViewById(R.id.challenge_name);
        hint_challenge = (EditText) findViewById(R.id.hint_challenge);
        butCreateChallenge = (Button) findViewById(R.id.butCreateChallenge);
        /*imageViewCancel = (ImageView) findViewById(R.id.imageViewCancel);
        imageViewCancel2 = (ImageView) findViewById(R.id.imageViewCancel2);*/
        butCreateChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_challenge_content = name_challenge.getText().toString();
                hint_challenge_content = hint_challenge.getText().toString();
                if ((!name_challenge_content.equals("")) && (!hint_challenge_content.equals(""))) {
                    /*imageViewCancel.setImageResource(R.drawable.checked);
                    imageViewCancel2.setImageResource(R.drawable.checked);
                    butNewChallenge.setText("Challenge 1 created");
                    butNewChallenge2.setText("Challenge 2 created");*/
                    Intent intent = new Intent(ChallengesActivity.this, HomeGameMaster.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ChallengesActivity.this, R.string.toast_challenge, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Upload & Take photo
        butLoad = (Button) findViewById(R.id.butLoad);
        butLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        butUpload = (Button) findViewById(R.id.butUpload);
        butUpload.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

        // Database
        name_challenge = (EditText) findViewById(R.id.challenge_name);
        hint_challenge = (EditText) findViewById(R.id.hint_challenge);
        butCreateChallenge = (Button) findViewById(R.id.butCreateChallenge);

        ref = FirebaseDatabase.getInstance();
        childRef = ref.getReference("Challenge");

        butCreateChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameContent = name_challenge.getText().toString();
                String hintContent = hint_challenge.getText().toString();
                String spinnerContent = spinner_challenge.getSelectedItem().toString();
                String idquest = "test";//TODO a modifier

                // childRef.push().getKey() is used to generate the different key
                final String challengeId = ref.getReference("Challenge").push().getKey();

                // On recupere la quete crée par l'user actuel pour link challenge a la quête
                DatabaseReference refUser =
                        FirebaseDatabase.getInstance().getReference().child("User").child(mUserId);
                refUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        mUserQuest = user.getUser_createdquestID();
                        String userName = user.getUser_name();

                        childRef.child(challengeId).child("challenge_questId").setValue(mUserQuest);
                        childRef.child(challengeId).child("challenge_creatorID").setValue(mUserId);
                        childRef.child(challengeId).child("challenge_creatorname").setValue(userName);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                Challenge challenge = new Challenge(nameContent, hintContent, spinnerContent, idquest, mUserId);

                challenge.setChallenge_name(nameContent);
                challenge.setHint_challenge(hintContent);
                challenge.setChallenge_difficulty(spinnerContent);

                childRef.child(challengeId).setValue(challenge);



                name_challenge.setText("");
                hint_challenge.setText("");
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
            Intent intent = new Intent(ChallengesActivity.this, HomeGameMaster.class);
            startActivity(intent);
        } else if (id == R.id.nav_rules) {
            Intent intent = new Intent(ChallengesActivity.this, Rules.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(ChallengesActivity.this, ProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_camera) {
            Toast.makeText(ChallengesActivity.this, "Lien page Photo", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_quests) {
            Intent intent = new Intent(ChallengesActivity.this, HomeGameMaster.class);
            startActivity(intent);
        } else if (id == R.id.nav_switch) {
            Intent intent = new Intent(ChallengesActivity.this, HomeJoueur.class);
            startActivity(intent);
        } else if (id == R.id.nav_delete) {
            Toast.makeText(ChallengesActivity.this, "Déco joueur", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Send photos to ImageView
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        imageViewInscriptionLogo = (ImageView) findViewById(R.id.imageViewInscriptionLogo);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK
                //&& data != null && data.getData() != null
            ) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewInscriptionLogo.setImageBitmap(imageBitmap);
        }
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri targetUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageViewInscriptionLogo.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
