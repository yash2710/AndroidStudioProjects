// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import com.flipkart.android.DB.WishList;
import com.flipkart.android.DB.WishListDao;
import com.flipkart.android.datahandler.WishListVDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.wishlist.WishListJsonResponse;
import com.flipkart.android.response.wishlist.WishListResponse;
import com.flipkart.android.utils.ScreenMathUtils;
import java.util.ArrayList;
import java.util.Collections;

// Referenced classes of package com.flipkart.android.activity:
//            HomeFragmentHolderActivity

final class i extends WishListVDataHandler
{

    private HomeFragmentHolderActivity a;

    i(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        a = homefragmentholderactivity;
        super();
    }

    public final void errorReceived(int j, int k, String s)
    {
        HomeFragmentHolderActivity.b(a, false);
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
                Collections.reverse(arraylist1);
                for (int j = 0; j < arraylist1.size(); j++)
                {
                    arraylist.add(new WishList((String)arraylist1.get(j), ScreenMathUtils.getCurrentLinuxTimeInSeconds() + (long)j));
                }

                wishlistdao.createInBulk(arraylist);
            }
        }
        HomeFragmentHolderActivity.b(a, true);
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((WishListResponse)obj, flag);
    }
}
