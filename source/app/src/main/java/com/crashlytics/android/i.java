// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import com.crashlytics.android.internal.q;
import com.crashlytics.android.internal.v;
import java.util.concurrent.Callable;

final class i
    implements Callable
{

    private Callable a;

    i(Callable callable)
    {
        a = callable;
        super();
    }

    public final Object call()
    {
        Object obj;
        try
        {
            obj = a.call();
        }
        catch (Exception exception)
        {
            v.a().b().a("Crashlytics", "Failed to execute task.", exception);
            return null;
        }
        return obj;
    }
}
