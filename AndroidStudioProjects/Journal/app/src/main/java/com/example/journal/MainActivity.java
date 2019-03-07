package com.example.journal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // sign up button click
    Button signUpButton;

    // Log in button click
    Button loginButton;
    EditText usernameEditText;
    EditText passwordEditText;
    String login_username;
    String login_password;
    String verify_user_pass;
    static String Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Journal by Sang Ly");

        // Sign up

        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_sign_up_page();
            }
        });

        // end sign up

        // Log in

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        usernameEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        passwordEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_account();
            }
        });


        // end Log in
    }

    // Sign up
    public void open_sign_up_page(){
        Intent startNewActivity = new Intent(this, signUp.class);
        startActivity(startNewActivity);
    }

    // log in

    private static String USERNAME_PASSWORD = "Journal_By_Sang_Ly";
    private static String USERNAME_PASSWORD_INFO = "username_password_info";


    public void load_account(){
        login_username = usernameEditText.getText().toString();
        login_password = passwordEditText.getText().toString();
        Login = login_username  + login_password;

        SharedPreferences page1 = getSharedPreferences(USERNAME_PASSWORD, MODE_PRIVATE);
        verify_user_pass = page1.getString(USERNAME_PASSWORD_INFO+Login,"");

        if(verify_user_pass.toLowerCase().equals( Login.toLowerCase()) && Login.toLowerCase() !=  ""){
            access_journal();
        }
        else{
            Login_Error_Message();
        }
    }

    public void access_journal(){
        Intent startNewActivity = new Intent(this, Journal.class);
        startActivity(startNewActivity);
    }
    public void Login_Error_Message(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Incorrect Username and Password");
        builder.setCancelable(false);
        builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clear_user_pass();
            }
        });
        builder.show();
    }

    public void clear_user_pass(){
        usernameEditText.setText("");
        passwordEditText.setText("");
    }

}
