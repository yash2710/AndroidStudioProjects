// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.discovery;

import com.flipkart.android.DB.ProductDiscovery;
import com.flipkart.android.DB.ProductDiscoveryDao;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.HashUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.volley.request.discovery.params.SearchParam;

// Referenced classes of package com.flipkart.android.volley.request.discovery:
//            SearchRequest

final class e extends Thread
{

    private byte a[];
    private SearchRequest b;

    e(SearchRequest searchrequest, byte abyte0[])
    {
        b = searchrequest;
        a = abyte0;
        super();
    }

    public final void run()
    {
        try
        {
            (new ProductDiscoveryDao(FlipkartApplication.getAppContext())).create(new ProductDiscovery(HashUtils.md5(SearchRequest.a(b).generateURI()), ScreenMathUtils.getCurrentLinuxTimeInSeconds(), a, SearchRequest.a(b).getQuery(), SearchRequest.a(b).getStoreId(), SearchRequest.a(b).getStoreName()));
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }
}
