// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;


// Referenced classes of package com.google.gson.internal:
//            y

final class s
{

    private y a;

    s()
    {
    }

    final void a(y y1)
    {
        y y2 = null;
        y y4;
        for (y y3 = y1; y3 != null; y3 = y4)
        {
            y3.a = y2;
            y4 = y3.b;
            y2 = y3;
        }

        a = y2;
    }

    public final y next()
    {
        y y1 = a;
        if (y1 == null)
        {
            return null;
        }
        y y2 = y1.a;
        y1.a = null;
        y y4;
        for (y y3 = y1.c; y3 != null; y3 = y4)
        {
            y3.a = y2;
            y4 = y3.b;
            y2 = y3;
        }

        a = y2;
        return y1;
    }
}
