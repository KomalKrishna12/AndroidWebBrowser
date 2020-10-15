package com.example.androidwebbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText searchURL;
    private LinearLayout facebookBtn, youtubeBtn, googleBtn, instagamBtn, twitterBtn, yahooBtn;
    private ImageView searchBtn;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadingBar = new ProgressDialog(this);

        searchBtn = findViewById(R.id.search_btn_home);
        searchURL = findViewById(R.id.search_url_home);
        facebookBtn = findViewById(R.id.facebook_btn);
        youtubeBtn = findViewById(R.id.youtube_btn);
        googleBtn = findViewById(R.id.google_btn);
        instagamBtn = findViewById(R.id.instagram_btn);
        twitterBtn = findViewById(R.id.twitter_btn);
        yahooBtn = findViewById(R.id.yahoo_btn);

        loadingBar.setTitle("Welcome");
        loadingBar.setMessage("Welcome to the Android Web Browser.");
        loadingBar.show();


        searchBtn.setOnClickListener(this);
        youtubeBtn.setOnClickListener(this);
        googleBtn.setOnClickListener(this);
        facebookBtn.setOnClickListener(this);
        instagamBtn.setOnClickListener(this);
        twitterBtn.setOnClickListener(this);
        yahooBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == searchBtn) {
            openWebsite();
        }

        if (view == youtubeBtn) {
            Intent intent = new Intent(HomeActivity.this, URLSearch.class);
            intent.putExtra("url_address", "https://www.youtube.com");
            startActivity(intent);
        }

        if (view == googleBtn) {
            Intent intent = new Intent(HomeActivity.this, URLSearch.class);
            intent.putExtra("url_address", "https://www.google.com");
            startActivity(intent);
        }

        if (view == twitterBtn) {
            Intent intent = new Intent(HomeActivity.this, URLSearch.class);
            intent.putExtra("url_address", "https://www.twitter.com");
            startActivity(intent);
        }

        if (view == facebookBtn) {
            Intent intent = new Intent(HomeActivity.this, URLSearch.class);
            intent.putExtra("url_address", "https://www.facebook.com");
            startActivity(intent);
        }

        if (view == yahooBtn) {
            Intent intent = new Intent(HomeActivity.this, URLSearch.class);
            intent.putExtra("url_address", "https://www.yahoo.com");
            startActivity(intent);
        }

        if (view == instagamBtn) {
            Intent intent = new Intent(HomeActivity.this, URLSearch.class);
            intent.putExtra("url_address", "https://www.instagram.com");
            startActivity(intent);
        }

    }

    private void openWebsite() {
        loadingBar.setTitle("loading...");
        loadingBar.setMessage("Please wait while we are opening required web address.");
        loadingBar.show();
        String searchStr = searchURL.getText().toString();
        if (searchStr.isEmpty()) {
            Toast.makeText(this, "Please enter URL or web address", Toast.LENGTH_SHORT).show();
        } else {
            String url_without_http = searchStr.replaceAll("https://www.", "");
            // String url_without_com = searchStr.replaceAll( ".com", "");
            String http = "https://";
            String www = "www.";
            //String com = ".com";
            Intent intent = new Intent(HomeActivity.this, URLSearch.class);
            intent.putExtra("url_address", http + www + url_without_http);
            startActivity(intent);

            searchURL.setText("");
            searchURL.requestFocus();
        }
    }
}