// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.view.View;
import com.flipkart.android.datahandler.WishListVDataHandler;
import com.flipkart.android.response.wishlist.WishListJsonResponse;
import com.flipkart.android.response.wishlist.WishListResponse;

// Referenced classes of package com.flipkart.android.utils:
//            CustomDialog, WishListUtils, StringUtils, PageTypeUtils

final class H extends WishListVDataHandler
{

    private String a;
    private View b;
    private PageTypeUtils c;
    private Activity d;

    H(String s, View view, PageTypeUtils pagetypeutils, Activity activity)
    {
        a = s;
        b = view;
        c = pagetypeutils;
        d = activity;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        String s1 = (new StringBuilder("Delete from WishList Failed.")).append(CustomDialog.getErrorMessage(i)).toString();
        WishListUtils.a(a, b, c, d, s1);
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
                    WishListUtils.a(a, b, c);
                }
            }
            return;
        }
        String s = wishlistjsonresponse.getErrorMessage();
        if (StringUtils.isNullOrEmpty(s))
        {
            s = "Delete from WishList Failed.Please try again";
        }
        WishListUtils.a(a, b, c, d, s);
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((WishListResponse)obj, flag);
    }
}
