package com.laporwarga.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = 
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        
        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
            Toast.makeText(context, "⚠ Jaringan Terputus!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "✓ Jaringan Terhubung", Toast.LENGTH_SHORT).show();
        }
    }
}
