// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.widget.AbsListView;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.utils:
//            ProductPageUgcBuilder, ProductPageReviewContext

final class u
    implements android.widget.AbsListView.OnScrollListener
{

    u()
    {
    }

    public final void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        if (k != 0 && i + j > k - 2 && (long)ProductPageUgcBuilder.b().getReviewList().size() < ProductPageUgcBuilder.b().getTotalReviewCount())
        {
            ProductPageUgcBuilder.d();
        }
    }

    public final void onScrollStateChanged(AbsListView abslistview, int i)
    {
    }
}
