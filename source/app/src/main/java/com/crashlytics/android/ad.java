// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import java.util.concurrent.Callable;

// Referenced classes of package com.crashlytics.android:
//            Z

final class ad
    implements Callable
{

    private Z a;

    ad(Z z)
    {
        a = z;
        super();
    }

    public final Object call()
    {
        if (!a.g())
        {
            Z.c(a);
        }
        return null;
    }
}
