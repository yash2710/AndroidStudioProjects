// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.SearchAndProdInfoDataHandler;
import com.flipkart.android.datahandler.param.BaseDataHandlerParam;
import com.flipkart.android.response.discovery.GuidedSearchResponse;
import com.flipkart.android.utils.FkProductListContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageFragment

final class am extends SearchAndProdInfoDataHandler
{

    private ProductPageFragment a;

    am(ProductPageFragment productpagefragment, BaseDataHandlerParam basedatahandlerparam, int i)
    {
        a = productpagefragment;
        super(basedatahandlerparam, 0);
    }

    public final void resultReceivedFilterInfo(int i, String s, ArrayList arraylist, ArrayList arraylist1)
    {
    }

    public final void resultReceivedNoResultFound(int i, String s)
    {
        ProductPageFragment.a(a, i, s);
    }

    public final void resultReceivedOmnitureInfo(String s, String s1, String s2, String s3)
    {
    }

    public final void resultReceivedProductInfo(int i, int j, String s, List list, Map map, String s1, boolean flag, 
            String s2)
    {
        a.analyticData.setRequestId(s2);
        ProductPageFragment.a(a, i, j, s, list, map, s1, flag, false, s2);
    }

    public final void resultReceivedStoreInfo(int i, String s, ArrayList arraylist, ArrayList arraylist1, String s1, int j, ArrayList arraylist2, 
            ArrayList arraylist3, boolean flag, GuidedSearchResponse guidedsearchresponse)
    {
        if (ProductPageFragment.c(a) != null && ProductPageFragment.c(a).getTotalProductCount() == 0)
        {
            ProductPageFragment.c(a).setTotalProductCount(j);
        }
    }

    public final void updateTotalProductCount(int i)
    {
    }
}
