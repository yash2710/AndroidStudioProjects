// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.wishlist.AddToWishListRequest;
import com.flipkart.android.volley.request.wishlist.DeleteFromWishListRequest;
import com.flipkart.android.volley.request.wishlist.GetFromWishListRequest;
import com.flipkart.android.volley.request.wishlist.params.AddToWishListParams;
import com.flipkart.android.volley.request.wishlist.params.DeleteFromWishListParams;
import com.flipkart.android.volley.request.wishlist.params.GetFromWishListParams;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class WishListVDataHandler extends BaseVDataHandler
{

    public WishListVDataHandler()
    {
    }

    public void addToWishList(String as[], AnalyticData analyticdata)
    {
        AddToWishListRequest addtowishlistrequest = new AddToWishListRequest(new AddToWishListParams(as), listner, errorListner, analyticdata);
        request = addtowishlistrequest;
        FlipkartApplication.getRequestQueue().add(addtowishlistrequest);
    }

    public void deleteFromWishList(String as[], boolean flag, AnalyticData analyticdata)
    {
        DeleteFromWishListRequest deletefromwishlistrequest = new DeleteFromWishListRequest(new DeleteFromWishListParams(as, flag), listner, errorListner, analyticdata);
        request = deletefromwishlistrequest;
        FlipkartApplication.getRequestQueue().add(deletefromwishlistrequest);
    }

    public void getWishList(int i, int j, int k, AnalyticData analyticdata)
    {
        GetFromWishListRequest getfromwishlistrequest = new GetFromWishListRequest(new GetFromWishListParams(i, j, k), listner, errorListner, analyticdata);
        request = getfromwishlistrequest;
        FlipkartApplication.getRequestQueue().add(getfromwishlistrequest);
    }
}
