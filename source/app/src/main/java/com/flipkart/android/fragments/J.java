// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.widget.AbsListView;
import com.flipkart.android.customviews.EnhancedListView;

// Referenced classes of package com.flipkart.android.fragments:
//            InAppNotificationFragment

final class J
    implements android.widget.AbsListView.OnScrollListener
{

    private InAppNotificationFragment a;

    J(InAppNotificationFragment inappnotificationfragment)
    {
        a = inappnotificationfragment;
        super();
    }

    public final void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        if (k != 0 && i + j > k - 2)
        {
            InAppNotificationFragment.k(a);
        }
    }

    public final void onScrollStateChanged(AbsListView abslistview, int i)
    {
        boolean flag = true;
        EnhancedListView enhancedlistview = InAppNotificationFragment.j(a);
        if (i != flag)
        {
            flag = false;
        }
        enhancedlistview.mSwipePaused = flag;
    }
}
