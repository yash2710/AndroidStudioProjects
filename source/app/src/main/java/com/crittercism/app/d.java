// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import java.util.concurrent.Callable;

// Referenced classes of package com.crittercism.app:
//            Crittercism

final class d
    implements Callable
{

    d()
    {
    }

    private static String a()
    {
        String s;
        try
        {
            s = Crittercism.a().w();
        }
        catch (Exception exception)
        {
            (new StringBuilder("Exception in getUserUUID.call(): ")).append(exception.getClass().getName());
            return null;
        }
        return s;
    }

    public final Object call()
    {
        return a();
    }
}
