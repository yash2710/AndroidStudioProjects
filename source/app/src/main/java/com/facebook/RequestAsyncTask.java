// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

// Referenced classes of package com.facebook:
//            RequestBatch, Request, Settings

public class RequestAsyncTask extends AsyncTask
    implements TraceFieldInterface
{

    private static final String TAG = com/facebook/RequestAsyncTask.getCanonicalName();
    private static Method executeOnExecutorMethod;
    public Trace _nr_trace;
    private final HttpURLConnection connection;
    private Exception exception;
    private final RequestBatch requests;

    public RequestAsyncTask(RequestBatch requestbatch)
    {
        this(null, requestbatch);
    }

    public RequestAsyncTask(HttpURLConnection httpurlconnection, RequestBatch requestbatch)
    {
        requests = requestbatch;
        connection = httpurlconnection;
    }

    public RequestAsyncTask(HttpURLConnection httpurlconnection, Collection collection)
    {
        this(httpurlconnection, new RequestBatch(collection));
    }

    public transient RequestAsyncTask(HttpURLConnection httpurlconnection, Request arequest[])
    {
        this(httpurlconnection, new RequestBatch(arequest));
    }

    public RequestAsyncTask(Collection collection)
    {
        this(null, new RequestBatch(collection));
    }

    public transient RequestAsyncTask(Request arequest[])
    {
        this(null, new RequestBatch(arequest));
    }

    public void _nr_setTrace(Trace trace)
    {
        try
        {
            _nr_trace = trace;
            return;
        }
        catch (Exception exception1)
        {
            return;
        }
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "RequestAsyncTask#doInBackground", null);
_L1:
        List list = doInBackground((Void[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return list;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "RequestAsyncTask#doInBackground", null);
          goto _L1
    }

    protected transient List doInBackground(Void avoid[])
    {
        List list;
        try
        {
            if (connection == null)
            {
                return requests.executeAndWait();
            }
            list = Request.executeConnectionAndWait(connection, requests);
        }
        catch (Exception exception1)
        {
            exception = exception1;
            return null;
        }
        return list;
    }

    RequestAsyncTask executeOnSettingsExecutor()
    {
        if (executeOnExecutorMethod == null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        Method method = executeOnExecutorMethod;
        Object aobj[] = new Object[2];
        aobj[0] = Settings.getExecutor();
        aobj[1] = null;
        method.invoke(this, aobj);
        return this;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
_L2:
        Void avoid[] = new Void[0];
        if (!(this instanceof AsyncTask))
        {
            execute(avoid);
            return this;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)this, avoid);
            return this;
        }
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected final Exception getException()
    {
        return exception;
    }

    protected final RequestBatch getRequests()
    {
        return requests;
    }

    protected volatile void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "RequestAsyncTask#onPostExecute", null);
_L1:
        onPostExecute((List)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "RequestAsyncTask#onPostExecute", null);
          goto _L1
    }

    protected void onPostExecute(List list)
    {
        super.onPostExecute(list);
        if (exception != null)
        {
            String s = TAG;
            Object aobj[] = new Object[1];
            aobj[0] = exception.getMessage();
            FkLogger.debug(s, String.format("onPostExecute: exception encountered during request: %s", aobj));
        }
    }

    protected void onPreExecute()
    {
        super.onPreExecute();
        if (requests.getCallbackHandler() == null)
        {
            requests.setCallbackHandler(new Handler());
        }
    }

    public String toString()
    {
        return (new StringBuilder("{RequestAsyncTask: ")).append(" connection: ").append(connection).append(", requests: ").append(requests).append("}").toString();
    }

    static 
    {
        Method amethod[] = android/os/AsyncTask.getMethods();
        int i = amethod.length;
        int j = 0;
        do
        {
label0:
            {
                if (j < i)
                {
                    Method method = amethod[j];
                    if (!"executeOnExecutor".equals(method.getName()))
                    {
                        break label0;
                    }
                    Class aclass[] = method.getParameterTypes();
                    if (aclass.length != 2 || aclass[0] != java/util/concurrent/Executor || !aclass[1].isArray())
                    {
                        break label0;
                    }
                    executeOnExecutorMethod = method;
                }
                return;
            }
            j++;
        } while (true);
    }
}
