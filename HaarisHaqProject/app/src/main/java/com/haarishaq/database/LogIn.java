package com.haarishaq.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class LogIn {
    @PrimaryKey
    @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE)
    int userId;

    public LogIn(int userId) {
        this.userId = userId;
    }
}
