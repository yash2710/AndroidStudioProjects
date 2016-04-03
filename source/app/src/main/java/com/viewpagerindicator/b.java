// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.view.View;

// Referenced classes of package com.viewpagerindicator:
//            IconPageIndicator

final class b
    implements Runnable
{

    private View a;
    private IconPageIndicator b;

    b(IconPageIndicator iconpageindicator, View view)
    {
        b = iconpageindicator;
        a = view;
        super();
    }

    public final void run()
    {
        int i = a.getLeft() - (b.getWidth() - a.getWidth()) / 2;
        b.smoothScrollTo(i, 0);
        IconPageIndicator.a(b, null);
    }
}
