// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import android.os.AsyncTask;
import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.discovery.DiscoveryResponse;
import com.flipkart.android.volley.request.discovery.SearchRequest;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.flipkart.android.datahandler:
//            SearchVDataHander

final class l extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private SearchVDataHander a;

    l(SearchVDataHander searchvdatahander)
    {
        a = searchvdatahander;
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

    protected final transient DiscoveryResponse doInBackground(String as[])
    {
        return SearchVDataHander.a(a, as[0]);
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "l#doInBackground", null);
_L1:
        DiscoveryResponse discoveryresponse = doInBackground((String[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return discoveryresponse;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "l#doInBackground", null);
          goto _L1
    }

    protected final void onPostExecute(DiscoveryResponse discoveryresponse)
    {
        if (discoveryresponse != null)
        {
            a.resultReceived(discoveryresponse, true);
            return;
        } else
        {
            SearchRequest searchrequest = new SearchRequest(SearchVDataHander.a(a), a.listner, a.errorListner, SearchVDataHander.b(a), SearchVDataHander.c(a));
            a.request = searchrequest;
            FlipkartApplication.getRequestQueue().add(searchrequest);
            return;
        }
    }

    protected final volatile void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "l#onPostExecute", null);
_L1:
        onPostExecute((DiscoveryResponse)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "l#onPostExecute", null);
          goto _L1
    }
}
