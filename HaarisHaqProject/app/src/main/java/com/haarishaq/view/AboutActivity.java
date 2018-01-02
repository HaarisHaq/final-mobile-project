package com.haarishaq.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AboutActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findViewById(R.id.backButton).setOnClickListener(this);
        findViewById(R.id.contactUsButton).setOnClickListener(this);
        findViewById(R.id.officialGameButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton:
                finish();
                break;
            case R.id.contactUsButton:
                String number = "5198074810";
                Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null));
                startActivity(sendIntent);
                break;
            case R.id.officialGameButton:
                String link = "https://play.google.com/store/apps/details?id=com.cmplay.tiles2&hl=en";
                Intent officialGameIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(officialGameIntent);
        }
    }
}
