package fr.indianacroft.wildhunt;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Tab_HomeJoueur extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_homejoueur);

        // Customize Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setBackgroundColor(Color.parseColor("#9E511C"));

        // Fragment Adapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Avatar & Menu clicks
        ImageView imageViewAvatar = (ImageView) findViewById(R.id.imageViewAvatar);
        ImageView imageViewMenu = (ImageView) findViewById(R.id.imageViewMenu);
        imageViewAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Créer lien page profil
                Toast.makeText(Tab_HomeJoueur.this, "Créer lien page profil", Toast.LENGTH_LONG).show();
            }
        });
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Créer lien page menu
                Toast.makeText(Tab_HomeJoueur.this, "Créer lien page menu", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Tab_HomeJoueur_PlayerActivity playerActivity = new Tab_HomeJoueur_PlayerActivity();
                    return playerActivity;
                case 1:
                    Tab_HomeJoueur_LobbyActivity lobbyActivity = new Tab_HomeJoueur_LobbyActivity();
                    return lobbyActivity;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
//TODO enlever hardcoding
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Partie en cours";
                case 1:
                    return "Lobby";
            }
            return null;
        }
    }
}
