// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import com.flipkart.android.datahandler.WishListVDataHandler;
import com.flipkart.android.response.wishlist.WishListJsonResponse;
import com.flipkart.android.response.wishlist.WishListResponse;
import com.flipkart.android.utils.CustomDialog;

// Referenced classes of package com.flipkart.android.fragments:
//            WishListFragment

final class bI extends WishListVDataHandler
{

    private View a;
    private WishListFragment b;

    bI(WishListFragment wishlistfragment, View view)
    {
        b = wishlistfragment;
        a = view;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        String s1 = (new StringBuilder("Delete All from WishList Failed")).append(CustomDialog.getErrorMessage(i)).toString();
        WishListFragment.a(b, true, false, "", a, s1);
    }

    public final void resultReceived(WishListResponse wishlistresponse, boolean flag)
    {
        if (wishlistresponse != null)
        {
            WishListJsonResponse wishlistjsonresponse = wishlistresponse.getWishlist();
            if (wishlistjsonresponse != null)
            {
                WishListFragment.a(b, true, wishlistjsonresponse.isSuccess(), "", a, wishlistjsonresponse.getErrorMessage());
            }
        }
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((WishListResponse)obj, flag);
    }
}
