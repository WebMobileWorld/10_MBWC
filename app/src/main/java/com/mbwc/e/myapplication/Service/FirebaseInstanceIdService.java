package com.mbwc.e.myapplication.Service;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by E on 11/13/2016.
 */

public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {
        private static final String REG_TOKEN = "REG_TOKEN" ;
    @Override
    public void onTokenRefresh(){
        String recent_token = FirebaseInstanceId.getInstance().getToken();
    }
}
