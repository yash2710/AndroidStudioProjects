// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.text.MessageFormat;

public final class Carrier
{

    private static AgentLog a = AgentLogManager.getAgentLog();

    public Carrier()
    {
    }

    public static String nameFromContext(Context context)
    {
        ConnectivityManager connectivitymanager = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo networkinfo;
        try
        {
            networkinfo = connectivitymanager.getActiveNetworkInfo();
        }
        catch (SecurityException securityexception)
        {
            a.warning("Cannot determine network state. Enable android.permission.ACCESS_NETWORK_STATE in your manifest.");
            return "unknown";
        }
        if (networkinfo == null || !networkinfo.isConnected())
        {
            return "none";
        }
        switch (networkinfo.getType())
        {
        case 8: // '\b'
        default:
            AgentLog agentlog = a;
            Object aobj[] = new Object[2];
            aobj[0] = networkinfo.getTypeName();
            aobj[1] = Integer.valueOf(networkinfo.getType());
            agentlog.warning(MessageFormat.format("Unknown network type: {0} [{1}]", aobj));
            return "unknown";

        case 0: // '\0'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            String s = ((TelephonyManager)context.getSystemService("phone")).getNetworkOperatorName();
            boolean flag;
            if (Build.PRODUCT.equals("google_sdk") || Build.PRODUCT.equals("sdk") || Build.PRODUCT.equals("sdk_x86") || Build.FINGERPRINT.startsWith("generic"))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (s.equals("Android") && flag)
            {
                return "wifi";
            } else
            {
                return s;
            }

        case 1: // '\001'
        case 6: // '\006'
        case 7: // '\007'
        case 9: // '\t'
            return "wifi";
        }
    }

}
