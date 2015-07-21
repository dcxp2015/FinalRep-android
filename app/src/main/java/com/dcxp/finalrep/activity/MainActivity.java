package com.dcxp.finalrep.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcxp.finalrep.R;
import com.dcxp.finalrep.fragments.Playlists;
import com.dcxp.finalrep.utils.ParseUtil;
import com.dcxp.finalrep.utils.ParseUtilCallback;
import com.dcxp.finalrep.utils.PhraseManager;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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

        //Parse initialization
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "kJawWr0883JUddCTgipaPphexvb3tPUY3Cq9E5uE", "cQCuAIKEwrYWmu4wvdYjwbbMFnBruLSupV7GQnh6");

        phraseManager = new PhraseManager(this);

        List<String> libraryChildren = new ArrayList<String>();
        libraryChildren.add("Record");
        libraryChildren.add("Explore");

        List<String> playlistsChildren = new ArrayList<String>();

        List<String> startWorkoutChildren = new ArrayList<String>();

        // Create the list of strings containing the nav bar titles
        Map<String, List<String>> navitems = new LinkedHashMap<String, List<String>>();
        navitems.put("Library", libraryChildren);
        navitems.put("Playlists", playlistsChildren);
        navitems.put("Start Workout", startWorkoutChildren);

        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.lv_nav);
        listView.setAdapter(new NavigiationDrawerAdapter(this, navitems));
        listView.setGroupIndicator(null);

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
    public void onStart(){
        super.onStart();
        ParseUtil.getAllPhrases(new ParseUtilCallback() {
            @Override
            public void success(Object result) {
                ParseObject res = (ParseObject) result;
                System.out.print(res);
            }

            @Override
            public void failure(ParseException reason) {
                reason.fillInStackTrace();
            }
        });
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
    private class NavigiationDrawerAdapter extends BaseExpandableListAdapter {
        private Map<String, List<String>> items;
        private Context context;

        public NavigiationDrawerAdapter(Context context, Map<String, List<String>> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public int getGroupCount() {
            return items.keySet().size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return items.get(getKey(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return items.get(getKey(groupPosition));
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return items.get(getKey(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View inflatedView = convertView;

            if(inflatedView == null) {
                inflatedView = LayoutInflater.from(context).inflate(R.layout.row_navigation_drawer, parent, false);
            }

            String name = getKey(groupPosition);

            ImageView itemImage = (ImageView) inflatedView.findViewById(R.id.iv_item_image);
            TextView itemName = (TextView) inflatedView.findViewById(R.id.txtv_item_name);

            itemName.setText(name);

            switch(name) {
                case "Library":
                    itemImage.setImageResource(R.drawable.explore);
                    break;
                case "Playlists":
                    itemImage.setImageResource(R.drawable.audio);
                    setContent(new Playlists());
                    break;
                case "Start Workout":
                    itemImage.setImageResource(R.drawable.flex);
                    break;
            }

            return inflatedView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View inflatedView = convertView;

            if(inflatedView == null) {
                inflatedView = getLayoutInflater().inflate(R.layout.row_navigation_drawer_child, parent, false);
            }

            ((TextView) inflatedView.findViewById(R.id.txtv_item_name)).setText(items.get(getKey(groupPosition)).get(childPosition));

            return inflatedView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private String getKey(int pos) {
            Iterator<String> iter = items.keySet().iterator();

            int i = 0;
            while(iter.hasNext()) {
                String str = iter.next();

                if(i == pos) {
                    return str;
                }

                i++;
            }

            return null;
        }
    }

    public PhraseManager getPhraseManager() {
        return phraseManager;
    }
}
