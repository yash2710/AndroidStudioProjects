// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import crittercism.android.i;
import java.util.Vector;
import java.util.concurrent.Callable;

// Referenced classes of package com.crittercism.app:
//            Crittercism

final class h
    implements Callable
{

    h(Crittercism crittercism)
    {
    }

    private static Boolean a()
    {
        Boolean boolean1;
        try
        {
            Vector vector = Crittercism.a().q().e();
            i j = i.d();
            j.a(vector);
            Crittercism.a().a(j);
            (new StringBuilder("allAppLoads.getPendingDataVector().size() = ")).append(j.e().size());
        }
        catch (Exception exception)
        {
            (new StringBuilder("Exception in startAppLoadsThreads boolean callable: ")).append(exception.getClass().getName());
        }
        if (Crittercism.a().q().e().size() != 0)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        boolean1 = Boolean.valueOf(true);
        return boolean1;
        Exception exception1;
        exception1;
        return Boolean.valueOf(Crittercism.a().f());
    }

    public final Object call()
    {
        return a();
    }
}
