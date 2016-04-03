// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;

// Referenced classes of package com.flipkart.android.DB:
//            WishList, WishListDao

final class k
    implements Callable
{

    private ArrayList a;
    private WishListDao b;

    k(WishListDao wishlistdao, ArrayList arraylist)
    {
        b = wishlistdao;
        a = arraylist;
        super();
    }

    public final volatile Object call()
    {
        return call();
    }

    public final Void call()
    {
        WishList wishlist;
        for (Iterator iterator = a.iterator(); iterator.hasNext(); b.create(wishlist))
        {
            wishlist = (WishList)iterator.next();
        }

        return null;
    }
}
