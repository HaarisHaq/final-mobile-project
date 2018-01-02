
package com.haarishaq.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.haarishaq.database.AppDatabase;
import com.haarishaq.database.User;

import java.util.List;
import java.util.regex.Pattern;


/**
 * A login screen that offers login via email/password.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mGivenNameView;
    private EditText mSurnameView;
    private EditText mUserNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mUserNameView = (EditText) findViewById(R.id.username);
        mGivenNameView = (EditText) findViewById(R.id.firstname);
        mSurnameView = (EditText) findViewById(R.id.surname);

        Button bRegistrate = (Button) findViewById(R.id.registrateButton);
        bRegistrate.setOnClickListener(this);

        Button btn = (Button) findViewById(R.id.userList);
        btn.setOnClickListener(this);
    }


    private boolean isEmailValid(String email) {
        String pattern = "[^\\ ]+@{1}[a-zA-Z\\d]+[\\.]{1}[a-zA-Z\\d]{1,5}";
        return Pattern.matches(pattern, email);
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registrateButton) {
            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();

            String userName = mUserNameView.getText().toString();
            String givenName = mGivenNameView.getText().toString();
            String surname = mSurnameView.getText().toString();
            if (isEmailValid(email) && isPasswordValid(password) &&
                    !userName.equals("") && !givenName.equals("") && !surname.equals("")) {
                AppDatabase db = AppDatabase.getDatabase(this);
                List<User> lUser = db.userDAO().getAllUsers();
                for (User u : lUser) {
                    if (u.userName.equals(userName) ||
                            u.email.equals(email)) {
                        return;
                    }
                }
                User mUser = new User(email, userName, givenName, surname, password);
                db.userDAO().addUser(mUser);
                db.close();
            }
        }
        if (v.getId() == R.id.userList) {
            startActivity(new Intent(this, userlist.class));
        }
    }
}
