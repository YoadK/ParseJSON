package com.example.parsejson.BroadcastReceiversPackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

// BroadcastReceiver are check if the phone are charged
public class BroadcastReceiverBattery3 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        assert action != null;
        switch (action) {
            case Intent.ACTION_POWER_CONNECTED:
                Toast.makeText(context, "The phone are charged", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
