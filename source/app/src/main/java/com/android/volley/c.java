// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


// Referenced classes of package com.android.volley:
//            Request, Response, ExecutorDelivery

final class c
    implements Runnable
{

    private final Request a;
    private final Response b;
    private final Runnable c;

    public c(ExecutorDelivery executordelivery, Request request, Response response, Runnable runnable)
    {
        a = request;
        b = response;
        c = runnable;
    }

    public final void run()
    {
        if (a.isCanceled())
        {
            a.a("canceled-at-delivery");
        } else
        {
            if (b.isSuccess())
            {
                a.deliverResponse(b.result);
            } else
            {
                a.deliverError(b.error);
            }
            if (b.intermediate)
            {
                a.addMarker("intermediate-response");
            } else
            {
                a.a("done");
            }
            if (c != null)
            {
                c.run();
                return;
            }
        }
    }
}
