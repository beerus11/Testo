package in.anuraggoel.testo.activities;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import in.anuraggoel.testo.R;
import in.anuraggoel.testo.TestApplication;
import in.anuraggoel.testo.manager.DatabaseHelper;
import in.anuraggoel.testo.models.User;
import in.anuraggoel.testo.utils.AppUtil;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private TextView mRegister;
    private Button mLogin;
    private EditText mPhoneNo, mPassword;
    private DatabaseHelper db;
    public static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = TestApplication.getDBHandler(this);
        mRegister = (TextView) findViewById(R.id.tvCreateAccount);
        mPhoneNo = (EditText) findViewById(R.id.etPhoneNo);
        mPassword = (EditText) findViewById(R.id.etPassword);
        mLogin = (Button) findViewById(R.id.btn_login);
        mLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticate();
            }
        });
        mRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }


    private void authenticate() {
        String password = mPassword.getText().toString();
        int phoneno;
        try {
            phoneno = Integer.parseInt(mPhoneNo.getText().toString());
        } catch (NumberFormatException exp) {
            showMessage("Invalid PhoneNo");
            return;
        }
        if (isValid(password)) {
            User user = db.getUserByPhoneNo(phoneno);
            showMessage("Authenticated");
            Log.d(TAG, user.toString());
            AppUtil.saveSession(user, this);
            startMainActivity();
        } else {
            showMessage("Invalid Details");
        }
    }

    private void registerUser() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private Boolean isValid(String password) {
        if (password != null && password != "")
            return true;
        else
            return false;
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

