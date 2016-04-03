// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import android.widget.Toast;
import com.flipkart.android.DB.WishList;
import com.flipkart.android.DB.WishListDao;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.param.ProductsListParam;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.wishlist.WishListJsonResponse;
import com.flipkart.android.response.wishlist.WishListResponse;
import com.flipkart.android.utils.ScreenMathUtils;
import java.util.ArrayList;
import java.util.Collections;

// Referenced classes of package com.flipkart.android.datahandler:
//            WishListVDataHandler, SearchAndProdInfoDataHandler

final class j extends WishListVDataHandler
{

    private int a;
    private String b;
    private AnalyticData c;
    private SearchAndProdInfoDataHandler d;

    j(SearchAndProdInfoDataHandler searchandprodinfodatahandler, int i, String s, AnalyticData analyticdata)
    {
        d = searchandprodinfodatahandler;
        a = i;
        b = s;
        c = analyticdata;
        super();
    }

    public final void errorReceived(int i, int k, String s)
    {
        WishListDao wishlistdao = new WishListDao(FlipkartApplication.getAppContext());
        SearchAndProdInfoDataHandler.c(d).setProductIds(wishlistdao.getAllWishListPids());
        if (SearchAndProdInfoDataHandler.c(d) != null && SearchAndProdInfoDataHandler.c(d).getProductIds() != null)
        {
            d.updateTotalProductCount(SearchAndProdInfoDataHandler.c(d).getProductIds().size());
        }
        SearchAndProdInfoDataHandler.a(d, a, b, c);
        Toast.makeText(FlipkartApplication.getAppContext(), "Error while Syncing Wishlist.Please try after sometime", 1).show();
    }

    public final void resultReceived(WishListResponse wishlistresponse, boolean flag)
    {
        if (wishlistresponse != null)
        {
            WishListJsonResponse wishlistjsonresponse = wishlistresponse.getWishlist();
            if (wishlistjsonresponse != null && wishlistjsonresponse.isSuccess())
            {
                WishListDao wishlistdao = new WishListDao(FlipkartApplication.getAppContext());
                wishlistdao.deleteAll();
                ArrayList arraylist = new ArrayList();
                ArrayList arraylist1 = wishlistjsonresponse.getProductIds();
                SearchAndProdInfoDataHandler.c(d).setProductIds(arraylist1);
                Collections.reverse(arraylist1);
                for (int i = 0; i < arraylist1.size(); i++)
                {
                    arraylist.add(new WishList((String)arraylist1.get(i), ScreenMathUtils.getCurrentLinuxTimeInSeconds() + (long)i));
                }

                wishlistdao.createInBulk(arraylist);
                if (SearchAndProdInfoDataHandler.c(d) != null && SearchAndProdInfoDataHandler.c(d).getProductIds() != null)
                {
                    d.updateTotalProductCount(SearchAndProdInfoDataHandler.c(d).getProductIds().size());
                }
                SearchAndProdInfoDataHandler.a(d, a, b, c);
            }
        }
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((WishListResponse)obj, flag);
    }
}
