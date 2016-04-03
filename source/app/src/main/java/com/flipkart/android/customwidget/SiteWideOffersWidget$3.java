// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Dialog;
import android.view.View;

// Referenced classes of package com.flipkart.android.customwidget:
//            SiteWideOffersWidget

class this._cls0
    implements android.view.teWideOffersWidget._cls3
{

    final SiteWideOffersWidget this$0;

    public void onClick(View view)
    {
        if (SiteWideOffersWidget.access$300(SiteWideOffersWidget.this) != null && SiteWideOffersWidget.access$300(SiteWideOffersWidget.this).isShowing())
        {
            SiteWideOffersWidget.access$300(SiteWideOffersWidget.this).dismiss();
        }
    }

    ()
    {
        this$0 = SiteWideOffersWidget.this;
        super();
    }
}
