// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import com.flipkart.android.utils.ScreenMathUtils;

public class SplitterWidget extends View
{

    public static final String WIDGET_COMMON_NAME = "SPLITTERVIEW";

    public SplitterWidget(Context context, BaseWidget.WidgetTheme widgettheme)
    {
        super(context);
        if (widgettheme.equals(BaseWidget.WidgetTheme.dark))
        {
            setBackgroundDrawable(new ColorDrawable(-1));
        } else
        {
            setBackgroundDrawable(new ColorDrawable(0xffdcdcdc));
        }
        setMinimumHeight(ScreenMathUtils.dpToPx(1, context));
    }
}
