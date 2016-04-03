// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;

// Referenced classes of package com.flipkart.android.customviews:
//            TabbedItemSelector

final class G
    implements Runnable
{

    private View a;
    private TabbedItemSelector b;

    G(TabbedItemSelector tabbeditemselector, View view)
    {
        b = tabbeditemselector;
        a = view;
        super();
    }

    public final void run()
    {
        int i = a.getLeft() - (b.getWidth() - a.getWidth()) / 2;
        b.smoothScrollTo(i, 0);
        TabbedItemSelector.a(b, null);
    }
}
