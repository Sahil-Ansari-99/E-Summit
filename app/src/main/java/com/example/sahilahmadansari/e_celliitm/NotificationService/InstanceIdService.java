package com.example.sahilahmadansari.e_celliitm.NotificationService;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class InstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshToken=FirebaseInstanceId.getInstance().getToken();
        Log.e("Token", refreshToken);

    }
}
