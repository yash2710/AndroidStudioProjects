// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.flipkart.logging.FkLogger;

public class NetworkMonitor extends BroadcastReceiver
{

    public static final int NETWORK_TYPE_FAST_3G = 2;
    public static final int NETWORK_TYPE_FAST_WIFI = 1;
    public static final int NETWORK_TYPE_NO_NETWORK = 4;
    public static final int NETWORK_TYPE_SLOW = 3;
    private static boolean a = false;
    private static Context b;

    public NetworkMonitor()
    {
    }

    public static void checkNetworkConnectivity(Context context)
    {
        if (context != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j;
        boolean flag;
        NetworkInfo anetworkinfo[];
        int i;
        NetworkInfo networkinfo;
        try
        {
            anetworkinfo = ((ConnectivityManager)context.getSystemService("connectivity")).getAllNetworkInfo();
            i = anetworkinfo.length;
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
            return;
        }
        j = 0;
_L5:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_105;
        }
        networkinfo = anetworkinfo[j];
        if (!networkinfo.isConnected() || networkinfo.getType() == 2) goto _L4; else goto _L3
_L3:
        flag = true;
_L6:
        FkLogger.info("NetworkMonitor", (new StringBuilder("Network Availability: ")).append(flag).toString());
        if (flag != a)
        {
            a = flag;
            return;
        }
          goto _L1
_L4:
        j++;
          goto _L5
        flag = false;
          goto _L6
    }

    public static String getNetworkOperatorName()
    {
        return ((TelephonyManager)b.getSystemService("phone")).getNetworkOperatorName();
    }

    public static NetworkInfo getNetworkType(Context context)
    {
        if (context != null)
        {
            NetworkInfo networkinfo;
            try
            {
                networkinfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
            }
            catch (Exception exception)
            {
                return null;
            }
            return networkinfo;
        } else
        {
            return null;
        }
    }

    public static String getNetworkTypeVerbose()
    {
        int i = isNetworkFast();
        if (i == 3)
        {
            return "2G";
        }
        if (i == 2)
        {
            return "3G";
        }
        if (i == 1)
        {
            return "WiFi";
        } else
        {
            return "unknown";
        }
    }

    public static void initialize(Context context)
    {
        b = context;
    }

    public static boolean isNetworkAvailable()
    {
        checkNetworkConnectivity(b);
        return a;
    }

    public static int isNetworkFast()
    {
        if (b != null)
        {
            ConnectivityManager connectivitymanager = (ConnectivityManager)b.getSystemService("connectivity");
            if (connectivitymanager != null)
            {
                NetworkInfo networkinfo = connectivitymanager.getNetworkInfo(1);
                if (networkinfo != null && networkinfo.isConnected())
                {
                    return 1;
                }
            }
            int i = ((TelephonyManager)b.getSystemService("phone")).getNetworkType();
            if (i > 0 && i < 3)
            {
                return 3;
            }
            return i <= 2 ? 4 : 2;
        } else
        {
            return -1;
        }
    }

    public static boolean isNetworkPresent()
    {
        ConnectivityManager connectivitymanager;
        boolean flag;
        connectivitymanager = (ConnectivityManager)b.getSystemService("connectivity");
        flag = false;
        if (connectivitymanager == null) goto _L2; else goto _L1
_L1:
        NetworkInfo anetworkinfo[];
        anetworkinfo = connectivitymanager.getAllNetworkInfo();
        flag = false;
        if (anetworkinfo == null) goto _L2; else goto _L3
_L3:
        int i = 0;
_L8:
        int j;
        j = anetworkinfo.length;
        flag = false;
        if (i >= j) goto _L2; else goto _L4
_L4:
        if (anetworkinfo[i].getState() != android.net.NetworkInfo.State.CONNECTED) goto _L6; else goto _L5
_L5:
        flag = true;
_L2:
        return flag;
_L6:
        i++;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static void setNetworkAvailable(boolean flag)
    {
        a = flag;
    }

    public void onReceive(Context context, Intent intent)
    {
        FkLogger.info("NetworkMonitor", (new StringBuilder("Broadcast Received --- Context is ")).append(context).append("   Intent is ").append(intent).toString());
        checkNetworkConnectivity(context);
    }

}
