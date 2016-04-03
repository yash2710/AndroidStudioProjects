// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.HeaderValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.response.customwidgetitemvalue.TrackingParams;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.component.ComponentWidgetUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            NewTitleWidget

public abstract class BaseWidget extends LinearLayout
{

    protected Activity activity;
    protected Context context;
    int hashTag;
    protected LayoutDetails layoutDetails;
    protected android.view.View.OnClickListener onClickListener;
    int screenDpi;
    private boolean showTitle;
    protected WidgetTheme theme;
    private View titleView;
    private TrackingParams trackingParams;

    public BaseWidget(Context context1, LayoutDetails layoutdetails, WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, Activity activity1, int i)
    {
        super(context1);
        showTitle = true;
        screenDpi = -1;
        hashTag = 0;
        setOrientation(1);
        if (layoutdetails != null)
        {
            if (layoutdetails.getTheme() != null)
            {
                theme = layoutdetails.getThemeObject();
            } else
            {
                theme = widgettheme;
            }
        } else
        {
            theme = widgettheme;
        }
        FkLogger.debug("Title", (new StringBuilder("theme in the base widget for ")).append(getClass().toString()).append(" is ").append(theme).append(" widget layout details is ").append(layoutdetails).append(" coming theme us ").append(widgettheme).toString());
        onClickListener = onclicklistener;
        layoutDetails = layoutdetails;
        context = context1;
        activity = activity1;
        screenDpi = ScreenMathUtils.getScreenDpi(context1);
        hashTag = i;
        if (widgetitem != null && widgetitem.getAction() != null)
        {
            trackingParams = widgetitem.getAction().getTracking();
        }
        if (layoutDetails != null)
        {
            showTitle = layoutDetails.isShowTitle();
        }
        if (widgetitem != null && showTitle)
        {
            renderTitle(widgetitem);
        }
        if (layoutdetails != null)
        {
            setWidgetBackground(layoutDetails, this);
            setWidgetPadding(layoutDetails, this);
        }
    }

    public int getHashTag()
    {
        return hashTag;
    }

    protected void renderTitle(WidgetItem widgetitem)
    {
        FkLogger.debug("Title", (new StringBuilder("render title widget ")).append(getClass().toString()).toString());
        View view = getChildAt(0);
        if (view instanceof NewTitleWidget)
        {
            FkLogger.debug("Title", "titleWidget is there...");
            titleView = view;
            ComponentWidgetUtils.buildTitleWidget((NewTitleWidget)titleView, getContext(), (HeaderValue)widgetitem.getValue(), widgetitem.getAction(), theme, activity);
            titleView.setOnClickListener(onClickListener);
            titleView.setTag(widgetitem.getAction());
        } else
        {
            titleView = ComponentWidgetUtils.buildTitleWidget(null, getContext(), (HeaderValue)widgetitem.getValue(), widgetitem.getAction(), theme, activity);
            FkLogger.debug("Title", "titleWidget is not there...");
            if (titleView != null)
            {
                titleView.setOnClickListener(onClickListener);
                titleView.setTag(widgetitem.getAction());
                addView(titleView, 0);
                return;
            }
        }
    }

    public void setHashTag(int i)
    {
        hashTag = i;
    }

    protected void setTitleGone()
    {
        if (titleView != null)
        {
            titleView.setVisibility(8);
        }
    }

    protected void setWidgetBackground(LayoutDetails layoutdetails, View view)
    {
        ComponentWidgetUtils.setWidgetBackground(layoutdetails, view);
    }

    protected void setWidgetPadding(LayoutDetails layoutdetails, View view)
    {
        ComponentWidgetUtils.setWidgetPadding(layoutdetails, view);
    }

    protected void updateTitle(WidgetItem widgetitem)
    {
        if (widgetitem != null)
        {
            renderTitle(widgetitem);
        }
    }

    public void updateWidget(Context context1, LayoutDetails layoutdetails, WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist)
    {
        context = context1;
        layoutDetails = layoutdetails;
        theme = widgettheme;
        onClickListener = onclicklistener;
        if (layoutdetails != null)
        {
            setWidgetBackground(layoutDetails, this);
            setWidgetPadding(layoutDetails, this);
        }
        if (widgetitem != null)
        {
            renderTitle(widgetitem);
        }
    }
}
