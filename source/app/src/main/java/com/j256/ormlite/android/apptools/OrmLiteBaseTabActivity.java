// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android.apptools;

import android.app.TabActivity;
import android.content.Context;
import android.os.Bundle;
import com.j256.ormlite.support.ConnectionSource;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.j256.ormlite.android.apptools:
//            OrmLiteSqliteOpenHelper, OpenHelperManager

public abstract class OrmLiteBaseTabActivity extends TabActivity
    implements TraceFieldInterface
{

    private volatile boolean created;
    private volatile boolean destroyed;
    private volatile OrmLiteSqliteOpenHelper helper;

    public OrmLiteBaseTabActivity()
    {
        created = false;
        destroyed = false;
    }

    public ConnectionSource getConnectionSource()
    {
        return getHelper().getConnectionSource();
    }

    public OrmLiteSqliteOpenHelper getHelper()
    {
        if (helper == null)
        {
            if (!created)
            {
                throw new IllegalStateException("A call has not been made to onCreate() yet so the helper is null");
            }
            if (destroyed)
            {
                throw new IllegalStateException("A call to onDestroy has already been made and the helper cannot be used after that point");
            } else
            {
                throw new IllegalStateException("Helper is null for some unknown reason");
            }
        } else
        {
            return helper;
        }
    }

    protected OrmLiteSqliteOpenHelper getHelperInternal(Context context)
    {
        return OpenHelperManager.getHelper(context);
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("OrmLiteBaseTabActivity");
        TraceMachine.enterMethod(_nr_trace, "OrmLiteBaseTabActivity#onCreate", null);
_L1:
        if (helper == null)
        {
            helper = getHelperInternal(this);
            created = true;
        }
        super.onCreate(bundle);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "OrmLiteBaseTabActivity#onCreate", null);
          goto _L1
    }

    protected void onDestroy()
    {
        super.onDestroy();
        releaseHelper(helper);
        destroyed = true;
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

    protected void releaseHelper(OrmLiteSqliteOpenHelper ormlitesqliteopenhelper)
    {
        OpenHelperManager.releaseHelper();
        helper = null;
    }
}
