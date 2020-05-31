package com.example.covid19trackerapp;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static androidx.core.content.ContextCompat.getSystemService;

public class ConnectionCheck {
    public static boolean checkConnection(Context context){

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else {
            connected = false;
        }
        return connected;
    }


}
