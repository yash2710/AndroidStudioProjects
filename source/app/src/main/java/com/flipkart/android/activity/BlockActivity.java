// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.flipkart.android.activity:
//            a

public class BlockActivity extends Activity
    implements TraceFieldInterface
{

    public static String BLOCK_ACTION_COMMAND = "com.flipkart.android.BlockActivity.KillOtherActivities";
    private Button a;

    public BlockActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("BlockActivity");
        TraceMachine.enterMethod(_nr_trace, "BlockActivity#onCreate", null);
_L1:
        requestWindowFeature(1);
        super.onCreate(bundle);
        sendBroadcast(new Intent(BLOCK_ACTION_COMMAND));
        setContentView(0x7f03001a);
        a = (Button)findViewById(0x7f0a006a);
        a.setOnClickListener(new a(this));
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "BlockActivity#onCreate", null);
          goto _L1
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
