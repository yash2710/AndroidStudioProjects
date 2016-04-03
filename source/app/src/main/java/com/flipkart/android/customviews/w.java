// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.customviews:
//            OfferExpandablePanel, v, x

final class w
    implements android.view.View.OnClickListener
{

    final OfferExpandablePanel a;

    private w(OfferExpandablePanel offerexpandablepanel)
    {
        a = offerexpandablepanel;
        super();
    }

    w(OfferExpandablePanel offerexpandablepanel, byte byte0)
    {
        this(offerexpandablepanel);
    }

    public final void onClick(View view)
    {
        FkLogger.debug("ExpandablePanel", "Panel handle clicked... ");
        v v1;
        if (OfferExpandablePanel.a(a))
        {
            if (OfferExpandablePanel.b(a) != null)
            {
                OfferExpandablePanel.b(a).setVisibility(0);
            }
            v1 = new v(a, OfferExpandablePanel.c(a), OfferExpandablePanel.d(a));
            OfferExpandablePanel.g(a).onCollapse(OfferExpandablePanel.e(a), OfferExpandablePanel.f(a));
        } else
        {
            if (OfferExpandablePanel.b(a) != null)
            {
                OfferExpandablePanel.b(a).setVisibility(8);
            }
            v1 = new v(a, OfferExpandablePanel.d(a), OfferExpandablePanel.c(a));
            OfferExpandablePanel.g(a).onExpand(OfferExpandablePanel.e(a), OfferExpandablePanel.f(a));
        }
        v1.setDuration(OfferExpandablePanel.h(a));
        v1.setAnimationListener(new x(this));
        OfferExpandablePanel.f(a).startAnimation(v1);
    }
}
