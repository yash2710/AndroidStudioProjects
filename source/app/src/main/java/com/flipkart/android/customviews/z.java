// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;

// Referenced classes of package com.flipkart.android.customviews:
//            Panel

final class z
    implements android.view.View.OnClickListener
{

    private Panel a;

    z(Panel panel)
    {
        a = panel;
        super();
    }

    public final void onClick(View view)
    {
        if (Panel.b(a))
        {
            a.bringToFront();
        }
        if (a.initChange())
        {
            a.post(a.a);
        }
    }
}
