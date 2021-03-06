package com.haarishaq.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Hiscore {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE)
    int userId;
    String timeSet;
    double timeTaken;
    int score;

    Hiscore(int userId, String timeSet, double timeTaken, int score) {
        this.userId = userId;
        this.timeSet = timeSet;
        this.timeTaken = timeTaken;
        this.score = score;
    }
}
