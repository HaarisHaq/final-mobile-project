package com.haarishaq.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Haaris Haq on 01/12/2017.
 */

@Entity
public class User {

    @PrimaryKey
    public final int id;
    public String email;
    public String givenName;
    public String surname;
    public String userName;
    public String password;

    public static int count = 1000;

    @Ignore
    public User(String email, String userName, String givenName, String surname, String password) {
        this.id = count++;
        this.email = email;
        this.userName = userName;
        this.givenName = givenName;
        this.surname = surname;
        this.password = password;
    }
    public User(int id, String email, String userName, String givenName, String surname, String password) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.givenName = givenName;
        this.surname = surname;
        this.password = password;
    }
}
