package com.bnucist2016cs.xieshuzhao.partybranchworkbook.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by User on 2018/5/5.
 */

public class CommonUtil {

        public static boolean isNetworkAvailable(LoginActivity context) {
            NetworkInfo info = getNetworkInfo(context);
            if (info != null) {
                return info.isAvailable();
            }
            return false;
        }

        public static boolean isWifi(Context context) {
            NetworkInfo info = getNetworkInfo(context);
            if (info != null) {
                if (info.getType() == ConnectivityManager.TYPE_WIFI)
                    return true;
            }
            return false;
        }

        public static boolean isMobile(Context context) {
            NetworkInfo info = getNetworkInfo(context);
            if (info != null) {
                if (info.getType() == ConnectivityManager.TYPE_MOBILE)
                    return true;
            }
            return false;
        }

        private static NetworkInfo getNetworkInfo(Context context) {

            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo();
        }

        public static boolean checkSdCard() {
            if (android.os.Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED))
                return true;
            else
                return false;
        }

    }

