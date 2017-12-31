package com.haarishaq.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Haaris Haq on 01/12/2017.
 */

@Database(entities = {User.class, Hiscore.class}, version = 16, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract HiscoreDAO hiscoreDAO();

    public abstract UserDAO userDAO();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "userdatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public static void spoofData() {
        User[] users = {
                new User(1,"pen@mail.com","PenPal","Pen", "Pen","asdf123"),
                new User(2, "Stylus@mail.com", "loseraccount", "Stylus", "Letter","asdf1234"),
                new User(12, "Charcoal@mail.com", "wishiwasink", "Charcoal","Book", "asdf1234"),
                new User(67, "pencil@mail.com", "almostadiamond", "Graphite","lastname", "asdf1234")
        };

        Hiscore[] scores = {
                new Hiscore(12, "12:00", 1.25, 200),
                new Hiscore(1, "1:00", 8.25, 60),
                new Hiscore(2, "2:40", 5.0, 2000),
                new Hiscore(67, "18:00", 1.2, 800),
                new Hiscore(12, "8:00", .8, 9001)
        };

        if (INSTANCE.hiscoreDAO().getAllHiscores().size() > 0) {
            INSTANCE.hiscoreDAO().removeAllHiscores();
        }

        for (User u : users) {
            INSTANCE.userDAO().addUser(u);
        }
        for (Hiscore s : scores) {
            INSTANCE.hiscoreDAO().addHiscore(s);
        }

    }
}
