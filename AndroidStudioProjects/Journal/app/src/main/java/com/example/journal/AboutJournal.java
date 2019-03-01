package com.example.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class AboutJournal extends AppCompatActivity {

    TextView aboutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_journal);

        String about = "Journal by Sang Ly \n\n" +
                "Journal is the application that will replace notebooks in the future." +
                "Journal allow you to save the writing and import picture on the go";
        setTitle("about");
        aboutTextView =  (TextView) findViewById(R.id.aboutTextView);
        aboutTextView.setText(about);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater MI = getMenuInflater();
        MI.inflate(R.menu.journal, menu);
        return true;
    }

}
