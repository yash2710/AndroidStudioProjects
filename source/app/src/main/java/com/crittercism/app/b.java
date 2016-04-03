// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import crittercism.android.l;
import java.util.Vector;
import java.util.concurrent.Callable;

// Referenced classes of package com.crittercism.app:
//            Crittercism

final class b
    implements Callable
{

    b(Crittercism crittercism)
    {
    }

    private static Boolean a()
    {
        Boolean boolean1;
        try
        {
            Crittercism.a().a(l.c());
            (new StringBuilder("pendingNdkCrashes.getPendingDataVector().size = ")).append(Crittercism.a().t().e().size());
        }
        catch (Exception exception)
        {
            (new StringBuilder("Exception in startNdkSendingThreads boolean callable: ")).append(exception.getClass().getName());
            exception.printStackTrace();
        }
        if (Crittercism.a().t().e().size() != 0)
        {
            break MISSING_BLOCK_LABEL_85;
        }
        boolean1 = Boolean.valueOf(true);
        return boolean1;
        Exception exception1;
        exception1;
        return Boolean.valueOf(Crittercism.a().l());
    }

    public final Object call()
    {
        return a();
    }
}
