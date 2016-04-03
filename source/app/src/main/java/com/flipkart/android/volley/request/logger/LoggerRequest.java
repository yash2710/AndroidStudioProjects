// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.logger;

import com.flipkart.android.response.logger.LoggerResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.logger.params.LoggerParams;

// Referenced classes of package com.flipkart.android.volley.request.logger:
//            a

public class LoggerRequest extends GsonRequest
{

    private LoggerParams a;

    public LoggerRequest(LoggerParams loggerparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(1, constructGetUri("2", "util/dump", loggerparams.generateURI()), null, (new a()).getType(), listener, errorlistener);
        a = null;
        a = loggerparams;
    }

    public byte[] getBody()
    {
        return a.generateToByteArray();
    }

    public void performJsonUpdate(byte abyte0[], LoggerResponse loggerresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (LoggerResponse)obj, flag);
    }
}
