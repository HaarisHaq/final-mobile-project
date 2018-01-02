package com.haarishaq.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haarishaq.database.AppDatabase;

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

        if (AppDatabase.getDatabase(this).logInDAO().getLoggedIn() != null) {
            ViewGroup parent = (ViewGroup) login.getParent();
            Button logout = new Button(this);
            Button toAsyncPic = new Button(this);
            logout.setText(R.string.logout);
            logout.setLayoutParams(login.getLayoutParams());
            logout.setId(R.id.logOutButton);
            toAsyncPic.setText(R.string.pictureButton);
            toAsyncPic.setLayoutParams(login.getLayoutParams());
            toAsyncPic.setId(R.id.asyncPicButton);
            parent.removeView(login);
            parent.removeView(register);
            parent.addView(logout);
            parent.addView(toAsyncPic);
            logout.setOnClickListener(this);
            toAsyncPic.setOnClickListener(this);
        } else {
            ViewGroup parent = (ViewGroup) play.getParent();
            parent.removeView(play);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                startActivity(new Intent(this, GameActivity.class));
                break;
            case R.id.loginButton:
                startActivityForResult(new Intent(this, LoginActivity.class), 0);
                break;
            case R.id.aboutButton:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.registrateButton:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.scoreButton:
                startActivity(new Intent(this, ScoresActivity.class));
                break;
            case R.id.logOutButton:
                AppDatabase.getDatabase(this).logInDAO().removeLogIn();
                this.recreate();
                break;
            case R.id.asyncPicButton:
                startActivity(new Intent(this, idkyet.class));
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        recreate();
    }
}
