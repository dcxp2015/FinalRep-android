package com.dcxp.finalrep.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dcxp.finalrep.R;
import com.dcxp.finalrep.dialogs.RecordingDialog;
import com.dcxp.finalrep.fragments.Playlists;
import com.dcxp.finalrep.models.Phrase;
import com.dcxp.finalrep.utils.PhraseManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, RecordingDialog.IRecordingDialogListener {
    public static final String TAG = "com.dcxp.finalrep.debug";
    private static final String LIBRARY = "Library";
    private static final String PLAYLISTS = "Playlists";
    private static final String START_WORKOUT = "Start Workout";
    private static final String RECORD = "Record";
    private static final String EXPLORE = "Explore";

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

        List<String> libraryChildren = new ArrayList<String>();
        libraryChildren.add(RECORD);
        libraryChildren.add(EXPLORE);

        List<String> playlistsChildren = new ArrayList<String>();

        List<String> startWorkoutChildren = new ArrayList<String>();

        // Create the list of strings containing the nav bar titles
        final Map<String, List<String>> navitems = new LinkedHashMap<String, List<String>>();
        navitems.put(LIBRARY, libraryChildren);
        navitems.put(PLAYLISTS, playlistsChildren);
        navitems.put(START_WORKOUT, startWorkoutChildren);

        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        final NavigiationDrawerAdapter adapter = new NavigiationDrawerAdapter(this, navitems);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.lv_nav);
        listView.setAdapter(adapter);
        listView.setGroupIndicator(null);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selected = navitems.get(adapter.getKey(groupPosition)).get(childPosition);

                if (selected.equals(EXPLORE)) {
                    // User selected explore
                } else if (selected.equals(RECORD)) {
                    // Close the drawer
                    drawerLayout.closeDrawer(Gravity.LEFT);

                    // User selected record
                    new RecordingDialog().show(getFragmentManager(), null);
                }

                return false;
            }
        });

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

    @Override
    public void onPhraseRecorded(Phrase phrase) {
        phraseManager.addPhrase(phrase);
        phraseManager.savePhrases();
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

            TextView title = (TextView) inflatedView.findViewById(R.id.txtv_item_name);
            title.setText(items.get(getKey(groupPosition)).get(childPosition));

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
