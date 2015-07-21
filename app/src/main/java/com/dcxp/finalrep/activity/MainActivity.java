package com.dcxp.finalrep.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dcxp.finalrep.R;
import com.dcxp.finalrep.fragments.Playlists;
import com.dcxp.finalrep.utils.PhraseManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private NavigationDrawerFragment navigationDrawerFragment;
    private ActionBarDrawerToggle toggle;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private CharSequence title;
    private PhraseManager phraseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phraseManager = new PhraseManager(this);

        // Create the list of strings containing the nav bar titles
        List<String> list = new ArrayList<String>();
        list.add("Explore");
        list.add("My Playlists");
        list.add("Start Workout");

        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        ListView listView = (ListView) findViewById(R.id.lv_nav);
        listView.setAdapter(new NavigationDrawerArrayAdapter(this, list));

        actionBar = getSupportActionBar();

        title = getTitle();

        // Set up the drawer.
        navigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout));

        // Set up the toggle
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
                actionBar.setTitle(title);
                toggle.syncState();
            }
        };

        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(true);

        drawerLayout.setDrawerListener(toggle);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!navigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    public void setContent(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fl_content, fragment)
                .commit();
    }

    /**
     * Adapter for the navigation drawer list view. Ouputs a view containing a textview and imageview
     */
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
                inflatedView = LayoutInflater.from(context).inflate(R.layout.row_navigation_drawer, parent, false);
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
                    itemImage.setImageResource(R.drawable.audio);
                    setContent(new Playlists());
                    break;
                case "Start Workout":
                    itemImage.setImageResource(R.drawable.flex);
                    break;
            }

            return inflatedView;
        }
    }

    public PhraseManager getPhraseManager() {
        return phraseManager;
    }
}
