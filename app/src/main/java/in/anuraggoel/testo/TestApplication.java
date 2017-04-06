package in.anuraggoel.testo;

import android.app.Application;
import android.content.Context;

import in.anuraggoel.testo.manager.DatabaseHelper;

/**
 * Created by Anurag on 06-04-2017.
 */

public class TestApplication extends Application {
    private static DatabaseHelper db;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static DatabaseHelper getDBHandler(Context context) {
        if (db == null)
            db = new DatabaseHelper(context);
        return db;
    }
}
