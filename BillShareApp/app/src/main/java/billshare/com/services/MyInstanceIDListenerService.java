package billshare.com.services;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyInstanceIDListenerService extends FirebaseInstanceIdService {
    private static final String TAG="MyInstanceIDService";
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
      //  Log.d(TAG, "Refreshed token: " + refreshedToken);
       // super.onTokenRefresh();
    }
    private void saveTokenInPreferences(){

    }
}
