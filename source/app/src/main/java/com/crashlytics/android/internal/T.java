// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package com.crashlytics.android.internal:
//            U

final class T
    implements ThreadFactory
{

    private String a;
    private AtomicLong b;

    T(String s, AtomicLong atomiclong)
    {
        a = s;
        b = atomiclong;
        super();
    }

    public final Thread newThread(Runnable runnable)
    {
        Thread thread = Executors.defaultThreadFactory().newThread(new U(runnable));
        Locale locale = Locale.US;
        String s = a;
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(b.getAndIncrement());
        thread.setName(String.format(locale, s, aobj));
        return thread;
    }
}
