// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.flipkart.android.datahandler.DebugLogger;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.NetworkMonitor;
import com.flipkart.android.utils.ScreenMathUtils;
import java.util.ArrayList;
import org.json.JSONObject;

public class RukminiErrorReceiver extends BroadcastReceiver
{

    private ArrayList a;

    public RukminiErrorReceiver()
    {
        a = new ArrayList();
    }

    public void onReceive(Context context, Intent intent)
    {
        if (!AppConfigUtils.getInstance().getEnableBatch())
        {
            return;
        }
        try
        {
            String s = intent.getExtras().getString("url");
            a.clear();
            a.add(s);
            JSONObject jsonobject = new JSONObject();
            JSONObject jsonobject1 = new JSONObject();
            jsonobject1.put("connectionType", NetworkMonitor.getNetworkTypeVerbose());
            jsonobject1.put("networkOperatorName", NetworkMonitor.getNetworkOperatorName());
            jsonobject1.put("deviceFormFactor", ScreenMathUtils.getScreenDpiString(context));
            jsonobject1.put("imageUrls", a);
            jsonobject.put("event", "INVALID_IMAGE");
            jsonobject.put("data", jsonobject1);
            DebugLogger.logDebuggingJson(jsonobject);
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
