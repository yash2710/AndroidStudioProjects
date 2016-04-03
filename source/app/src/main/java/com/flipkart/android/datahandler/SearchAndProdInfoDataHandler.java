// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.param.BaseDataHandlerParam;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.datahandler.param.ProductsListParam;
import com.flipkart.android.response.discovery.GuidedSearchResponse;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.PageTypeUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler, h, i, SearchVDataHander, 
//            ProductInfoVDataHandler, j, WishListVDataHandler

public abstract class SearchAndProdInfoDataHandler extends BaseVDataHandler
{

    private BrowseParam a;
    private ProductsListParam b;
    private final int c;
    private SearchVDataHander d;
    private ProductInfoVDataHandler e;
    private List f;

    public SearchAndProdInfoDataHandler(BaseDataHandlerParam basedatahandlerparam, int k)
    {
        a = null;
        b = null;
        d = null;
        e = null;
        f = null;
        c = k;
        if (basedatahandlerparam == null) goto _L2; else goto _L1
_L1:
        if (!(basedatahandlerparam instanceof BrowseParam)) goto _L4; else goto _L3
_L3:
        a = (BrowseParam)basedatahandlerparam;
_L2:
        if (a != null)
        {
            d = new h(this);
        }
        e = new i(this, k);
        return;
_L4:
        if (basedatahandlerparam instanceof ProductsListParam)
        {
            b = (ProductsListParam)basedatahandlerparam;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    static int a(SearchAndProdInfoDataHandler searchandprodinfodatahandler)
    {
        return searchandprodinfodatahandler.c;
    }

    private void a(int k, String s, AnalyticData analyticdata)
    {
        int l = AppConfigUtils.getInstance().getFetchNewProductsCount();
        if (b != null && b.getProductIds() != null)
        {
            if (k < b.getProductIds().size())
            {
                int i1;
                String as[];
                if (b.getProductIds().size() > k + l)
                {
                    i1 = l;
                } else
                {
                    i1 = b.getProductIds().size() - k;
                }
                as = new String[i1];
                for (int j1 = k; j1 < k + l && j1 < b.getProductIds().size(); j1++)
                {
                    as[j1 - k] = (String)b.getProductIds().get(j1);
                }

                f = Arrays.asList(as);
                getProductInfo(as, new HashMap(), new HashMap(), s, analyticdata);
                return;
            } else
            {
                resultReceivedProductInfo(200, -1, "no more data", new ArrayList(), new HashMap(), null, false, null);
                return;
            }
        } else
        {
            resultReceivedProductInfo(200, -1, "no more data", new ArrayList(), new HashMap(), null, false, null);
            return;
        }
    }

    static void a(SearchAndProdInfoDataHandler searchandprodinfodatahandler, int k, String s, AnalyticData analyticdata)
    {
        searchandprodinfodatahandler.a(k, s, analyticdata);
    }

    static List b(SearchAndProdInfoDataHandler searchandprodinfodatahandler)
    {
        return searchandprodinfodatahandler.f;
    }

    static ProductsListParam c(SearchAndProdInfoDataHandler searchandprodinfodatahandler)
    {
        return searchandprodinfodatahandler.b;
    }

    public void cancelFetch()
    {
        if (d != null)
        {
            d.cancelRequests();
        }
        if (e != null)
        {
            e.cancelRequests();
        }
    }

    public boolean getNextSetFrom(int k, String s, AnalyticData analyticdata)
    {
        if (a != null)
        {
            d.doSearch(a.getPincode(), a.getQuery(), a.getStoreId(), a.getStoreName(), a.getFilters(), a.getSortOption(), a.getTags(), k, AppConfigUtils.getInstance().getFetchNewProductsCount(), 0, a.isEnableAugmentSearch(), analyticdata, a.getViews());
            analyticdata.setPageTypeUtilsFromString(PageTypeUtils.values()[FlipkartPreferenceManager.instance().getLastPageType()].toString());
            return true;
        }
        if (b != null)
        {
            if (b.getPageType() == PageTypeUtils.WishList && FlipkartPreferenceManager.instance().isLoggedIn().booleanValue() && (b.getProductIds() == null || b.getProductIds().size() == 0))
            {
                j j1 = new j(this, k, s, analyticdata);
                analyticdata.setRequestId(b.getRequestId());
                j1.getWishList(0, -1, -1, analyticdata);
            } else
            {
                if (b != null && b.getProductIds() != null)
                {
                    updateTotalProductCount(b.getProductIds().size());
                }
                a(k, s, analyticdata);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public boolean getProductInfo(String as[], HashMap hashmap, HashMap hashmap1, String s, AnalyticData analyticdata)
    {
        e.fetchProductInfoForProducts(Arrays.asList(as), hashmap, hashmap1, s, c, analyticdata);
        return true;
    }

    public void resultReceived(Object obj, boolean flag)
    {
    }

    public abstract void resultReceivedFilterInfo(int k, String s, ArrayList arraylist, ArrayList arraylist1);

    public abstract void resultReceivedNoResultFound(int k, String s);

    public abstract void resultReceivedOmnitureInfo(String s, String s1, String s2, String s3);

    public abstract void resultReceivedProductInfo(int k, int l, String s, List list, Map map, String s1, boolean flag, 
            String s2);

    public abstract void resultReceivedStoreInfo(int k, String s, ArrayList arraylist, ArrayList arraylist1, String s1, int l, ArrayList arraylist2, 
            ArrayList arraylist3, boolean flag, GuidedSearchResponse guidedsearchresponse);

    public abstract void updateTotalProductCount(int k);
}
