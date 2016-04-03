// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.referrer.ReferrerRequest;
import com.flipkart.android.volley.request.referrer.params.ReferrerParams;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class ReferrerVDataHandler extends BaseVDataHandler
{

    public ReferrerVDataHandler()
    {
    }

    public void sendReferrerString(String s, long l, String s1)
    {
        ReferrerRequest referrerrequest = new ReferrerRequest(new ReferrerParams(s, s1, l), listner, errorListner);
        request = referrerrequest;
        FlipkartApplication.getRequestQueue().add(referrerrequest);
    }
}
