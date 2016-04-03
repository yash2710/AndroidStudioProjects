// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.DotdWidgetBuilder;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.flipkart.android.customwidget:
//            OMUParentWidget

public class DotdWidget extends OMUParentWidget
{

    public static final String WIDGET_COMMON_NAME = "DOTD";

    public DotdWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, List list, boolean flag, 
            String s, boolean flag1, Activity activity, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, (ArrayList)list, s, activity, i);
    }

    protected void drawWidget(ArrayList arraylist, Action action)
    {
        if (!StringUtils.isNullOrEmpty(arraylist) && DotdWidgetBuilder.buildDotdWidget(activity, context, onClickListener, FlipkartApplication.getImageLoader(), arraylist, scrollLayout, true, action))
        {
            return;
        } else
        {
            setVisibility(8);
            setTitleGone();
            return;
        }
    }
}
