// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.share;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.google.zxing.client.android.common.executor.AsyncTaskExecManager;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;

// Referenced classes of package com.google.zxing.client.android.share:
//            a, c

public final class AppPickerActivity extends ListActivity
    implements TraceFieldInterface
{

    private c a;
    private final AsyncTaskExecInterface b = (AsyncTaskExecInterface)(new AsyncTaskExecManager()).build();

    public AppPickerActivity()
    {
    }

    protected final void onListItemClick(ListView listview, View view, int i, long l)
    {
        ListAdapter listadapter = getListAdapter();
        if (i >= 0 && i < listadapter.getCount())
        {
            String s = ((a)listadapter.getItem(i)).a();
            Intent intent = new Intent();
            intent.addFlags(0x80000);
            intent.putExtra("url", (new StringBuilder("market://details?id=")).append(s).toString());
            setResult(-1, intent);
        } else
        {
            setResult(0);
        }
        finish();
    }

    protected final void onPause()
    {
        c c1 = a;
        if (c1 != null)
        {
            c1.cancel(true);
            a = null;
        }
        super.onPause();
    }

    protected final void onResume()
    {
        super.onResume();
        a = new c(this);
        b.execute(a, new Void[0]);
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
