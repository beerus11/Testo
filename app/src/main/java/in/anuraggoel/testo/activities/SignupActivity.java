package in.anuraggoel.testo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import in.anuraggoel.testo.R;
import in.anuraggoel.testo.TestApplication;
import in.anuraggoel.testo.exception.TestoException;
import in.anuraggoel.testo.manager.DatabaseHelper;
import in.anuraggoel.testo.models.User;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = SignupActivity.class.getSimpleName();
    private EditText mUserName, mPassword, mPhoneNo;
    private Button mLogin;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = TestApplication.getDBHandler(this);
        mUserName = (EditText) findViewById(R.id.tvUserName);
        mPassword = (EditText) findViewById(R.id.tvPassword);
        mPhoneNo = (EditText) findViewById(R.id.tvPhoneNo);
        mLogin = (Button) findViewById(R.id.btnLogin);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser() {
        String userName = mUserName.getText().toString();
        String password = mPassword.getText().toString();
        long phoneno;
        try {
            Log.d(TAG, "phone :: " + mPhoneNo.getText().toString().trim());
            phoneno = Long.parseLong(mPhoneNo.getText().toString().trim());
        } catch (NumberFormatException exp) {
            showMessage("Invalid PhoneNo");
            return;
        }
        if (isValid(userName, password)) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user.setPhoneNo(phoneno);
            if (db.createUser(user) > 0) {
                showMessage("User Created Successfully !");
                startMainActivity();
            } else {
                showMessage("Username or PhoneNo Already Exist!");
            }
        } else {
            showMessage("Invalid Details");
        }
    }

    private Boolean isValid(String userName, String password) {
        if (userName != null && userName != "" && password != null && password != "")
            return true;
        else
            return false;
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
