package com.example.androidwebbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class URLSearch extends AppCompatActivity implements View.OnClickListener {
    private EditText searchUrl;
    private ImageView searchBtn;
    private ImageView homeBtn;
    private WebView searchWebAddress;
    private String URL;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_r_l_search);

        loadingBar = new ProgressDialog(this);

        searchBtn = findViewById(R.id.search_btn);
        homeBtn = findViewById(R.id.home_btn);
        searchUrl = findViewById(R.id.search_url);
        searchWebAddress = findViewById(R.id.search_website);

        URL = getIntent().getExtras().get("url_address").toString();
        searchUrl.setText(URL);

        WebSettings webSettings = searchWebAddress.getSettings();
        webSettings.setJavaScriptEnabled(true);
        searchWebAddress.loadUrl(URL);
        searchWebAddress.setWebViewClient(new WebViewClient());

        loadingBar.setTitle("loading...");
        loadingBar.setMessage("Please wait while we are opening required web address.");
        loadingBar.show();

        searchBtn.setOnClickListener(this);
        homeBtn.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        if (searchWebAddress.canGoBack()) {
            searchWebAddress.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {

        if (view == searchBtn) {
            searchWebAddressMethod();
        }

        if (view == homeBtn) {
            Intent intent = new Intent(URLSearch.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void searchWebAddressMethod() {
        loadingBar.setTitle("loading...");
        loadingBar.setMessage("Please wait while we are opening required web address.");
        loadingBar.show();
        String searchStr = searchUrl.getText().toString();
        if (searchStr.isEmpty()) {
            Toast.makeText(this, "Please enter URL or web address", Toast.LENGTH_SHORT).show();
        } else {
            String url_without_http = searchStr.replaceAll("https://www.", "");
            // String url_without_com = searchStr.replaceAll( ".com", "");
            String http = "https://";
            String www = "www.";
            //String com = ".com";
            Intent intent = new Intent(URLSearch.this, URLSearch.class);
            intent.putExtra("url_address", http + www + url_without_http);
            startActivity(intent);

            searchUrl.setText("");
            searchUrl.requestFocus();
        }
    }
}