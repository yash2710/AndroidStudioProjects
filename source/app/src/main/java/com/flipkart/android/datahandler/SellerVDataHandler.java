// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.DB.Seller;
import com.flipkart.android.DB.SellerDao;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.seller.SellerResponse;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.GsonUtils;
import com.flipkart.android.utils.HashUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.volley.request.seller.SellerRequest;
import com.flipkart.android.volley.request.seller.params.SellerParam;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler, m

public abstract class SellerVDataHandler extends BaseVDataHandler
{

    private String a;

    public SellerVDataHandler(String s)
    {
        a = s;
    }

    public void fetchSellerInfo(int i, int j)
    {
        SellerParam sellerparam = new SellerParam(a, j, i);
        String s = sellerparam.generateURI();
        Seller seller = (new SellerDao(FlipkartApplication.getAppContext())).getSellerById(HashUtils.md5(s));
        SellerResponse sellerresponse = null;
        if (seller != null)
        {
            long l = AppConfigUtils.getInstance().getSellerDbTimeout();
            int k = ScreenMathUtils.getCurrentLinuxTimeInSeconds() - seller.getTime() != l;
            sellerresponse = null;
            if (k <= 0)
            {
                sellerresponse = (SellerResponse)GsonUtils.getResponse((new m(this)).getType(), seller.getResponse(), true);
            }
        }
        if (sellerresponse != null)
        {
            resultReceivedSellerInfo(200, "", sellerresponse);
            return;
        } else
        {
            SellerRequest sellerrequest = new SellerRequest(sellerparam, listner, errorListner);
            request = sellerrequest;
            FlipkartApplication.getRequestQueue().add(sellerrequest);
            return;
        }
    }

    public void resultReceived(SellerResponse sellerresponse, boolean flag)
    {
        if (sellerresponse != null)
        {
            resultReceivedSellerInfo(200, "", sellerresponse);
            return;
        } else
        {
            resultReceivedSellerInfo(4321, "Opps someting went wrong", null);
            return;
        }
    }

    public volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((SellerResponse)obj, flag);
    }

    public abstract void resultReceivedSellerInfo(int i, String s, SellerResponse sellerresponse);
}
