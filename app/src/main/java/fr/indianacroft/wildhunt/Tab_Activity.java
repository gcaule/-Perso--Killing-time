package fr.indianacroft.wildhunt;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Tab_Activity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_);

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
                Toast.makeText(Tab_Activity.this, "Créer lien page profil", Toast.LENGTH_LONG).show();
            }
        });
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Créer lien page menu
                Toast.makeText(Tab_Activity.this, "Créer lien page menu", Toast.LENGTH_LONG).show();
            }
        });

        // Bottom Navigation Bar
        /*final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    //TODO : faire les liens vers les différentes pages
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home:
                                Toast.makeText(Tab_Activity.this, "Créer lien Home", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.photo:
                                Toast.makeText(Tab_Activity.this, "Créer lien Appareil photo", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.users:
                                Toast.makeText(Tab_Activity.this, "Créer lien Home Joueur/MJ", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.notifications:
                                Toast.makeText(Tab_Activity.this, "Créer lien notifs", Toast.LENGTH_LONG).show();
                                break;
                        }
                        return false;
                    }
                });*/
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Tab1_Activity tab1 = new Tab1_Activity();
                    return tab1;
                case 1:
                    Tab2_Activity tab2 = new Tab2_Activity();
                    return tab2;
                case 2:
                    Tab3_Activity tab3 = new Tab3_Activity();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Create new game";
                case 1:
                    return "Games created";
                case 2:
                    return "Validate game";
            }
            return null;
        }
    }
}
