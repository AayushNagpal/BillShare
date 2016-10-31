package billshare.com.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

import billshare.com.model.User;

public class PreferenceUtil {
    private static PreferenceUtil preferenceUtil;

    private Context context;


    private PreferenceUtil(Context context) {
        this.context = context;
    }

    public static PreferenceUtil instance(Context context) {
        if (preferenceUtil == null) {
            preferenceUtil = new PreferenceUtil(context);

        }
        return preferenceUtil;
    }

    private SharedPreferences getPreferenceObject() {
        return PreferenceManager
                .getDefaultSharedPreferences(context);
    }

    private SharedPreferences.Editor getPreferenceEditorObject() {
        return getPreferenceObject().edit();
    }

    public void setSPreferences(User user) {

        SharedPreferences.Editor editor = getPreferenceEditorObject();
        editor.putString(StringConstants.NAME, user.getName());
        editor.putString(StringConstants.EMAILID, user.getEmailId());
        editor.putInt(StringConstants.USER_ID, user.getId());
        editor.apply();
        editor.commit();

    }

    public void clearSPreferences() {
        SharedPreferences.Editor editor = getPreferenceEditorObject();
        editor.clear();
        editor.apply();
        editor.commit();

    }

    public boolean isNotExistSPreferences() {

        return getPreferenceObject().getAll().isEmpty();
    }
}
