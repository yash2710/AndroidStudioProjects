// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.support.v4.app.FragmentActivity;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.ProductInfoVDataHandler;
import com.flipkart.android.fragments.model.ProductPageMoreSellerModel;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.productInfo.ProductInfoResponse;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.ProductPageMoreSellerBuilder;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageMultipleSellersFragment

final class aE extends ProductInfoVDataHandler
{

    private ProductPageMultipleSellersFragment a;

    aE(ProductPageMultipleSellersFragment productpagemultiplesellersfragment, int i)
    {
        a = productpagemultiplesellersfragment;
        super(0);
    }

    public final void errorReceived(int i, int j, String s)
    {
        a.closeRefresing();
        a.isRefreshing = false;
        CustomDialog.showErrorMessage(i, j, s, a.activity);
    }

    public final void resultReceived(ProductInfoResponse productinforesponse, boolean flag)
    {
        if (!flag)
        {
            a.closeRefresing();
            a.isRefreshing = false;
        }
        a.requestId = productinforesponse.getRequestId();
        a.analyticData.setRequestId(a.requestId);
        if (productinforesponse != null)
        {
            a.analyticData.setRequestId(productinforesponse.getRequestId());
            Map map = productinforesponse.getProductInfo();
            if (map == null || map.size() <= 0 || a.b == null || a.c == null)
            {
                return;
            } else
            {
                a.c.addProductMap(map);
                a.d = ProductPageMoreSellerModel.getModel((ProductInfo)map.get(a.a.getProductId()), a.getActivity().getApplicationContext());
                ProductPageMoreSellerBuilder.buildProductPageMoreSeller(a.d, a.b, a, a.activity);
                return;
            }
        } else
        {
            CustomDialog.showErrorMessage("Oops!! Something went wrong. Please try after sometime.", a.activity);
            return;
        }
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((ProductInfoResponse)obj, flag);
    }
}
