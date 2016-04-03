// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.component;

import android.view.View;
import com.flipkart.android.customwidget.OMUInfiniteListView;
import com.flipkart.logging.FkLogger;

final class c
    implements com.flipkart.android.customwidget.CollapsedExpandableWidget.OnClickItemListner
{

    private View a;

    c(View view)
    {
        a = view;
        super();
    }

    public final void onClickedItem(String s, String s1)
    {
        View view = a.findViewWithTag(s);
        FkLogger.debug("Title", (new StringBuilder("View tag is ")).append(s).append(" view is ").append(view).toString());
        if (view != null && (view instanceof OMUInfiniteListView))
        {
            ((OMUInfiniteListView)view).resetListViewParams(s1);
        }
    }
}
