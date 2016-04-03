// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.appconfig;

import com.flipkart.android.response.appconfig.ConfigResponse;
import com.flipkart.android.volley.request.GsonRequest;

// Referenced classes of package com.flipkart.android.volley.request.appconfig:
//            a

public class ConfigRequest extends GsonRequest
{

    public ConfigRequest(com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, constructGetUri("2", "util/config/android", ""), null, (new a()).getType(), listener, errorlistener);
    }

    public void performJsonUpdate(byte abyte0[], ConfigResponse configresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (ConfigResponse)obj, flag);
    }
}
