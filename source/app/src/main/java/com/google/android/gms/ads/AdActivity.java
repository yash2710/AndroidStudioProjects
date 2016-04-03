// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.internal.cm;
import com.google.android.gms.internal.cn;
import com.google.android.gms.internal.eu;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

public final class AdActivity extends Activity
    implements TraceFieldInterface
{

    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private cn kr;

    public AdActivity()
    {
    }

    private void S()
    {
        if (kr == null)
        {
            break MISSING_BLOCK_LABEL_16;
        }
        kr.S();
        return;
        RemoteException remoteexception;
        remoteexception;
        eu.c("Could not forward setContentViewSet to ad overlay:", remoteexception);
        return;
    }

    protected final void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("AdActivity");
        TraceMachine.enterMethod(_nr_trace, "AdActivity#onCreate", null);
_L2:
        super.onCreate(bundle);
        kr = cm.a(this);
        if (kr == null)
        {
            eu.D("Could not create ad overlay.");
            finish();
            TraceMachine.exitMethod();
            return;
        }
        break; /* Loop/switch isn't completed */
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "AdActivity#onCreate", null);
        if (true) goto _L2; else goto _L1
_L1:
        try
        {
            kr.onCreate(bundle);
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not forward onCreate to ad overlay:", remoteexception);
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
            if (kr != null)
            {
                kr.onDestroy();
            }
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not forward onDestroy to ad overlay:", remoteexception);
        }
        super.onDestroy();
    }

    protected final void onPause()
    {
        try
        {
            if (kr != null)
            {
                kr.onPause();
            }
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not forward onPause to ad overlay:", remoteexception);
            finish();
        }
        super.onPause();
    }

    protected final void onRestart()
    {
        super.onRestart();
        try
        {
            if (kr != null)
            {
                kr.onRestart();
            }
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not forward onRestart to ad overlay:", remoteexception);
        }
        finish();
    }

    protected final void onResume()
    {
        super.onResume();
        try
        {
            if (kr != null)
            {
                kr.onResume();
            }
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not forward onResume to ad overlay:", remoteexception);
        }
        finish();
    }

    protected final void onSaveInstanceState(Bundle bundle)
    {
        try
        {
            if (kr != null)
            {
                kr.onSaveInstanceState(bundle);
            }
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not forward onSaveInstanceState to ad overlay:", remoteexception);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    protected final void onStart()
    {
        ApplicationStateMonitor.getInstance().activityStarted();
        super.onStart();
        try
        {
            if (kr != null)
            {
                kr.onStart();
            }
            return;
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not forward onStart to ad overlay:", remoteexception);
        }
        finish();
    }

    protected final void onStop()
    {
        ApplicationStateMonitor.getInstance().activityStopped();
        try
        {
            if (kr != null)
            {
                kr.onStop();
            }
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not forward onStop to ad overlay:", remoteexception);
            finish();
        }
        super.onStop();
    }

    public final void setContentView(int i)
    {
        super.setContentView(i);
        S();
    }

    public final void setContentView(View view)
    {
        super.setContentView(view);
        S();
    }

    public final void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.setContentView(view, layoutparams);
        S();
    }
}
