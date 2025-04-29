package com.xcstats;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewTrack_WebViewActivity extends AppCompatActivity {


    @BindView(R.id.webback_IMV)
    ImageView webback_IMV;

    @BindView(R.id.webtext)
    TextView webtext;

    @BindView(R.id.viewtrack_WV)
    WebView viewtrack_WV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_track_web_view);
        ButterKnife.bind(this);

        WebView webView = (WebView) findViewById(R.id.viewtrack_WV);
        String url = getIntent().getStringExtra("indoorUrl");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (url != null) {
            webView.loadUrl(url);
        }
        WebView outdoorweb = (WebView) findViewById(R.id.viewtrack_WV);
        String ourdoorurl = getIntent().getStringExtra("outdoorUrl");
        WebSettings webSettings1 = outdoorweb.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        if (ourdoorurl != null) {
            outdoorweb.loadUrl(ourdoorurl);

        }
    }

    @OnClick(R.id.webback_IMV)
    public void webback_IMV() {
        onBackPressed();
    }
}