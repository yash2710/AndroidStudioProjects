// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.notifyme;

import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.notifyme.params.NotifymeParam;

// Referenced classes of package com.flipkart.android.volley.request.notifyme:
//            a

public class NotifymeRequest extends GsonRequest
{

    private NotifymeParam a;

    public NotifymeRequest(NotifymeParam notifymeparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, AnalyticData analyticdata)
    {
        super(1, constructGetUri("2", "ugc/notifyMe", notifymeparam.generateURI()), null, (new a()).getType(), listener, errorlistener, analyticdata);
        a = null;
        a = notifymeparam;
    }

    public byte[] getBody()
    {
        return a.generateToByteArray();
    }

    public void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
    }
}
