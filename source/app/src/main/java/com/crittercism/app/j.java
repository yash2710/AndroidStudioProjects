// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;


// Referenced classes of package com.crittercism.app:
//            Crittercism

final class j
    implements Runnable
{

    j(Crittercism crittercism)
    {
    }

    public final void run()
    {
        try
        {
            Crittercism.a().i();
            return;
        }
        catch (Exception exception)
        {
            (new StringBuilder("Exception in logHandledExceptionInstanceMethod Thread.run: ")).append(exception.getClass().getName());
        }
    }
}
