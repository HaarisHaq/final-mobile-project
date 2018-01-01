package com.haarishaq.database;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.haarishaq.view.R;

import java.util.List;

/**
 * Created by Haaris Haq on 05/12/2017.
 */

public class RecordAdapter extends BaseAdapter {

    AppDatabase db;
    private Context context;
    public static final int HISCORE = 0;
    public static final int USER = 1;
    public static final int OTHER = 2;

    private List list;

    private void initializeDB() {
        db = AppDatabase.getDatabase(context);
    }

    public RecordAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        initializeDB();
        return db.hiscoreDAO().getAllHiscores().size();
    }

    @Override
    public Object getItem(int position) {
        switch(position){
            case HISCORE:
                break;
            case USER:
                break;
            case OTHER:
                break;
        }
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
