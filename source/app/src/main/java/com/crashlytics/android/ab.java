// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.crashlytics.android:
//            Z

final class ab
    implements Callable
{

    private long a;
    private String b;
    private Z c;

    ab(Z z, long l, String s)
    {
        c = z;
        a = l;
        b = s;
        super();
    }

    public final Object call()
    {
        if (!Z.a(c).get())
        {
            if (Z.d(c) == null)
            {
                Z.e(c);
            }
            Z _tmp = c;
            Z.a(Z.d(c), a, b);
        }
        return null;
    }
}
