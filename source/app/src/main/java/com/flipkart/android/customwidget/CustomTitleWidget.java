// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.component.ComponentWidgetUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class CustomTitleWidget extends BaseWidget
{

    public static String COMMON_NAME_HOME = "TITLE_HOME";
    public static String COMMON_NAME_OFFER = "TITLE_CLP_OFFERS";
    public static int TYPE_HOME = 1;
    public static int TYPE_OFFER = 2;
    private int titleType;

    public CustomTitleWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, int i, Activity activity, 
            int j)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, j);
        titleType = i;
        renderTitle();
    }

    private Action getActionForType()
    {
        if (titleType == TYPE_HOME)
        {
            return AppConfigUtils.getInstance().getHomeWidgetAction();
        }
        if (titleType == TYPE_OFFER)
        {
            return AppConfigUtils.getInstance().getOfferWidgetAction();
        } else
        {
            return null;
        }
    }

    private void renderTitle()
    {
        FkLogger.debug("Title", (new StringBuilder("render title widget ")).append(getClass().toString()).toString());
        String s;
        android.graphics.drawable.Drawable drawable;
        Action action;
        View view;
        if (titleType == TYPE_HOME)
        {
            s = "Home";
            drawable = context.getResources().getDrawable(0x7f0200fb);
            action = getActionForType();
        } else
        {
            s = "Offers";
            drawable = context.getResources().getDrawable(0x7f020120);
            action = getActionForType();
        }
        view = ComponentWidgetUtils.buildTitleWidget(null, context, s, drawable, null, theme, false, activity);
        view.setTag(action);
        view.setContentDescription("Main_Home");
        view.setOnClickListener(onClickListener);
        addView(view);
    }

    protected void renderTitle(WidgetItem widgetitem)
    {
    }

    public void updateWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist)
    {
    }

}
