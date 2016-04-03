// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.ads.purchase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.dd;
import com.google.android.gms.internal.di;
import com.google.android.gms.internal.eu;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

public final class InAppPurchaseActivity extends Activity
    implements TraceFieldInterface
{

    public static final String CLASS_NAME = "com.google.android.gms.ads.purchase.InAppPurchaseActivity";
    public static final String SIMPLE_CLASS_NAME = "InAppPurchaseActivity";
    private dd tb;

    public InAppPurchaseActivity()
    {
    }

    protected final void onActivityResult(int i, int j, Intent intent)
    {
        try
        {
            if (tb != null)
            {
                tb.onActivityResult(i, j, intent);
            }
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not forward onActivityResult to in-app purchase manager:", remoteexception);
        }
        super.onActivityResult(i, j, intent);
    }

    protected final void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("InAppPurchaseActivity");
        TraceMachine.enterMethod(_nr_trace, "InAppPurchaseActivity#onCreate", null);
_L2:
        super.onCreate(bundle);
        tb = di.d(this);
        if (tb == null)
        {
            eu.D("Could not create in-app purchase manager.");
            finish();
            TraceMachine.exitMethod();
            return;
        }
        break; /* Loop/switch isn't completed */
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "InAppPurchaseActivity#onCreate", null);
        if (true) goto _L2; else goto _L1
_L1:
        try
        {
            tb.onCreate();
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not forward onCreate to in-app purchase manager:", remoteexception);
            finish();
            TraceMachine.exitMethod();
            return;
        }
        TraceMachine.exitMethod();
        return;
    }

    protected final void onDestroy()
    {
        try
        {
            if (tb != null)
            {
                tb.onDestroy();
            }
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not forward onDestroy to in-app purchase manager:", remoteexception);
        }
        super.onDestroy();
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop()
    {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }
}
