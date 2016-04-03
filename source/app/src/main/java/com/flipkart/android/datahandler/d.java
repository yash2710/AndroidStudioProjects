// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.flipkart.android.register.RegistrationHelper;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

final class d
    implements Runnable
{

    private BaseVDataHandler a;

    d(BaseVDataHandler basevdatahandler)
    {
        a = basevdatahandler;
        super();
    }

    public final void run()
    {
        if (RegistrationHelper.doRegister(Long.toString(System.currentTimeMillis() / 1000L)))
        {
            Object obj = BaseVDataHandler.access$300(a);
            a.resultReceived(obj, false);
        }
    }
}
