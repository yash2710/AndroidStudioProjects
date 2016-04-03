// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.wishlist;

import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.response.wishlist.WishListResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.wishlist.params.DeleteFromWishListParams;

// Referenced classes of package com.flipkart.android.volley.request.wishlist:
//            b

public class DeleteFromWishListRequest extends GsonRequest
{

    private DeleteFromWishListParams a;

    public DeleteFromWishListRequest(DeleteFromWishListParams deletefromwishlistparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, AnalyticData analyticdata)
    {
        super(1, constructSecureGetUri("2", "ugc/wishlist/delete", deletefromwishlistparams.generateURI()), null, (new b()).getType(), listener, errorlistener, analyticdata);
        a = null;
        a = deletefromwishlistparams;
    }

    public byte[] getBody()
    {
        return a.generateToByteArray();
    }

    public void performJsonUpdate(byte abyte0[], WishListResponse wishlistresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (WishListResponse)obj, flag);
    }
}
