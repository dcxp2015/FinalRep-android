package com.dcxp.tone.activities;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dcxp.tone.R;
import com.dcxp.tone.fragments.ExploreFragment;
import com.dcxp.tone.fragments.NavigationDrawerFragment;
import com.dcxp.tone.fragments.PhraseLibraryFragment;
import com.dcxp.tone.fragments.PlaylistsFragment;
import com.dcxp.tone.fragments.SelectWorkoutFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    public static final String TAG = "com.dcxp.tone";
    private static final int EXPLORE = 0;
    private static final int MY_PLAYLISTS = 1;
    private static final int MY_PHRASES = 2;
    private static final int START_WORKOUT = 3;

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationDrawerFragment navigationDrawerFragment;
    private String currentFragmentTitle;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        List<String> list = new ArrayList<String>();
        list.add("Explore");
        list.add("My Playlists");
        list.add("My Phrases");
        list.add("Start Workout");

        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        ListView listView = (ListView) findViewById(R.id.lv_nav);
        listView.setAdapter(new NavigationDrawerArrayAdapter(this, list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onNavigationDrawerItemSelected(position);
            }
        });

        navigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout));

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toggle.syncState();
                actionBar.setTitle(getString(R.string.title_activity_navigation_test));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                actionBar.setTitle(currentFragmentTitle);
                toggle.syncState();
            }

        };

        toggle.setDrawerIndicatorEnabled(true);

        toggle.syncState();

        drawerLayout.setDrawerListener(toggle);

        // Set the content to the intitial fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_content, new SelectWorkoutFragment())
                .commit();

        actionBar.setTitle(currentFragmentTitle = "Select Workout");
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if(drawerLayout == null) {
            return;
        }

        // Add in the right fragment based on which item was clicked in the drawer
        switch(position) {
            case EXPLORE:
                setContent(ExploreFragment.class, null);
                currentFragmentTitle = "Explore";
                break;
            case MY_PLAYLISTS:
                setContent(PlaylistsFragment.class, null);
                currentFragmentTitle = "My Playlists";
                break;
            case MY_PHRASES:
                setContent(PhraseLibraryFragment.class, null);
                currentFragmentTitle = "My Phrases";
                break;
        }

        // Hide the drawer
        drawerLayout.closeDrawer(Gravity.LEFT);
        toggle.syncState();
    }

    public void setContent(Class<? extends Fragment> fragcls, Bundle args) {
        try {
            Fragment fragment = fragcls.newInstance();
            fragment.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fl_content, fragment)
            .commit();
        } catch(Exception e) {
            Log.e(TAG, e + "");
        }
    }

    private class NavigationDrawerArrayAdapter extends ArrayAdapter<String> {
        private List<String> items;
        private Context context;

        public NavigationDrawerArrayAdapter(Context context, List<String> items) {
            super(context, -1, items);
            this.context = context;
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflatedView = convertView;

            if(inflatedView == null) {
                inflatedView = LayoutInflater.from(context).inflate(R.layout.navigation_drawer_item, parent, false);
            }

            String name = items.get(position);

            ImageView itemImage = (ImageView) inflatedView.findViewById(R.id.iv_item_image);
            TextView itemName = (TextView) inflatedView.findViewById(R.id.txtv_item_name);

            itemName.setText(name);

            switch(name) {
                case "Explore":
                    itemImage.setImageResource(R.drawable.explore);
                    break;
                case "My Playlists":
                    itemImage.setImageResource(R.drawable.playlist);
                    break;
                case "My Phrases":
                    itemImage.setImageResource(R.drawable.audio);
                    break;
                case "Start Workout":
                    itemImage.setImageResource(R.drawable.flex);
                    break;
            }

            return inflatedView;
        }
    }
}
