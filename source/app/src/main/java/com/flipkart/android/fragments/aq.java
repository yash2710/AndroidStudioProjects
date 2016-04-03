// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;


// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageFragment

final class aq
    implements Runnable
{

    private ProductPageFragment a;

    aq(ProductPageFragment productpagefragment)
    {
        a = productpagefragment;
        super();
    }

    public final void run()
    {
        a.isRefreshing = false;
        a.closeRefresing();
    }
}
