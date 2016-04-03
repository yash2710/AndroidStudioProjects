// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import crittercism.android.m;
import java.util.Vector;
import java.util.concurrent.Callable;

// Referenced classes of package com.crittercism.app:
//            Crittercism

final class i
    implements Callable
{

    i(Crittercism crittercism)
    {
    }

    private static Boolean a()
    {
        Boolean boolean1;
        try
        {
            Vector vector = Crittercism.a().r().e();
            m m1 = m.c();
            m1.a(vector);
            Crittercism.a().a(m1);
        }
        catch (Exception exception)
        {
            (new StringBuilder("Exception in startCrashingSendingThreads boolean callable: ")).append(exception.getClass().getName());
        }
        if (Crittercism.a().r().e().size() != 0)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        boolean1 = Boolean.valueOf(true);
        return boolean1;
        Exception exception1;
        exception1;
        return Boolean.valueOf(Crittercism.a().h());
    }

    public final Object call()
    {
        return a();
    }
}
