// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.instrumentation.io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListener;
import java.net.HttpURLConnection;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            TransactionState, HttpURLConnectionExtension

final class a
    implements StreamCompleteListener
{

    private TransactionState a;
    private HttpURLConnectionExtension b;

    a(HttpURLConnectionExtension httpurlconnectionextension, TransactionState transactionstate)
    {
        b = httpurlconnectionextension;
        a = transactionstate;
        super();
    }

    public final void streamComplete(StreamCompleteEvent streamcompleteevent)
    {
        if (!a.isComplete())
        {
            long l = HttpURLConnectionExtension.a(b).getContentLength();
            long l1 = streamcompleteevent.getBytes();
            if (l < 0L)
            {
                l = l1;
            }
            a.setBytesReceived(l);
            HttpURLConnectionExtension.a(b, a);
        }
    }

    public final void streamError(StreamCompleteEvent streamcompleteevent)
    {
        if (!a.isComplete())
        {
            a.setBytesReceived(streamcompleteevent.getBytes());
        }
        HttpURLConnectionExtension.a(b, streamcompleteevent.getException());
    }
}
