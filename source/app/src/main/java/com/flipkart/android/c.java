// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android;

import com.flipkart.android.datahandler.WishListVDataHandler;
import com.flipkart.android.response.wishlist.WishListResponse;

// Referenced classes of package com.flipkart.android:
//            SplashActivity

final class c extends WishListVDataHandler
{

    c(SplashActivity splashactivity)
    {
    }

    public final void errorReceived(int i, int j, String s)
    {
    }

    public final void resultReceived(WishListResponse wishlistresponse, boolean flag)
    {
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((WishListResponse)obj, flag);
    }
}
