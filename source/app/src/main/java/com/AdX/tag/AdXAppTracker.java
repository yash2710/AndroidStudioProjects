// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.AdX.tag;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.AdX.tag:
//            AdXConnect

public class AdXAppTracker extends BroadcastReceiver
{

    public AdXAppTracker()
    {
    }

    private static void a(Context context, Intent intent)
    {
        String s;
        String s3;
        s = "";
        PackageManager packagemanager;
        ComponentName componentname;
        ActivityInfo activityinfo;
        Set set;
        Iterator iterator;
        String s1;
        String s2;
        String s4;
        Exception exception2;
        try
        {
            packagemanager = context.getPackageManager();
        }
        catch (Exception exception)
        {
            Log.w("AdXAppTracker", "Unhandled exception while forwarding install intents.  Possibly lost some install information.", exception);
            return;
        }
        if (packagemanager == null) goto _L2; else goto _L1
_L1:
        componentname = new ComponentName(context, com/AdX/tag/AdXAppTracker);
        activityinfo = ((PackageManager)packagemanager).getReceiverInfo(componentname, 128);
        if (activityinfo == null) goto _L2; else goto _L3
_L3:
        if (activityinfo.metaData == null) goto _L2; else goto _L4
_L4:
        set = activityinfo.metaData.keySet();
        if (set == null) goto _L2; else goto _L5
_L5:
        iterator = ((Set)set).iterator();
_L8:
        if (!iterator.hasNext())
        {
            return;
        }
        s1 = (String)iterator.next();
        s2 = (String)s1;
        if (!s2.substring(0, 20).equalsIgnoreCase("ADX_FORWARD_REFERRAL"))
        {
            continue; /* Loop/switch isn't completed */
        }
        Log.i("AdXAppTracker", (new StringBuilder("Found Forwarding : ")).append(s2).toString());
        s4 = activityinfo.metaData.getString((String)s1);
        ((BroadcastReceiver)Class.forName(s4).newInstance()).onReceive(context, intent);
        Log.d("AdXAppTracker", (new StringBuilder("Successfully forwarded install notification to ")).append(s4).toString());
        s = s4;
        continue; /* Loop/switch isn't completed */
        exception2;
        s3 = s4;
_L6:
        Log.w("AdXAppTracker", (new StringBuilder("Could not forward Market's INSTALL_REFERRER intent to ")).append(s3).toString());
        s = s3;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        s3 = s;
        if (true) goto _L6; else goto _L2
_L2:
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void onReceive(Context context, Intent intent)
    {
        Log.i("AdXAppTracker", "Received Referrral Intent");
        String s = intent.toUri(0);
        if (s != null && s.length() > 0)
        {
            int i = s.indexOf("referrer=");
            if (i >= 0)
            {
                String s1 = s.substring(i, -4 + s.length());
                Log.i("AdXAppTracker", (new StringBuilder("Referral URI: ")).append(s1).toString());
                android.content.SharedPreferences.Editor editor = context.getSharedPreferences("AdXPrefrences", 0).edit();
                editor.putString("InstallReferral", s1);
                editor.commit();
                Log.i("AdXAppTracker", (new StringBuilder("Cached Referral URI: ")).append(s1).toString());
            } else
            {
                Log.i("AdXAppTracker", "No Referral URL.");
            }
        }
        AdXConnect.doBroadcastConnectInstance(context);
        a(context, intent);
        Log.i("AdXAppTracker", "End");
    }
}
