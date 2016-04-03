// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.shareapp.ShareAppRequest;
import com.flipkart.android.volley.request.shareapp.params.ShareAppParams;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class ShareTheAppDataHandler extends BaseVDataHandler
{

    public ShareTheAppDataHandler()
    {
    }

    public void shareTheApp(String s)
    {
        ShareAppRequest shareapprequest = new ShareAppRequest(new ShareAppParams(s), listner, errorListner);
        request = shareapprequest;
        FlipkartApplication.getRequestQueue().add(shareapprequest);
    }
}
