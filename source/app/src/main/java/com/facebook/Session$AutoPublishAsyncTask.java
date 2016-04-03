// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.content.Context;
import android.os.AsyncTask;
import com.facebook.internal.Utility;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.facebook:
//            Settings, Session

class ext extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private final Context mApplicationContext;
    private final String mApplicationId;
    final Session this$0;

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

    protected volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "Session$AutoPublishAsyncTask#doInBackground", null);
_L1:
        Void void1 = doInBackground((Void[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return void1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "Session$AutoPublishAsyncTask#doInBackground", null);
          goto _L1
    }

    protected transient Void doInBackground(Void avoid[])
    {
        try
        {
            Settings.publishInstallAndWaitForResponse(mApplicationContext, mApplicationId, true);
        }
        catch (Exception exception)
        {
            Utility.logd("Facebook-publish", exception);
        }
        return null;
    }

    protected volatile void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "Session$AutoPublishAsyncTask#onPostExecute", null);
_L1:
        onPostExecute((Void)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "Session$AutoPublishAsyncTask#onPostExecute", null);
          goto _L1
    }

    protected void onPostExecute(Void void1)
    {
        synchronized (Session.this)
        {
            Session.access$1402(Session.this, null);
        }
    }

    public chine(String s, Context context)
    {
        this$0 = Session.this;
        super();
        mApplicationId = s;
        mApplicationContext = context.getApplicationContext();
    }
}
