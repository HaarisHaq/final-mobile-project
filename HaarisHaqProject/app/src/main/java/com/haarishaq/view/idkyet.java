package com.haarishaq.view;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Haaris Haq on 02/01/2018.
 */

public class idkyet extends AppCompatActivity implements ViewGroup.OnClickListener {
    private static final String TAG = "async";
    private static Drawable loadedImage;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.idkyet);
        Button loadImage = findViewById(R.id.load_image_button);
        loadImage.setOnClickListener(this);
    }

    public void draw(Drawable d) {
        ImageView image = findViewById(R.id.inputStreamPic);
        image.setImageDrawable(d); //set picture
        image.invalidate(); //reload imageview
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.load_image_button) {
            try {
                DownloadFilesTask t = new DownloadFilesTask();
                t.execute(new URL("https://i.imgur.com/jVhMTO4.png"));
            } catch (Exception e) {
                Log.e(TAG, "error onclick asynctaskactivity: " + e.getMessage());
            }
            draw(loadedImage);
        }
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
        protected Long doInBackground(URL... urls) {
            try {
                Log.d(TAG, "src image: " + urls[0]);
                loadedImage = Drawable.createFromStream(urls[0].openStream(), "");
            } catch (IOException ioe) {
                Log.e(TAG, ioe.toString());
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            return Long.parseLong("0");
        }

        protected void onPostExecute(Long result) {
            Toast.makeText(getBaseContext(), "image loaded from web asynchronously", Toast.LENGTH_SHORT).show();
        }
    }

}
