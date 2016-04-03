// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import crittercism.android.b;
import crittercism.android.d;

// Referenced classes of package com.crittercism.app:
//            Crittercism, n

final class a
    implements Runnable
{

    private Crittercism a;

    a(Crittercism crittercism)
    {
        a = crittercism;
        super();
    }

    public final void run()
    {
        String s;
        boolean flag;
        Crittercism crittercism;
        android.content.Context context1;
        Thread.UncaughtExceptionHandler uncaughtexceptionhandler;
        try
        {
            Crittercism.a();
            android.content.Context context = Crittercism.a().n();
            Crittercism.a().m();
            flag = Crittercism.a(context);
            Crittercism.a(Crittercism.a(), flag);
        }
        catch (Throwable throwable)
        {
            (new StringBuilder("Exception performing async disk io and finishing initialization: ")).append(throwable.getClass().getName());
            throwable.printStackTrace();
            return;
        }
        if (flag)
        {
            return;
        }
        crittercism = Crittercism.a();
        Crittercism.a();
        context1 = Crittercism.a().n();
        Crittercism.a().m();
        com.crittercism.app.Crittercism.b(crittercism, com.crittercism.app.Crittercism.b(context1));
        Crittercism.a(Crittercism.a());
        uncaughtexceptionhandler = Thread.getDefaultUncaughtExceptionHandler();
        if (!(uncaughtexceptionhandler instanceof n))
        {
            Thread.setDefaultUncaughtExceptionHandler(new n(a, uncaughtexceptionhandler));
        }
        Crittercism.a().p();
        s = d.a(Crittercism.a().n(), "com.crittercism.prefs.did");
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_227;
        }
        s = Crittercism.a().o().b();
        Crittercism.a().p();
        d.a(Crittercism.a().n(), "com.crittercism.prefs.did", s);
_L1:
        Crittercism.a().o().a(s);
        Crittercism.a().b();
        Crittercism.c(Crittercism.a(), com.crittercism.app.Crittercism.b(Crittercism.a()));
        Crittercism.a().d();
        return;
        (new StringBuilder("deviceID is ")).append(s);
          goto _L1
    }
}
