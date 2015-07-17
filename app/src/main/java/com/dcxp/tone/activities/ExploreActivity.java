package com.dcxp.tone.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.dcxp.tone.Divider;
import com.dcxp.tone.R;
import com.dcxp.tone.uploadexplorer.UploadRVAdapter;
import com.dcxp.tone.uploadexplorer.UploadedPhrase;

import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends ActionBarActivity {
    private List<UploadedPhrase> uploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        uploads = new ArrayList<UploadedPhrase>();
        uploads.add(new UploadedPhrase("Push harder", "Daniel Christopher"));
        uploads.add(new UploadedPhrase("You got it", "Daniel Christopher"));
        uploads.add(new UploadedPhrase("Lets go", "Daniel Christopher"));
        uploads.add(new UploadedPhrase("No pain no gain", "Daniel Christopher"));
        uploads.add(new UploadedPhrase("Pain is temporary", "Daniel Christopher"));
        uploads.add(new UploadedPhrase("Never give up", "Daniel Christopher"));
        uploads.add(new UploadedPhrase("Work harder", "Daniel Christopher"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView rvExplore = (RecyclerView) findViewById(R.id.rv_explore_content);
        rvExplore.setAdapter(new UploadRVAdapter(uploads));
        rvExplore.setHasFixedSize(true);
        rvExplore.setLayoutManager(layoutManager);
        rvExplore.addItemDecoration(new Divider(25));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_explore, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
