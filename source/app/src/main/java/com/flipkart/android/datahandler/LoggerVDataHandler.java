// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.logger.LoggerRequest;
import com.flipkart.android.volley.request.logger.params.LoggerParams;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class LoggerVDataHandler extends BaseVDataHandler
{

    public LoggerVDataHandler()
    {
    }

    public void sendLog(String s, String s1)
    {
        LoggerRequest loggerrequest = new LoggerRequest(new LoggerParams(s, s1), listner, errorListner);
        request = loggerrequest;
        FlipkartApplication.getRequestQueue().add(loggerrequest);
    }
}
