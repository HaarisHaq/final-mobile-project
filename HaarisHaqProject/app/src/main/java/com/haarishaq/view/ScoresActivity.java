package com.haarishaq.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListView;

import com.haarishaq.database.AppDatabase;
import com.haarishaq.database.Hiscore;
import com.haarishaq.database.HiscoreDAO;
import com.haarishaq.database.RecordAdapter;
import com.haarishaq.database.UserDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haaris Haq on 04/12/2017.
 */

public class ScoresActivity extends AppCompatActivity {
    int i = 0;
    private static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        db = AppDatabase.getDatabase(this);
        AppDatabase.spoofData();

        HiscoreDAO hiscore = db.hiscoreDAO();
        UserDAO user = db.userDAO();

        GridView scoresGridView = (GridView) findViewById(R.id.scoreGrid);
        ListView lv;
        List<Hiscore> scoreList = new ArrayList<>();
        scoreList.addAll(hiscore.getAllHiscores());
        scoresGridView.setAdapter(new RecordAdapter(this, scoreList));
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
