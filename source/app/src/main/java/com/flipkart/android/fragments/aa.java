// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageFragment

final class aa
    implements Runnable
{

    private View a;
    private ProductPageFragment b;

    aa(ProductPageFragment productpagefragment, View view)
    {
        b = productpagefragment;
        a = view;
        super();
    }

    public final void run()
    {
        ProductPageFragment.a(b, a);
    }
}
