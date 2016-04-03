// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.InfiniteListView;

import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.InfiniteListView.params.InfiniteProductListParam;
import java.util.Map;

// Referenced classes of package com.flipkart.android.volley.request.InfiniteListView:
//            a

public class FetchInfiniteListRequest extends GsonRequest
{

    public FetchInfiniteListRequest(InfiniteProductListParam infiniteproductlistparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, (new StringBuilder()).append(BASE_API_URL_SECURE).append("/3").append(infiniteproductlistparam.generateURI()).toString(), null, (new a()).getType(), listener, errorlistener);
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (Map)obj, flag);
    }

    public void performJsonUpdate(byte abyte0[], Map map, boolean flag)
    {
    }
}
