package com.haarishaq.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haarishaq.view.R;

import java.util.List;

/**
 * Created by Haaris Haq on 05/12/2017.
 */

public class RecordAdapter extends BaseAdapter {

    private static AppDatabase db;
    private Context context;

    private LayoutInflater inflater;

    private List<Hiscore> lHiscores;

    private void initializeDB() {
        db = AppDatabase.getDatabase(context);
    }

    public RecordAdapter(Context context, List<Hiscore> lHiscores) {
        this.context = context;
        this.lHiscores = lHiscores;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        initializeDB();
        return db.hiscoreDAO().getAllHiscores().size();
    }

    @Override
    public Object getItem(int position) {
        return lHiscores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //try {
            View listItemView;
            listItemView = inflater.inflate(R.layout.simplerow, null);
            TextView nameView = (TextView) listItemView.findViewById(R.id.nameColumn);
            TextView scoreView = (TextView) listItemView.findViewById(R.id.scoreColumn);

            nameView.setText(db.userDAO().getUser(lHiscores.get(position).userId).get(0).userName);
            scoreView.setText(lHiscores.get(position).score + "");
            return listItemView;/*
        } catch (Exception e) {
            Log.d("########3", "getView:" + e.getMessage());
            return new View(context);
        }*/
    }
}
