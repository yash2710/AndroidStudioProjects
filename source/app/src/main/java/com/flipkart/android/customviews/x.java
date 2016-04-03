// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.animation.Animation;
import android.widget.TextView;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.customviews:
//            w, OfferExpandablePanel

final class x
    implements android.view.animation.Animation.AnimationListener
{

    private w a;

    x(w w1)
    {
        a = w1;
        super();
    }

    public final void onAnimationEnd(Animation animation)
    {
        OfferExpandablePanel offerexpandablepanel;
        boolean flag;
        boolean flag1;
        if (OfferExpandablePanel.a(a.a))
        {
            if (OfferExpandablePanel.i(a.a))
            {
                OfferExpandablePanel.e(a.a).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f0200dd, 0);
            } else
            if (OfferExpandablePanel.j(a.a) != null && OfferExpandablePanel.j(a.a).contains("see_full_offer"))
            {
                OfferExpandablePanel.e(a.a).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f0200dd, 0);
                OfferExpandablePanel.e(a.a).setText(StringUtils.getHyperLinkedText("see full offer"));
            } else
            if (OfferExpandablePanel.j(a.a) != null && OfferExpandablePanel.j(a.a).contains("product_list"))
            {
                OfferExpandablePanel.e(a.a).setCompoundDrawablesWithIntrinsicBounds(0x7f02011c, 0, 0x7f020145, 0);
            } else
            if (OfferExpandablePanel.j(a.a) != null && OfferExpandablePanel.j(a.a).contains("product_page_offers"))
            {
                OfferExpandablePanel.e(a.a).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020091, 0);
            } else
            {
                OfferExpandablePanel.e(a.a).setCompoundDrawablesWithIntrinsicBounds(0x7f02011c, 0, 0x7f0200dd, 0);
            }
        } else
        if (OfferExpandablePanel.i(a.a))
        {
            OfferExpandablePanel.e(a.a).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020167, 0);
        } else
        if (OfferExpandablePanel.j(a.a) != null && OfferExpandablePanel.j(a.a).contains("see_full_offer"))
        {
            OfferExpandablePanel.e(a.a).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020167, 0);
        } else
        if (OfferExpandablePanel.j(a.a) != null && OfferExpandablePanel.j(a.a).contains("product_list"))
        {
            OfferExpandablePanel.e(a.a).setCompoundDrawablesWithIntrinsicBounds(0x7f02011c, 0, 0x7f020144, 0);
        } else
        if (OfferExpandablePanel.j(a.a) != null && OfferExpandablePanel.j(a.a).contains("product_page_offers"))
        {
            TrackingHelper.sendViewMoreClicked();
            OfferExpandablePanel.e(a.a).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020176, 0);
        } else
        {
            OfferExpandablePanel.e(a.a).setCompoundDrawablesWithIntrinsicBounds(0x7f02011c, 0, 0x7f020167, 0);
        }
        offerexpandablepanel = a.a;
        flag = OfferExpandablePanel.a(a.a);
        flag1 = false;
        if (!flag)
        {
            flag1 = true;
        }
        OfferExpandablePanel.a(offerexpandablepanel, flag1);
    }

    public final void onAnimationRepeat(Animation animation)
    {
    }

    public final void onAnimationStart(Animation animation)
    {
    }
}
