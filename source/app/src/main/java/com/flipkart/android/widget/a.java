// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.widget;

import android.widget.RemoteViews;
import com.android.volley.VolleyError;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.init.FlipkartApplication;

// Referenced classes of package com.flipkart.android.widget:
//            FlipkartAppWidget

final class a
    implements com.android.volley.toolbox.ImageLoader.ImageListener
{

    private int a;
    private FlipkartAppWidget b;

    a(FlipkartAppWidget flipkartappwidget, int i)
    {
        b = flipkartappwidget;
        a = i;
        super();
    }

    public final void onErrorResponse(VolleyError volleyerror)
    {
    }

    public final void onResponse(com.android.volley.toolbox.ImageLoader.ImageContainer imagecontainer, boolean flag)
    {
        if (imagecontainer.getBitmap() != null)
        {
            b.a.setImageViewBitmap(0x7f0a0083, imagecontainer.getBitmap());
            FlipkartAppWidget.pushWidgetUpdate(FlipkartApplication.getAppContext(), b.a);
            FlipkartPreferenceManager.instance().saveAppWidgetCurrentImagePosition(a);
        }
    }
}
