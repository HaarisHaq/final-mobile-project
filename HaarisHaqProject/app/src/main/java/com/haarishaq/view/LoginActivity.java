package com.haarishaq.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.haarishaq.database.AppDatabase;
import com.haarishaq.database.LogIn;
import com.haarishaq.database.User;

import java.util.regex.Pattern;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "####";
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        db = AppDatabase.getDatabase(this);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    private void attemptLogin() {
        if (db.logInDAO().getLoggedIn() != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        
        if (cancel) {
            focusView.requestFocus();
        } else {
            int userId = -1;
            AppDatabase.spoofData();
            for (User u : db.userDAO().getAllUsers()) {
                if (u.email.equals(email)) {
                    if (u.password.equals(password)) {
                        userId = u.id;
                    }
                    break;
                }
            }
            if (userId > 0) {
                db.logInDAO().addLogIn(new LogIn(userId));
                finish();
            } else {
                mPasswordView.setError("Password may be incorrect");
                mEmailView.setError("Email may be incorrect");
            }
        }
    }

    private boolean isEmailValid(String email) {
        String pattern = "[^\\ ]+@{1}[a-zA-Z\\d]+[\\.]{1}[a-zA-Z\\d]{1,5}";
        return Pattern.matches(pattern, email);
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


}

