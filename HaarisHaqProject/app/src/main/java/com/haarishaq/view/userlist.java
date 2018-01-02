package com.haarishaq.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haarishaq.database.AppDatabase;
import com.haarishaq.database.User;

import java.util.List;

/**
 * Created by Haaris Haq on 01/01/2018.
 */

public class userlist extends AppCompatActivity {

    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.userlist);
        AppDatabase db = AppDatabase.getDatabase(this);
        ListView lv = (ListView) findViewById(R.id.userList);
        List<User> lUser = db.userDAO().getAllUsers();
        String[] names = new String[lUser.size()];

        for(int i = 0; i< lUser.size(); i++){
            names[i] = lUser.get(i).userName;
        }
        lv.setAdapter(new ArrayAdapter<>(this, R.layout.newtext, names));
        db.close();
    }
}
