package com.example.eric.tutorversity.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
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

import com.android.volley.Response;
import com.example.eric.tutorversity.OurSingleton;
import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.Tutor;
import com.example.eric.tutorversity.models.User;
import com.example.eric.tutorversity.models.api.request.AuthRequest;
import com.example.eric.tutorversity.models.api.request.LogoutRequest;
import com.example.eric.tutorversity.models.api.response.AuthResponse;
import com.example.eric.tutorversity.models.api.response.LogoutResponse;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private LoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        if (getIntent() != null && getIntent().getExtras() != null)
        {
            String userJSON = getIntent().getExtras().getString(USER);
            User user = new User(userJSON);
            LogoutTask logoutTask = new LogoutTask(user);
            logoutTask.makeRequest();
        }

        mPasswordView = (EditText) findViewById(R.id.password);
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
                findViewById(R.id.loginMessage).setVisibility(View.GONE);
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
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
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
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
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new LoginTask(email, password);
            mAuthTask.makeRequest();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 1;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class LoginTask implements Response.Listener<AuthResponse> {

        private final String mEmail;
        private final String mPassword;

        LoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        protected Void makeRequest() {
            AuthRequest authRequest = new AuthRequest(mEmail, mPassword);
            OurSingleton.getInstance(
                    getApplicationContext()).addToRequestQueue(getApplicationContext(),
                    authRequest.makeRequest(this));
            return null;
        }

        protected void onComplete(AuthResponse response) {
            mAuthTask = null;
            showProgress(false);

            if (response.getSuccess()) {
                if (response.getUser() instanceof Student)
                {
                    Intent intent = new Intent(getBaseContext(), StudentDashboard.class);
                    intent = intent.putExtra(USER, response.getUser().toJSON().toString());
                    startActivity(intent);
                    finish();
                }
                else if (response.getUser() instanceof Tutor)
                {
                    Intent intent = new Intent(getBaseContext(), TutorDashboard.class);
                    intent.putExtra(USER, response.getUser().toJSON().toString());
                    startActivity(intent);
                }
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        public void onResponse(AuthResponse response) {
            Log.d("I", Boolean.toString(response.getSuccess()));
            onComplete(response);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class LogoutTask implements Response.Listener<LogoutResponse> {

        private User user;
        LogoutTask(User user) {
            this.user = user;
        }

        protected void makeRequest() {
            LogoutRequest logoutRequest = new LogoutRequest(user);
            OurSingleton.getInstance(
                    getApplicationContext()).addToRequestQueue(getApplicationContext(),
                    logoutRequest.makeRequest(this));
        }

        protected void onComplete(LogoutResponse response) {
            mAuthTask = null;
            showProgress(false);

            if (response.getSuccess()) {
                findViewById(R.id.loginMessage).setVisibility(View.VISIBLE);
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        public void onResponse(LogoutResponse response) {
            Log.d("I", Boolean.toString(response.getSuccess()));
            onComplete(response);
        }
    }
}

