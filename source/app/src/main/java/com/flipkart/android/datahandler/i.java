// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.flipkart.android.response.productInfo.ProductInfoResponse;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.datahandler:
//            ProductInfoVDataHandler, SearchAndProdInfoDataHandler

final class i extends ProductInfoVDataHandler
{

    private SearchAndProdInfoDataHandler a;

    i(SearchAndProdInfoDataHandler searchandprodinfodatahandler, int j)
    {
        a = searchandprodinfodatahandler;
        super(j);
    }

    public final void errorReceived(int j, int k, String s)
    {
        a.resultReceivedProductInfo(j, k, s, null, null, null, false, null);
    }

    public final void resultReceived(ProductInfoResponse productinforesponse, boolean flag)
    {
        if (!StringUtils.isNull(productinforesponse))
        {
            a.resultReceivedProductInfo(200, -1, "", SearchAndProdInfoDataHandler.b(a), productinforesponse.getProductInfo(), "list", flag, productinforesponse.getRequestId());
            return;
        } else
        {
            a.resultReceivedProductInfo(200, -1, "", SearchAndProdInfoDataHandler.b(a), null, "list", flag, null);
            return;
        }
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((ProductInfoResponse)obj, flag);
    }
}
