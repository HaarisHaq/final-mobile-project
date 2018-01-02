package com.haarishaq.database;

import android.arch.persistence.room.*;

@Dao
public interface LogInDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addLogIn(LogIn login);

    @Query("select * from login")
    LogIn getLoggedIn();

    @Query("delete from login")
    void removeLogIn();
}
