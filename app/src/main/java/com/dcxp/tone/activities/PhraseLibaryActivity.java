package com.dcxp.tone.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.dcxp.tone.R;
import com.dcxp.tone.library.PhraseLibaryRowAdapter;

import java.util.ArrayList;
import java.util.List;

public class PhraseLibaryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase_libary);

        List<String> phrases = new ArrayList<String>();

        for(int i = 0; i < 10; i++) {
            phrases.add("phrase: " + i);
        }

        ListView lv = (ListView) findViewById(R.id.lv_phrases);
        lv.setAdapter(new PhraseLibaryRowAdapter(this, phrases));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_phrase_libary, menu);
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
