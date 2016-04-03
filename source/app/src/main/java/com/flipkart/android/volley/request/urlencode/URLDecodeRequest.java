// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.urlencode;

import com.flipkart.android.response.urlencoder.UrlDecodeResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.urlencode.params.URLDecodeParams;

// Referenced classes of package com.flipkart.android.volley.request.urlencode:
//            a

public class URLDecodeRequest extends GsonRequest
{

    public URLDecodeRequest(URLDecodeParams urldecodeparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, constructGetUri("2", "urlencoder/decode", urldecodeparams.generateURI()), null, (new a()).getType(), listener, errorlistener);
    }

    public void performJsonUpdate(byte abyte0[], UrlDecodeResponse urldecoderesponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (UrlDecodeResponse)obj, flag);
    }
}
