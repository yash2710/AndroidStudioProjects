// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.referrer;

import com.flipkart.android.volley.filters.ThrottledRetryPolicy;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.referrer.params.ReferrerParams;

// Referenced classes of package com.flipkart.android.volley.request.referrer:
//            a

public class ReferrerRequest extends GsonRequest
{

    private ReferrerParams a;

    public ReferrerRequest(ReferrerParams referrerparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(1, constructGetUri("2", "register/referral", ""), null, (new a()).getType(), listener, errorlistener);
        setRetryPolicy(new ThrottledRetryPolicy(0, 10000));
        a = referrerparams;
    }

    public byte[] getBody()
    {
        return a.getBytes();
    }

    public void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
    }
}
