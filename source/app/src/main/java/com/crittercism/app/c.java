// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import java.util.concurrent.Callable;

// Referenced classes of package com.crittercism.app:
//            Crittercism

final class c
    implements Callable
{

    c()
    {
    }

    private static Boolean a()
    {
        boolean flag1;
        Crittercism.a();
        android.content.Context context = Crittercism.a().n();
        Crittercism.a().m();
        flag1 = Crittercism.a(context);
        boolean flag = flag1;
_L2:
        return Boolean.valueOf(flag);
        Exception exception;
        exception;
        (new StringBuilder("Exception in getOptOutStatus.call(): ")).append(exception.getClass().getName());
        flag = false;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final Object call()
    {
        return a();
    }
}
