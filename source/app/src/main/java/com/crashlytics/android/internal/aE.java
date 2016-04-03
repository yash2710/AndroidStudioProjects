// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import java.io.IOException;
import java.util.concurrent.Callable;

// Referenced classes of package com.crashlytics.android.internal:
//            aD

public abstract class aE
    implements Callable
{

    protected aE()
    {
    }

    protected abstract Object a();

    protected abstract void b();

    public Object call()
    {
        boolean flag = true;
        Object obj = a();
        try
        {
            b();
        }
        catch (IOException ioexception2)
        {
            throw new aD(ioexception2);
        }
        return obj;
        aD ad;
        ad;
        throw ad;
        Exception exception;
        exception;
_L4:
        b();
_L2:
        throw exception;
        IOException ioexception1;
        ioexception1;
        throw new aD(ioexception1);
        IOException ioexception;
        ioexception;
        if (flag) goto _L2; else goto _L1
_L1:
        throw new aD(ioexception);
        exception;
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
    }
}
