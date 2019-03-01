package com.example.journal;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class JournalPage4 extends AppCompatActivity {

    TextInputLayout page4InputText;
    TextInputEditText page4EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_4);

        setTitle("Page 4");

        page4InputText = (TextInputLayout) findViewById(R.id.page4TextInput);
        page4EditText = (TextInputEditText) findViewById(R.id.page4EditText);
        loadPage1();

        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                next_Go_Page_5();
            }
        });
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                back_Go_page_3();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater MI = getMenuInflater();
        MI.inflate(R.menu.journal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.save:
                save();
                return true;
            case R.id.About:
                showAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Save page
    // Upon click "save"
    // two option are given "Cancel" "Save"
    // "Cancel" will not save and keep text
    // "Save" will save text when user exit app
    public void save(){
        AlertDialog.Builder builder = new AlertDialog.Builder(JournalPage4.this);
        builder.setMessage("Save this page");
        builder.setCancelable(false);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                savePage1();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void showAbout(){
        Intent startNewActivity = new Intent(this, AboutJournal.class);
        startActivity(startNewActivity);
    }

    public void   back_Go_page_3(){
        Intent startNewActivity = new Intent(this, JournalPage3.class);
        startActivity(startNewActivity);
    }

    public void next_Go_Page_5() {
        Intent startNewActivity = new Intent(this, JournalPage5.class);
        startActivity(startNewActivity);
    }

    public static final String STORE_PAGE_4 = "page4";
    public static final String Page_4_info = "page4info";

    public void savePage1(){
        SharedPreferences spPage1 = getSharedPreferences(STORE_PAGE_4,MODE_PRIVATE);
        SharedPreferences.Editor edPage1 = spPage1.edit();
        edPage1.putString(Page_4_info, page4EditText.getText().toString());
        edPage1.apply();

    }
    public void loadPage1(){
        SharedPreferences page1 = getSharedPreferences(STORE_PAGE_4, MODE_PRIVATE);
        page4EditText.setText(page1.getString(Page_4_info,"").toString());
    }


}