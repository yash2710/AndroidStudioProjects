// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.layout.LayoutDetails;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class TitleWidget extends BaseWidget
{

    public static final String WIDGET_COMMON_NAME = "TITLE";

    public TitleWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, Activity activity, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
    }
}
