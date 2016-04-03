// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android;

import android.os.AsyncTask;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.notification.GcmUtils;
import com.flipkart.logging.FkLogger;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.flipkart.android:
//            SplashActivity

final class a extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private String a;
    private SplashActivity b;

    a(SplashActivity splashactivity)
    {
        b = splashactivity;
        super();
    }

    public void _nr_setTrace(Trace trace)
    {
        try
        {
            _nr_trace = trace;
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    protected final transient Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "a#doInBackground", null);
_L2:
        GoogleCloudMessaging googlecloudmessaging;
        if (SplashActivity.g(b) == null)
        {
            SplashActivity.a(b, GoogleCloudMessaging.getInstance(FlipkartApplication.getAppContext()));
        }
        googlecloudmessaging = SplashActivity.g(b);
        if (googlecloudmessaging == null)
        {
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return null;
        }
        break; /* Loop/switch isn't completed */
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "a#doInBackground", null);
        if (true) goto _L2; else goto _L1
_L1:
        String s;
        try
        {
            a = SplashActivity.g(b).register(new String[] {
                "656085505957"
            });
            FlipkartPreferenceManager.instance().saveNotificationRegId(a);
            FlipkartPreferenceManager.instance().saveGcmIdSentToServerStatus(false);
            GcmUtils.sendRegistrationIdToBackend("launch");
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
        }
        s = a;
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return s;
    }
}
