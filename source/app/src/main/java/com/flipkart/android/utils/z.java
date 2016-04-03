// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.widget.Toast;
import com.flipkart.logging.FkLogger;

final class z extends Thread
{

    private long a;
    private long b;
    private Toast c;

    z(long l, Toast toast)
    {
        b = l;
        c = toast;
        super();
        a = 0L;
    }

    public final void run()
    {
        try
        {
            long l;
            for (; a <= b; a = a + (System.currentTimeMillis() - l))
            {
                l = System.currentTimeMillis();
                c.show();
                sleep(1750L);
            }

        }
        catch (InterruptedException interruptedexception)
        {
            FkLogger.error("ToastExpander", interruptedexception.toString());
        }
    }
}
