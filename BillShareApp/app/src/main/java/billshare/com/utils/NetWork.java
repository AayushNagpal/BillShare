package billshare.com.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetWork {
    private Context context;

    public NetWork(Context context) {
        this.context = context;
    }

    public boolean isNetWorkIsAvaliable() {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = connManager.getActiveNetworkInfo();

        if (i == null) {
            return false;
        } else if (i.getType() == ConnectivityManager.TYPE_WIFI || i.getType() == ConnectivityManager.TYPE_MOBILE || i.getType() == ConnectivityManager.TYPE_BLUETOOTH) {
            return true;
        }
        return false;

    }

    public boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("/system/bin/ping -c 1 8.8.8.8");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }
}
