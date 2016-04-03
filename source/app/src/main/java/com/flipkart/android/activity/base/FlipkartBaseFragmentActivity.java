// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity.base;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.flipkart.android.activity.BlockActivity;
import com.flipkart.android.analytics.TrackingUtil;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

public class FlipkartBaseFragmentActivity extends FragmentActivity
    implements TraceFieldInterface
{

    private KillReceiver a;

    public FlipkartBaseFragmentActivity()
    {
        getClass().getSimpleName();
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("FlipkartBaseFragmentActivity");
        TraceMachine.enterMethod(_nr_trace, "FlipkartBaseFragmentActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        a = new KillReceiver();
        registerReceiver(a, new IntentFilter(BlockActivity.BLOCK_ACTION_COMMAND));
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "FlipkartBaseFragmentActivity#onCreate", null);
          goto _L1
    }

    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(a);
    }

    protected void onPause()
    {
        super.onPause();
        TrackingUtil.stopActivity();
    }

    protected void onResume()
    {
        super.onResume();
        TrackingUtil.startActivity(this);
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

    private class KillReceiver extends BroadcastReceiver
    {

        private FlipkartBaseFragmentActivity a;

        public final void onReceive(Context context, Intent intent)
        {
            a.finish();
        }

        public KillReceiver()
        {
            a = FlipkartBaseFragmentActivity.this;
            super();
        }
    }

}
