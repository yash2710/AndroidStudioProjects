// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.android.gms.tagmanager:
//            bh, TagManager

public class PreviewActivity extends Activity
    implements TraceFieldInterface
{

    public PreviewActivity()
    {
    }

    private void d(String s, String s1, String s2)
    {
        AlertDialog alertdialog = (new android.app.AlertDialog.Builder(this)).create();
        alertdialog.setTitle(s);
        alertdialog.setMessage(s1);
        alertdialog.setButton(-1, s2, new _cls1());
        alertdialog.show();
    }

    public void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("PreviewActivity");
        TraceMachine.enterMethod(_nr_trace, "PreviewActivity#onCreate", null);
_L1:
        Intent intent;
        super.onCreate(bundle);
        bh.B("Preview activity");
        android.net.Uri uri = getIntent().getData();
        if (!TagManager.getInstance(this).i(uri))
        {
            String s = (new StringBuilder("Cannot preview the app with the uri: ")).append(uri).append(". Launching current version instead.").toString();
            bh.D(s);
            d("Preview failure", s, "Continue");
        }
        intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (intent != null)
        {
            NoSuchFieldError nosuchfielderror;
            try
            {
                bh.B((new StringBuilder("Invoke the launch activity for package name: ")).append(getPackageName()).toString());
                startActivity(intent);
            }
            catch (Exception exception)
            {
                bh.A((new StringBuilder("Calling preview threw an exception: ")).append(exception.getMessage()).toString());
            }
            TraceMachine.exitMethod();
            return;
        }
        break MISSING_BLOCK_LABEL_146;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "PreviewActivity#onCreate", null);
          goto _L1
        bh.B((new StringBuilder("No launch activity found for package name: ")).append(getPackageName()).toString());
        TraceMachine.exitMethod();
        return;
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

    private class _cls1
        implements android.content.DialogInterface.OnClickListener
    {

        final PreviewActivity agw;

        public void onClick(DialogInterface dialoginterface, int i)
        {
        }

        _cls1()
        {
            agw = PreviewActivity.this;
            super();
        }
    }

}
