package com.haarishaq.view;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Haaris Haq on 02/01/2018.
 */

public class idkyet extends AppCompatActivity {
    private static final String TAG = "async";
    final String FILENAME = "killing_joke.xml";

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.idkyet);
        draw(getResources().getDrawable(R.drawable.ic_launcher_foreground));
        try {
            new DownloadFilesTask().execute(new URL("https://i.imgur.com/jVhMTO4.png"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void draw(Drawable d) {
        ImageView image = findViewById(R.id.killingJokePic);
        image.setImageDrawable(d);
        image.invalidate();
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
        Drawable d;

        protected Long doInBackground(URL... urls) {
            try {
                InputStream in = urls[0].openStream();
                Log.d(TAG, "doInBackground: " + urls[0]);
                d = Drawable.createFromStream((InputStream) urls[0].getContent(), "");
                in.close();
            } catch (IOException ioe) {
                Log.e(TAG, ioe.toString());
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            return null;
        }

        protected void onPostExecute(Long result) {
            draw(d);
            Toast.makeText(idkyet.this, "image loaded from web asynchronously", Toast.LENGTH_SHORT).show();
        }
    }

}
