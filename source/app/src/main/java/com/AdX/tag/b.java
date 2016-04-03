// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.AdX.tag;

import android.content.Context;
import android.util.Log;

// Referenced classes of package com.AdX.tag:
//            AdXConnect, AdXURLConnection

final class b
    implements Runnable
{

    private final Context a;
    private final int b;
    private final String c;

    b(Context context, int i, String s)
    {
        a = context;
        b = i;
        c = s;
        super();
    }

    public final void run()
    {
        if (AdXConnect.DEBUG)
        {
            Log.i("AdXAppTracker", (new StringBuilder(String.valueOf(AdXConnect.sendDelay))).append(" seconds is up sending to AdX").toString());
        }
        if (AdXConnect.b() == null)
        {
            AdXConnect.a(new AdXURLConnection());
        }
        if (AdXConnect.c() == null)
        {
            AdXConnect.j(new AdXConnect(a, b, c, (byte)0));
        }
    }
}
