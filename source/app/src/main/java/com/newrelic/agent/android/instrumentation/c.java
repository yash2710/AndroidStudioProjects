// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.instrumentation.io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListener;
import javax.net.ssl.HttpsURLConnection;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            TransactionState, HttpsURLConnectionExtension

final class c
    implements StreamCompleteListener
{

    private TransactionState a;
    private HttpsURLConnectionExtension b;

    c(HttpsURLConnectionExtension httpsurlconnectionextension, TransactionState transactionstate)
    {
        b = httpsurlconnectionextension;
        a = transactionstate;
        super();
    }

    public final void streamComplete(StreamCompleteEvent streamcompleteevent)
    {
        if (!a.isComplete())
        {
            long l = HttpsURLConnectionExtension.a(b).getContentLength();
            long l1 = streamcompleteevent.getBytes();
            if (l < 0L)
            {
                l = l1;
            }
            a.setBytesReceived(l);
            HttpsURLConnectionExtension.a(b, a);
        }
    }

    public final void streamError(StreamCompleteEvent streamcompleteevent)
    {
        if (!a.isComplete())
        {
            a.setBytesReceived(streamcompleteevent.getBytes());
        }
        HttpsURLConnectionExtension.a(b, streamcompleteevent.getException());
    }
}
