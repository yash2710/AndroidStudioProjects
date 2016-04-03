// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.view.View;

// Referenced classes of package com.flipkart.android.customwidget:
//            SiteWideOffersWidget

class this._cls0
    implements android.view.teWideOffersWidget._cls1
{

    final SiteWideOffersWidget this$0;

    public void onClick(View view)
    {
        String s = (String)view.getTag();
        if (s.contains("offer_terms"))
        {
            String s1 = s.split("/")[1];
            createOfferTermsDialog(SiteWideOffersWidget.access$000());
            showOfferTermsAndConditions(s1, "");
        }
    }

    ()
    {
        this$0 = SiteWideOffersWidget.this;
        super();
    }
}
