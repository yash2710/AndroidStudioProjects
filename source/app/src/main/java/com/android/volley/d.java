// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


// Referenced classes of package com.android.volley:
//            Request

final class d
    implements Runnable
{

    private String a;
    private long b;
    private Request c;

    d(Request request, String s, long l)
    {
        c = request;
        a = s;
        b = l;
        super();
    }

    public final void run()
    {
        c.mEventLog.add(a, b);
        c.mEventLog.finish(toString());
    }
}
