package com.example.journal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;

import static com.example.journal.MainActivity.Login;

public class Journal extends AppCompatActivity {

    // Declare variable
    Button addPhotoButton;
    EditText editText;
    ImageView imageView;
    ImageView imageViewLayout;
    private static final int Image = 100;
    Uri imageUri;
    String image_Convert_To_Base64;

    // assign variable to their respect id
    // load both title and image for front cover
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal);

        setTitle("Journal by Sang Ly");

        addPhotoButton = (Button) findViewById(R.id.addPhotoButton);
        editText = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageViewLayout = (ImageView) findViewById(R.id.imageViewLayout);
        imageView.setImageURI(imageUri);

        load_title();
        load_image();

        addPhotoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                accessGallery();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                next_Go_Page_1();
            }
        });
        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                next_Go_Page_1();
            }
        });

    }

    public void accessGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, Image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == Image){
            imageUri = data.getData();
            addPhotoButton.setVisibility(View.GONE);
            imageViewLayout.setVisibility((View.GONE));
            imageView.setImageURI(imageUri);
        }
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
                editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(Journal.this);
        builder.setMessage("Save this page");
        builder.setCancelable(false);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                savePageFrontCoverImage();
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

    public void next_Go_Page_1() {
        Intent startNewActivity = new Intent(this, JournalPage1.class);
        startActivity(startNewActivity);
    }


    public static final String STORE_FRONT_COVER = "front_cover";
    public static final String FRONT_COVER_INFO = "front_cover_info";

    public void savePage1Title(){
        SharedPreferences spPage1 = getSharedPreferences(STORE_FRONT_COVER,MODE_PRIVATE);
        SharedPreferences.Editor edPage1 = spPage1.edit();
        edPage1.putString(FRONT_COVER_INFO+Login, editText.getText().toString());
        edPage1.apply();
    }
    public void load_title(){
        SharedPreferences page1 = getSharedPreferences(STORE_FRONT_COVER, MODE_PRIVATE);
        editText.setText(page1.getString(FRONT_COVER_INFO+Login,"").toString());
    }

    public static final String STORE_FRONT_COVER_IMAGE = "front_cover_image87987979";
    public static final String FRONT_COVER_IMAGE_INFO = "front_cover_image_info";
    String Image_String_Of_Base64;

    public void savePageFrontCoverImage(){
        if(imageView.getDrawable() != null){
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
            byte[] bb = bos.toByteArray();
            image_Convert_To_Base64 = Base64.encodeToString(bb, 0);
        }

        SharedPreferences spPage1 = getSharedPreferences(STORE_FRONT_COVER_IMAGE,MODE_PRIVATE);
        SharedPreferences.Editor edPage1 = spPage1.edit();
        edPage1.putString(FRONT_COVER_IMAGE_INFO+Login, image_Convert_To_Base64);
        edPage1.apply();
    }

    public void load_image(){

        SharedPreferences page1 = getSharedPreferences(STORE_FRONT_COVER_IMAGE, MODE_PRIVATE);
        Image_String_Of_Base64 = (page1.getString(FRONT_COVER_IMAGE_INFO+Login,"").toString());

        if(Image_String_Of_Base64 != ""){
            byte[] decodedString = Base64.decode(Image_String_Of_Base64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);
            addPhotoButton.setVisibility(View.GONE);
            imageViewLayout.setVisibility((View.GONE));
        }
    }
}