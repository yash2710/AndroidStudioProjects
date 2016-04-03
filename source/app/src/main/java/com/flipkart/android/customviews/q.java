// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;
import android.view.animation.Animation;

// Referenced classes of package com.flipkart.android.customviews:
//            ExpandablePanel, p

final class q
    implements android.view.View.OnClickListener
{

    private ExpandablePanel a;

    private q(ExpandablePanel expandablepanel)
    {
        a = expandablepanel;
        super();
    }

    q(ExpandablePanel expandablepanel, byte byte0)
    {
        this(expandablepanel);
    }

    public final void onClick(View view)
    {
        p p1;
        ExpandablePanel expandablepanel;
        boolean flag;
        if (ExpandablePanel.a(a))
        {
            p1 = new p(a, ExpandablePanel.b(a), ExpandablePanel.c(a));
            ExpandablePanel.f(a).onCollapse(ExpandablePanel.d(a), ExpandablePanel.e(a));
        } else
        {
            p1 = new p(a, ExpandablePanel.c(a), ExpandablePanel.b(a));
            ExpandablePanel.f(a).onExpand(ExpandablePanel.d(a), ExpandablePanel.e(a));
        }
        p1.setDuration(ExpandablePanel.g(a));
        ExpandablePanel.e(a).startAnimation(p1);
        expandablepanel = a;
        if (!ExpandablePanel.a(a))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ExpandablePanel.a(expandablepanel, flag);
    }
}
