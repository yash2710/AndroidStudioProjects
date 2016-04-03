// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.widget.AbsListView;

// Referenced classes of package com.flipkart.android.customwidget:
//            OMUInfiniteListView

class <init>
    implements android.widget.stOnScrollListener
{

    boolean isDownAnimationDone;
    boolean isUpAnimationDone;
    final OMUInfiniteListView this$0;

    public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        if (k != 0 && i + j > k - 2 && !isRefreshing && !isNoMoreDataToDownload())
        {
            OMUInfiniteListView.access$800(OMUInfiniteListView.this);
        }
    }

    public void onScrollStateChanged(AbsListView abslistview, int i)
    {
    }

    private ()
    {
        this$0 = OMUInfiniteListView.this;
        super();
        isUpAnimationDone = false;
        isDownAnimationDone = false;
    }

    isDownAnimationDone(isDownAnimationDone isdownanimationdone)
    {
        this();
    }
}
