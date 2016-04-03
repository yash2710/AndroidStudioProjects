// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import com.flipkart.android.datahandler.WishListVDataHandler;
import com.flipkart.android.response.wishlist.WishListJsonResponse;
import com.flipkart.android.response.wishlist.WishListResponse;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.WishListUtils;

// Referenced classes of package com.flipkart.android.fragments:
//            WishListFragment

final class bJ extends WishListVDataHandler
{

    private String a;
    private View b;
    private WishListFragment c;

    bJ(WishListFragment wishlistfragment, String s, View view)
    {
        c = wishlistfragment;
        a = s;
        b = view;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        String s1 = (new StringBuilder("Delete from WishList Failed")).append(CustomDialog.getErrorMessage(i)).toString();
        WishListFragment.a(c, false, false, a, b, s1);
    }

    public final void resultReceived(WishListResponse wishlistresponse, boolean flag)
    {
        WishListJsonResponse wishlistjsonresponse;
label0:
        {
            if (wishlistresponse != null)
            {
                wishlistjsonresponse = wishlistresponse.getWishlist();
                if (wishlistjsonresponse != null)
                {
                    if (!wishlistjsonresponse.isSuccess())
                    {
                        break label0;
                    }
                    WishListUtils.deleteEntryFromWishListDB(a);
                    WishListFragment.a(c, false, true, a, b, "");
                }
            }
            return;
        }
        String s = wishlistjsonresponse.getErrorMessage();
        if (StringUtils.isNullOrEmpty(s))
        {
            s = "Delete from WishList Failed.Please try again";
        }
        WishListFragment.a(c, false, false, a, b, s);
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((WishListResponse)obj, flag);
    }
}
