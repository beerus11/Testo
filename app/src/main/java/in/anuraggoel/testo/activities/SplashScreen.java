package in.anuraggoel.testo.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.anuraggoel.testo.R;
import in.anuraggoel.testo.TestApplication;
import in.anuraggoel.testo.manager.DatabaseHelper;
import in.anuraggoel.testo.models.Product;
import in.anuraggoel.testo.utils.AppUtil;
import in.anuraggoel.testo.utils.ProductUtil;

public class SplashScreen extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 3000;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        db = TestApplication.getDBHandler(this);
        if (!db.checkProductExist()) {
            //Adding Dummy Data in Product Table
            ProductUtil.addData(db);
        }
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (AppUtil.isSessionExist(SplashScreen.this))
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                else
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
