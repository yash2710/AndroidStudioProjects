// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.AdX.tag;

import android.os.AsyncTask;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;

// Referenced classes of package com.AdX.tag:
//            i, AdXConnect

final class d
    implements Runnable
{

    private AdXConnect a;

    d(AdXConnect adxconnect)
    {
        a = adxconnect;
        super();
    }

    public final void run()
    {
        AdXConnect.a(a, new i(a, AdXConnect.k(a)));
        i j = AdXConnect.m(a);
        Void avoid[] = new Void[0];
        if (!(j instanceof AsyncTask))
        {
            j.execute(avoid);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)j, avoid);
            return;
        }
    }
}
