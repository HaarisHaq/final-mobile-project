package com.haarishaq.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

        new DownloadFilesTask();
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
        protected Long doInBackground(URL... urls) {
            Long totalSize = Long.parseLong("1000");
            try {
                InputStream in = (InputStream) urls[0].getContent();
                Drawable d = Drawable.createFromStream(in, "killing_joke");
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                FileOutputStream out = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.close();
                in.close();
            } catch (IOException ioe) {
                Log.e(TAG, ioe.toString());
            }
            return totalSize;
        }

        protected void onPostExecute(Long result) {
            Toast.makeText(idkyet.this, "Downloaded " + result + " bytes", Toast.LENGTH_SHORT).show();
        }
    }

}
