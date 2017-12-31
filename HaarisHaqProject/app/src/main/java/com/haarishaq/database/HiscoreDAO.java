package com.haarishaq.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Haaris Haq on 01/12/2017.
 */
@Dao
public interface HiscoreDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addHiscore(Hiscore hiscore);

    @Query("select * from hiscore")
    public List<Hiscore> getAllHiscores();

    @Query("select * from hiscore where id = :hiscoreId")
    public List<Hiscore> getHiscore(int hiscoreId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateHiscore(Hiscore hiscore);

    @Query("delete from hiscore")
    void removeAllHiscores();

}
