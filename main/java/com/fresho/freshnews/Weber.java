package com.fresho.freshnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Weber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weber);


        String url = getIntent().getStringExtra("site");

        WebView webView = findViewById(R.id.web);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}