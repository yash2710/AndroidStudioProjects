// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.crashlytics.android:
//            Z

final class o
    implements Callable
{

    private Z a;

    o(Z z)
    {
        a = z;
        super();
    }

    public final Object call()
    {
        if (!Z.a(a).get())
        {
            Z.b(a);
            Z.c(a);
            return Boolean.valueOf(true);
        } else
        {
            return Boolean.valueOf(false);
        }
    }
}
