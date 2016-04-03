// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import com.flipkart.android.customviews.ViewPagerFixed;
import net.simonvt.menudrawer.LeftDrawer;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageFragment

final class ak
    implements android.widget.AdapterView.OnItemClickListener
{

    private ProductPageFragment a;

    ak(ProductPageFragment productpagefragment)
    {
        a = productpagefragment;
        super();
    }

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        ProductPageFragment.b(a, i);
        ProductPageFragment.i(a).setCurrentItem(i);
        if (a.activity.findViewById(0x7f0a01de) != null)
        {
            LeftDrawer leftdrawer = (LeftDrawer)a.activity.findViewById(0x7f0a01de);
            if (leftdrawer.isMenuVisible())
            {
                leftdrawer.closeMenu();
            }
        }
    }
}
