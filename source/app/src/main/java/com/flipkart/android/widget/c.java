// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.widget;

import android.content.Context;
import android.widget.RemoteViews;
import com.android.volley.VolleyError;
import com.flipkart.android.config.FlipkartPreferenceManager;

// Referenced classes of package com.flipkart.android.widget:
//            FlipkartAppWidget

final class c
    implements com.android.volley.toolbox.ImageLoader.ImageListener
{

    private Context a;
    private FlipkartAppWidget b;

    c(FlipkartAppWidget flipkartappwidget, Context context)
    {
        b = flipkartappwidget;
        a = context;
        super();
    }

    public final void onErrorResponse(VolleyError volleyerror)
    {
    }

    public final void onResponse(com.android.volley.toolbox.ImageLoader.ImageContainer imagecontainer, boolean flag)
    {
        b.a = new RemoteViews(a.getPackageName(), 0x7f030022);
        FlipkartAppWidget.a(b, b.a);
        b.a.setImageViewBitmap(0x7f0a0083, imagecontainer.getBitmap());
        FlipkartPreferenceManager.instance().saveAppWidgetCurrentImagePosition(0);
        FlipkartAppWidget.pushWidgetUpdate(a, b.a);
    }
}
