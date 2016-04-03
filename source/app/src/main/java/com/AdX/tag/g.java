// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.AdX.tag;

import android.os.AsyncTask;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;

// Referenced classes of package com.AdX.tag:
//            h, AdXConnect

final class g
    implements Runnable
{

    private AdXConnect a;

    g(AdXConnect adxconnect)
    {
        a = adxconnect;
        super();
    }

    public final void run()
    {
        AdXConnect.a(a, new h(a, AdXConnect.k(a)));
        h h1 = AdXConnect.n(a);
        Void avoid[] = new Void[0];
        if (!(h1 instanceof AsyncTask))
        {
            h1.execute(avoid);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)h1, avoid);
            return;
        }
    }
}
