// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.AdX.tag;

import android.os.AsyncTask;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;

// Referenced classes of package com.AdX.tag:
//            j, AdXConnect

final class c
    implements Runnable
{

    private AdXConnect a;
    private final String b;

    c(AdXConnect adxconnect, String s)
    {
        a = adxconnect;
        b = s;
        super();
    }

    public final void run()
    {
        AdXConnect.a(a, new j(a, AdXConnect.k(a), b));
        j j1 = AdXConnect.l(a);
        Void avoid[] = new Void[0];
        if (!(j1 instanceof AsyncTask))
        {
            j1.execute(avoid);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)j1, avoid);
            return;
        }
    }
}
