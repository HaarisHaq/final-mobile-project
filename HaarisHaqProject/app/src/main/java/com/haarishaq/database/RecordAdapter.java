package com.haarishaq.database;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.haarishaq.view.R;

/**
 * Created by Haaris Haq on 05/12/2017.
 */

public class RecordAdapter extends BaseAdapter {

    AppDatabase db;
    private Context context;

    private void initializeDB() {
        db = AppDatabase.getDatabase(context);
    }

    public RecordAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        initializeDB();
        return db.hiscoreDAO().getAllHiscores().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListView lv;
        if (convertView == null) {
            lv = new ListView(context);
            lv.setLayoutParams(new GridView.LayoutParams(85, 85));
            lv.setPadding(8, 8, 8, 8);
        } else {
            lv = (ListView) convertView;
        }
        lv.setAdapter(new ArrayAdapter<>(context, R.layout.simplerow, db.hiscoreDAO().getAllHiscores()));
        return lv;
    }
}
