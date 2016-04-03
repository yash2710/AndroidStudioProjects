// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.crittercism.app.Crittercism;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

public class NotificationActivity extends Activity
    implements android.view.View.OnClickListener, android.view.View.OnTouchListener, TraceFieldInterface
{

    public NotificationActivity()
    {
    }

    public void onClick(View view)
    {
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("NotificationActivity");
        TraceMachine.enterMethod(_nr_trace, "NotificationActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null && bundle1.containsKey("com.crittercism.notification"))
        {
            setTheme(0x103000b);
            requestWindowFeature(1);
            String s = bundle1.getString("com.crittercism.notification");
            Crittercism crittercism = Crittercism.a();
            LinearLayout linearlayout = new LinearLayout(this);
            linearlayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
            linearlayout.setOrientation(0);
            int i = crittercism.y();
            linearlayout.setPadding(i, i, i, i);
            linearlayout.setId(13);
            linearlayout.setOnClickListener(this);
            linearlayout.setOnTouchListener(this);
            TextView textview = new TextView(this);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -2);
            layoutparams.setMargins(0, 0, crittercism.y(), 0);
            textview.setLayoutParams(layoutparams);
            textview.setTextSize(16F);
            textview.setTextColor(-1);
            textview.setId(51);
            textview.setText((new StringBuilder()).append(Crittercism.getNotificationTitle()).append(": ").append(s).toString());
            linearlayout.addView(textview);
            setContentView(linearlayout);
        }
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "NotificationActivity#onCreate", null);
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

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        finish();
        return true;
    }
}
