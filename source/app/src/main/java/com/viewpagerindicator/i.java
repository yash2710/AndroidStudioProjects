// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.view.View;

// Referenced classes of package com.viewpagerindicator:
//            TabPageIndicator

final class i
    implements Runnable
{

    private View a;
    private TabPageIndicator b;

    i(TabPageIndicator tabpageindicator, View view)
    {
        b = tabpageindicator;
        a = view;
        super();
    }

    public final void run()
    {
        int j = a.getLeft() - (b.getWidth() - a.getWidth()) / 2;
        b.smoothScrollTo(j, 0);
        TabPageIndicator.a(b, null);
    }
}
