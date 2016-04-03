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

final class G extends WishListVDataHandler
{

    private String a;
    private String b;
    private View c;
    private PageTypeUtils d;
    private Activity e;

    G(String s, String s1, View view, PageTypeUtils pagetypeutils, Activity activity)
    {
        a = s;
        b = s1;
        c = view;
        d = pagetypeutils;
        e = activity;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        String s1 = (new StringBuilder("Add to WishList Failed.")).append(CustomDialog.getErrorMessage(i)).toString();
        WishListUtils.a(a, b, c, d, e, s1);
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
                    WishListUtils.insertEntryInWishListDB(a);
                    WishListUtils.a(a, b, c, d);
                }
            }
            return;
        }
        String s = wishlistjsonresponse.getErrorMessage();
        if (StringUtils.isNullOrEmpty(s))
        {
            s = "Add to WishList Failed.Please try again";
        }
        WishListUtils.a(a, b, c, d, e, s);
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((WishListResponse)obj, flag);
    }
}
