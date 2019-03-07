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
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.journal.MainActivity.Login;
import static com.example.journal.signUp.combine_user_pass;

public class JournalPage3 extends AppCompatActivity {

    TextInputLayout page3InputText;
    TextInputEditText page3EditText;
    EditText titleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_3);

        setTitle("Page 3");

        page3InputText = (TextInputLayout) findViewById(R.id.page3TextInput);
        page3EditText = (TextInputEditText) findViewById(R.id.page3EditText);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        loadPage1();
        loadPage1Title();

        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                next_Go_Page_4();
            }
        });
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                back_Go_page_2();
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
            case R.id.save: {
                // hide keyboard
                titleEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                page3EditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                save();
            }
                return true;
            case R.id.About:
                showAbout();
                return true;
            case R.id.Logout:
                showLoginPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showLoginPage(){
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }

    // Save page
    // Upon click "save"
    // two option are given "Cancel" "Save"
    // "Cancel" will not save and keep text
    // "Save" will save text when user exit app
    public void save(){
        AlertDialog.Builder builder = new AlertDialog.Builder(JournalPage3.this);
        builder.setMessage("Save this page");
        builder.setCancelable(false);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                savePage1();
                savePage1Title();
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

    public void   back_Go_page_2(){
        Intent startNewActivity = new Intent(this, JournalPage2.class);
        startActivity(startNewActivity);
    }

    public void next_Go_Page_4() {
        Intent startNewActivity = new Intent(this, JournalPage4.class);
        startActivity(startNewActivity);
    }

    public static final String STORE_PAGE_3 = "page3";
    public static final String Page_3_info = "page3info";

    public void savePage1(){
        SharedPreferences spPage1 = getSharedPreferences(STORE_PAGE_3,MODE_PRIVATE);
        SharedPreferences.Editor edPage1 = spPage1.edit();
        edPage1.putString(Page_3_info+Login, page3EditText.getText().toString());
        edPage1.apply();

    }
    public void loadPage1(){
        SharedPreferences page1 = getSharedPreferences(STORE_PAGE_3, MODE_PRIVATE);
        page3EditText.setText(page1.getString(Page_3_info+Login,"").toString());
    }

    // Store Title text information
    public static final String STORE_PAGE_3_Title = "page_3_Title";
    public static final String Page_3_Title_info = "page_3_Title_info";

    public void savePage1Title(){
        SharedPreferences spPage1 = getSharedPreferences(STORE_PAGE_3_Title,MODE_PRIVATE);
        SharedPreferences.Editor edPage1 = spPage1.edit();
        edPage1.putString(Page_3_Title_info+Login, titleEditText.getText().toString());
        edPage1.apply();
    }
    public void loadPage1Title(){
        SharedPreferences page1 = getSharedPreferences(STORE_PAGE_3_Title, MODE_PRIVATE);
        titleEditText.setText(page1.getString(Page_3_Title_info+Login,"").toString());
    }

}
