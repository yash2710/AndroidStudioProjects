// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.log;

import com.flipkart.android.datahandler.LoggerVDataHandler;
import com.flipkart.android.response.logger.LoggerResponse;

final class a extends LoggerVDataHandler
{

    a()
    {
    }

    public final void errorReceived(int i, int j, String s)
    {
    }

    public final void resultReceived(LoggerResponse loggerresponse, boolean flag)
    {
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((LoggerResponse)obj, flag);
    }
}
