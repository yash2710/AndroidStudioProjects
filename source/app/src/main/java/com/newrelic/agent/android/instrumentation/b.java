// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.instrumentation.io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListener;
import java.net.HttpURLConnection;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            TransactionState, HttpURLConnectionExtension

final class b
    implements StreamCompleteListener
{

    private TransactionState a;
    private HttpURLConnectionExtension b;

    b(HttpURLConnectionExtension httpurlconnectionextension, TransactionState transactionstate)
    {
        b = httpurlconnectionextension;
        a = transactionstate;
        super();
    }

    public final void streamComplete(StreamCompleteEvent streamcompleteevent)
    {
        if (a.isComplete()) goto _L2; else goto _L1
_L1:
        String s;
        long l;
        s = HttpURLConnectionExtension.a(b).getRequestProperty("content-length");
        l = streamcompleteevent.getBytes();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        long l1 = Long.parseLong(s);
        l = l1;
_L4:
        a.setBytesSent(l);
        HttpURLConnectionExtension.a(b, a);
_L2:
        return;
        NumberFormatException numberformatexception;
        numberformatexception;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void streamError(StreamCompleteEvent streamcompleteevent)
    {
        if (!a.isComplete())
        {
            a.setBytesSent(streamcompleteevent.getBytes());
        }
        HttpURLConnectionExtension.a(b, streamcompleteevent.getException());
    }
}
