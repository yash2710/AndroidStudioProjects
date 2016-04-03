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
import com.flipkart.android.utils.PmuWidgetBuilder;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            ScrollWidget

public class PMUWidget extends ScrollWidget
{

    private static final String TAG = (new StringBuilder("ASIMO.")).append(com/flipkart/android/customwidget/PMUWidget.getSimpleName()).toString();
    public static final String WIDGET_COMMON_NAME = "PMU";
    private String requestId;

    public PMUWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist, String s, 
            Activity activity, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, true, true, activity, i);
        requestId = s;
        if (arraylist != null && arraylist.size() > 0)
        {
            if (widgetitem != null)
            {
                drawWidget(arraylist, widgetitem.getAction());
                return;
            } else
            {
                drawWidget(arraylist, null);
                return;
            }
        } else
        {
            setTitleGone();
            setVisibility(8);
            return;
        }
    }

    private void drawWidget(ArrayList arraylist, Action action)
    {
        if (!StringUtils.isNullOrEmpty(arraylist) && PmuWidgetBuilder.buildPmuWidget(context, onClickListener, FlipkartApplication.getImageLoader(), arraylist, scrollLayout, true, action))
        {
            return;
        } else
        {
            setVisibility(8);
            setTitleGone();
            return;
        }
    }

    public void updateWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist)
    {
label0:
        {
            super.updateWidget(context, layoutdetails, widgettheme, onclicklistener, widgetitem, arraylist);
            if (arraylist != null && arraylist.size() > 0)
            {
                if (widgetitem == null)
                {
                    break label0;
                }
                drawWidget(arraylist, widgetitem.getAction());
            }
            return;
        }
        drawWidget(arraylist, null);
    }

}
