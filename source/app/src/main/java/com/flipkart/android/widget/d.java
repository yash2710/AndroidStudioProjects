// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.widget;

import com.flipkart.android.datahandler.AndroidWidgetVDataHandler;
import com.flipkart.android.response.android.widget.AndroidWidgetResponseWrapper;

// Referenced classes of package com.flipkart.android.widget:
//            FlipkartAppWidget

final class d extends AndroidWidgetVDataHandler
{

    private FlipkartAppWidget a;

    d(FlipkartAppWidget flipkartappwidget)
    {
        a = flipkartappwidget;
        super();
    }

    public final void resultReceived(AndroidWidgetResponseWrapper androidwidgetresponsewrapper, boolean flag)
    {
        if (androidwidgetresponsewrapper != null)
        {
            a.createImageUrlList();
        }
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((AndroidWidgetResponseWrapper)obj, flag);
    }
}
