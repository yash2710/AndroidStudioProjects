// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.InfiniteListView.FetchInfiniteListRequest;
import com.flipkart.android.volley.request.InfiniteListView.params.InfiniteProductListParam;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class InfiniteProductListDataHandler extends BaseVDataHandler
{

    public InfiniteProductListDataHandler()
    {
    }

    public void getNextSet(int i, int j, String s)
    {
        FetchInfiniteListRequest fetchinfinitelistrequest = new FetchInfiniteListRequest(new InfiniteProductListParam(i, j, s), listner, errorListner);
        request = fetchinfinitelistrequest;
        FlipkartApplication.getRequestQueue().add(fetchinfinitelistrequest);
    }
}
