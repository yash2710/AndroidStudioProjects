// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connectivity
{

    public Connectivity()
    {
    }

    public static String getConnection(Context context)
    {
        if (!isConnected(context))
        {
            return Connection.NO_CONNECTIVITY.getName();
        }
        if (!isConnectedWifi(context))
        {
            return Connection.MOBILE.getName();
        } else
        {
            return Connection.WIFI.getName();
        }
    }

    public static String getConnectionSpeed(Context context)
    {
        if (!isConnected(context))
        {
            return ConnectionSpeed.NO_CONNECTIVITY.getName();
        }
        if (!isConnectedFast(context))
        {
            return ConnectionSpeed.SLOW.getName();
        } else
        {
            return ConnectionSpeed.FAST.getName();
        }
    }

    public static NetworkInfo getNetworkInfo(Context context)
    {
        return ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean isConnected(Context context)
    {
        NetworkInfo networkinfo = getNetworkInfo(context);
        return networkinfo != null && networkinfo.isConnected();
    }

    public static boolean isConnectedFast(Context context)
    {
        NetworkInfo networkinfo = getNetworkInfo(context);
        return networkinfo != null && networkinfo.isConnected() && isConnectionFast(networkinfo.getType(), networkinfo.getSubtype());
    }

    public static boolean isConnectedMobile(Context context)
    {
        NetworkInfo networkinfo = getNetworkInfo(context);
        return networkinfo != null && networkinfo.isConnected() && networkinfo.getType() == 0;
    }

    public static boolean isConnectedWifi(Context context)
    {
        NetworkInfo networkinfo = getNetworkInfo(context);
        return networkinfo != null && networkinfo.isConnected() && networkinfo.getType() == 1;
    }

    public static boolean isConnectionFast(int i, int j)
    {
        if (i != 1) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (i != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        switch (j)
        {
        default:
            return false;

        case 7: // '\007'
            return false;

        case 4: // '\004'
            return false;

        case 2: // '\002'
            return false;

        case 1: // '\001'
            return false;

        case 11: // '\013'
            return false;

        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
            break;
        }
        if (true) goto _L1; else goto _L3
_L3:
        return false;
    }

    private class Connection extends Enum
    {

        public static final Connection MOBILE;
        public static final Connection NO_CONNECTIVITY;
        public static final Connection WIFI;
        private static final Connection b[];
        private String a;

        public static Connection valueOf(String s)
        {
            return (Connection)Enum.valueOf(com/flipkart/fk_android_batchnetworking/Connectivity$Connection, s);
        }

        public static Connection[] values()
        {
            return (Connection[])b.clone();
        }

        public final String getName()
        {
            return a;
        }

        static 
        {
            NO_CONNECTIVITY = new Connection("NO_CONNECTIVITY", 0, "no_connection");
            MOBILE = new Connection("MOBILE", 1, "mobile");
            WIFI = new Connection("WIFI", 2, "wifi");
            Connection aconnection[] = new Connection[3];
            aconnection[0] = NO_CONNECTIVITY;
            aconnection[1] = MOBILE;
            aconnection[2] = WIFI;
            b = aconnection;
        }

        private Connection(String s, int i, String s1)
        {
            super(s, i);
            a = s1;
        }
    }


    private class ConnectionSpeed extends Enum
    {

        public static final ConnectionSpeed FAST;
        public static final ConnectionSpeed NO_CONNECTIVITY;
        public static final ConnectionSpeed SLOW;
        private static final ConnectionSpeed b[];
        private String a;

        public static ConnectionSpeed valueOf(String s)
        {
            return (ConnectionSpeed)Enum.valueOf(com/flipkart/fk_android_batchnetworking/Connectivity$ConnectionSpeed, s);
        }

        public static ConnectionSpeed[] values()
        {
            return (ConnectionSpeed[])b.clone();
        }

        public final String getName()
        {
            return a;
        }

        static 
        {
            NO_CONNECTIVITY = new ConnectionSpeed("NO_CONNECTIVITY", 0, "no_connection");
            SLOW = new ConnectionSpeed("SLOW", 1, "slow");
            FAST = new ConnectionSpeed("FAST", 2, "fast");
            ConnectionSpeed aconnectionspeed[] = new ConnectionSpeed[3];
            aconnectionspeed[0] = NO_CONNECTIVITY;
            aconnectionspeed[1] = SLOW;
            aconnectionspeed[2] = FAST;
            b = aconnectionspeed;
        }

        private ConnectionSpeed(String s, int i, String s1)
        {
            super(s, i);
            a = s1;
        }
    }

}
