package ahhhlvin.c4q.nyc.retrofitstackoverflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_web_view);


        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(getIntent().getStringExtra("question_link"));
    }
}
