package com.haarishaq.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Haaris Haq on 01/12/2017.
 */
@Entity
public class Hiscore {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE)
    public int userId;
    public String timeSet;
    public double timeTaken;
    public int score;

    public int getUserId() {
        return userId;
    }

    public Hiscore(int userId, String timeSet, double timeTaken, int score) {
        this.userId = userId;
        this.timeSet = timeSet;
        this.timeTaken = timeTaken;
        this.score = score;
    }
}
