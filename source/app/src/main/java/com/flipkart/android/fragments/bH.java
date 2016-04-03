// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Dialog;
import android.view.View;
import com.flipkart.android.log.CrashLoggerUtils;

// Referenced classes of package com.flipkart.android.fragments:
//            WishListFragment

final class bH
    implements android.view.View.OnClickListener
{

    private Dialog a;
    private WishListFragment b;

    bH(WishListFragment wishlistfragment, Dialog dialog)
    {
        b = wishlistfragment;
        a = dialog;
        super();
    }

    public final void onClick(View view)
    {
        CrashLoggerUtils.pushAndUpdate("deleting complete wishlist");
        b.deleteFromWishList(true, null, null, null);
        if (a != null)
        {
            a.dismiss();
        }
    }
}
