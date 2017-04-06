package in.anuraggoel.testo.utils;

import android.content.Context;

import in.anuraggoel.testo.models.User;

/**
 * Created by Anurag on 06-04-2017.
 */

public class AppUtil {
    public static void saveSession(User user, Context context) {
        SettingUtils.set(context, Constants.PREF_TESTO_USER_LOGGEDIN, true);
        SettingUtils.set(context, Constants.PREF_TESTO_USER_NAME, user.getUserName());
        SettingUtils.set(context, Constants.PREF_TESTO_USER_PHONENO, user.getPhoneNo());

    }

    public static Boolean isSessionExist(Context context) {
        return SettingUtils.get(context, Constants.PREF_TESTO_USER_LOGGEDIN, false);
    }

    public static void delSession(Context context) {
        SettingUtils.getEditor(context).clear().commit();
    }
}
