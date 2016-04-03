// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.flipkart.android.customviews.ExpandablePanel;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class SplitPanelWidget extends BaseWidget
{

    public static final String WIDGET_COMMON_NAME = "SPLITPANEL";
    String requestId;
    ExpandablePanel splitPanel;

    public SplitPanelWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist, String s, 
            Activity activity, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        requestId = null;
        requestId = s;
        FkLogger.debug("Test", "into splitpanelwidget");
        splitPanel = (ExpandablePanel)inflate(context, 0x7f0300be, null);
        if (splitPanel != null)
        {
            addView(splitPanel);
        }
        buildExpandableView(arraylist);
    }

    private void buildExpandableView(ArrayList arraylist)
    {
        View view = new View(context);
        view.setMinimumHeight(1000);
        ((LinearLayout)splitPanel.getmContent()).addView(view);
        splitPanel.requestLayout();
    }
}
