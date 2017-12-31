package com.haarishaq.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    Button play, about, login, score, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.playButton);
        play.setOnClickListener(this);

        login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(this);

        register = (Button) findViewById(R.id.registrateButton);
        register.setOnClickListener(this);

        about = (Button) findViewById(R.id.aboutButton);
        about.setOnClickListener(this);

        score = (Button) findViewById(R.id.scoreButton);
        score.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                startActivity(new Intent(this, GameActivity.class));
                break;
            case R.id.loginButton:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.aboutButton:
                startActivity(new Intent( this, AboutActivity.class));
                break;
            case R.id.registrateButton:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.scoreButton:
                startActivity(new Intent(this, ScoresActivity.class));
                break;
        }
    }
}
