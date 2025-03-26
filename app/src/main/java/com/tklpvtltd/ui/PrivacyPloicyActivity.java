package com.tklpvtltd.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.tklpvtltd.MainActivity;
import com.tklpvtltd.R;

public class PrivacyPloicyActivity extends AppCompatActivity {
    private WebView refundpolicy;
    private ImageView backpress;
    private String webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_ploicy);
        refundpolicy = findViewById(R.id.webview);
        backpress = findViewById(R.id.backbtn);

        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        getcontent();
    }

    private void getcontent() {
        webUrl = "https://tklpvtltd.com/privacy-policy";
        loadWebViewLoad(refundpolicy);
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebViewLoad(final WebView webview) {

        final ProgressDialog pd = ProgressDialog.show(PrivacyPloicyActivity.this, "", "Please wait...", true);
        pd.setCancelable(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setSupportMultipleWindows(true);
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //Toast.makeText(WebView.this, description, Toast.LENGTH_SHORT).show();
                view.setVisibility(View.VISIBLE);
                webview.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pd.show();

            }


            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
            }


        });
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl(webUrl);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), PrivacyPloicyActivity.class);
        startActivity(intent);
    }
}