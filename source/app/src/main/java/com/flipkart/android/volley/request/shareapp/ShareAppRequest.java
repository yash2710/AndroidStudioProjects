// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.shareapp;

import com.flipkart.android.response.shareapp.ShareAppResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.shareapp.params.ShareAppParams;

// Referenced classes of package com.flipkart.android.volley.request.shareapp:
//            a

public class ShareAppRequest extends GsonRequest
{

    private ShareAppParams a;

    public ShareAppRequest(ShareAppParams shareappparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(1, constructSecureGetUri("2", "offer/add/device", shareappparams.generateURI()), null, (new a()).getType(), listener, errorlistener);
        a = shareappparams;
    }

    public byte[] getBody()
    {
        return a.generateToByteArray();
    }

    public void performJsonUpdate(byte abyte0[], ShareAppResponse shareappresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (ShareAppResponse)obj, flag);
    }
}
