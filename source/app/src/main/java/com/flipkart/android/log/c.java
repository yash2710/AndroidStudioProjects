// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.log;

import com.flipkart.android.datahandler.LoggerVDataHandler;
import com.flipkart.android.response.logger.LoggerResponse;

// Referenced classes of package com.flipkart.android.log:
//            ApiPerfLogger

final class c extends LoggerVDataHandler
{

    private int a;

    c(int i)
    {
        a = i;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        ApiPerfLogger.a(a);
        ApiPerfLogger.isSendingLogs = false;
    }

    public final void resultReceived(LoggerResponse loggerresponse, boolean flag)
    {
        ApiPerfLogger.a(a);
        ApiPerfLogger.isSendingLogs = false;
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((LoggerResponse)obj, flag);
    }
}
