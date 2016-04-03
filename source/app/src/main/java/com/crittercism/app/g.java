// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import android.util.Log;

// Referenced classes of package com.crittercism.app:
//            l, Crittercism

final class g
    implements Runnable
{

    g()
    {
    }

    public final void run()
    {
        Thread thread = new Thread(new l((byte)0));
_L2:
        if (Crittercism.c() || Crittercism.d(Crittercism.a()) == null || !Crittercism.d(Crittercism.a()).isAlive())
        {
            break MISSING_BLOCK_LABEL_63;
        }
        Crittercism.d(Crittercism.a()).join();
        continue; /* Loop/switch isn't completed */
        InterruptedException interruptedexception;
        interruptedexception;
        if (!Crittercism.c())
        {
            continue; /* Loop/switch isn't completed */
        }
        thread.start();
        return;
        Exception exception;
        exception;
        Log.w("Crittercism", "Exception in Thread in sendAppLoadData");
        exception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
    }
}
