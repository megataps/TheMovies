package com.megatap.themovies.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jackie on 12/17/14.
 */
public class NetworkUtils {

    /**
     * Checking for all possible Network providers
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable (final Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Checking for all possible GPS providers
     * @param context
     * @return
     */
    public static boolean isGPSAvailable(final Context context) {
        boolean result = true;
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                !manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            result = false;
        }
        return result;
    }
}
