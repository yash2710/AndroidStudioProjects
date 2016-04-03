// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ScreenMathUtils;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public abstract class ScrollWidget extends BaseWidget
{

    protected HorizontalScrollView horizontalScrollView;
    public LinearLayout scrollLayout;

    public ScrollWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, boolean flag, boolean flag1, 
            Activity activity, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        horizontalScrollView = new HorizontalScrollView(context);
        addView(horizontalScrollView);
        setLayoutParameters(flag, flag1);
    }

    private void setLayoutParameters(boolean flag, boolean flag1)
    {
        setLineatLayoutParams(flag, flag1);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
    }

    private void setLineatLayoutParams(boolean flag, boolean flag1)
    {
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -2);
        scrollLayout = new LinearLayout(context);
        if (flag1)
        {
            layoutparams.setMargins(ScreenMathUtils.dpToPx(5, context), ScreenMathUtils.dpToPx(5, context), ScreenMathUtils.dpToPx(5, context), ScreenMathUtils.dpToPx(5, context));
        }
        scrollLayout.setLayoutParams(layoutparams);
        if (flag)
        {
            scrollLayout.setOrientation(0);
        } else
        {
            scrollLayout.setOrientation(1);
        }
        horizontalScrollView.addView(scrollLayout);
    }
}
