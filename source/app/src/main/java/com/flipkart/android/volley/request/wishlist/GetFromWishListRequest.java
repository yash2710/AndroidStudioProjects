// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.wishlist;

import com.flipkart.android.DB.FlipkartProductInfo;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.wishlist.WishListResponse;
import com.flipkart.android.utils.GZipCompressorUtil;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.wishlist.params.GetFromWishListParams;
import com.google.mygson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.volley.request.wishlist:
//            c, d

public class GetFromWishListRequest extends GsonRequest
{

    public GetFromWishListRequest(GetFromWishListParams getfromwishlistparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, AnalyticData analyticdata)
    {
        super(0, constructSecureGetUri("2", "ugc/wishlist", getfromwishlistparams.generateURI()), null, (new c()).getType(), listener, errorlistener, analyticdata);
    }

    public void performJsonUpdate(byte abyte0[], WishListResponse wishlistresponse, boolean flag)
    {
        if (wishlistresponse != null)
        {
            Map map = wishlistresponse.getProduct();
            if (map != null)
            {
                ArrayList arraylist = new ArrayList();
                Iterator iterator = map.keySet().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    String s = (String)iterator.next();
                    if (!StringUtils.isNullOrEmpty(s))
                    {
                        ProductInfo productinfo = (ProductInfo)map.get(s);
                        if (productinfo != null)
                        {
                            productinfo.setLastUpdatedTimeStamp(ScreenMathUtils.getCurrentLinuxTimeInSeconds());
                            arraylist.add(new FlipkartProductInfo(s, ScreenMathUtils.getCurrentLinuxTimeInSeconds(), GZipCompressorUtil.compress(FlipkartApplication.getGsonInstance().toJson(map.get(s)).getBytes())));
                        }
                    }
                } while (true);
                if (arraylist.size() > 0)
                {
                    (new d(this, arraylist)).start();
                }
            }
        }
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (WishListResponse)obj, flag);
    }
}
