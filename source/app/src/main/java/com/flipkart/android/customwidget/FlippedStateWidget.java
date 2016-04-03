// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ScreenMathUtils;
import java.util.ArrayList;
import java.util.Map;

public class FlippedStateWidget extends LinearLayout
{

    public static final String WIDGET_COMMON_NAME = "ANNOUNCEMENT";
    private android.view.View.OnClickListener clickListner;
    private Context context;
    private ImageLoader imageLoader;
    private ArrayList items;
    private LayoutDetails layoutDetails;
    String requestId;
    private int screenDpi;
    private int screenWidth;
    private long timeStamp;
    private int widgetHeight;

    public FlippedStateWidget(Context context1)
    {
        super(context1);
        items = null;
        widgetHeight = 0;
        screenDpi = 320;
        timeStamp = -1L;
        imageLoader = null;
    }

    public FlippedStateWidget(Context context1, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist, ImageLoader imageloader)
    {
        super(context1);
        items = null;
        widgetHeight = 0;
        screenDpi = 320;
        timeStamp = -1L;
        imageLoader = null;
        context = context1;
        items = arraylist;
        clickListner = onclicklistener;
        imageLoader = imageloader;
        if (arraylist != null && arraylist.size() > 0)
        {
            screenWidth = getResources().getDisplayMetrics().widthPixels;
            screenDpi = ScreenMathUtils.getScreenDpi(context1);
            setOrientation(1);
            addView(getWidgetView(arraylist));
        }
    }

    private void addRequestIdToActionParamsExplicitly(Action action)
    {
        action.getParams().put("REQUEST_ID", requestId);
    }

    private android.widget.RelativeLayout.LayoutParams setCustomRelativeLayoutParams(int i, boolean flag)
    {
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        if (flag)
        {
            layoutparams.addRule(12);
            layoutparams.addRule(14);
        }
        return layoutparams;
    }

    private void setHeading(Action action, int i)
    {
        if (action != null)
        {
            Map map = action.getParams();
            if (map != null)
            {
                map.put("position", Integer.valueOf(i + 1));
            }
            action.setParams(map);
        }
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public LinearLayout getWidgetView(ArrayList arraylist)
    {
        WidgetItem widgetitem = (WidgetItem)items.get(0);
        if (widgetitem != null)
        {
            setHeading(widgetitem.getAction(), 0);
            addRequestIdToActionParamsExplicitly(widgetitem.getAction());
            LinearLayout linearlayout = (LinearLayout)((Activity)context).getLayoutInflater().inflate(0x7f030048, null);
            ((ImageView)linearlayout.findViewById(0x7f0a00ec)).setOnClickListener(clickListner);
            ((LinearLayout)linearlayout.findViewById(0x7f0a00ed)).setOnClickListener(clickListner);
            ((Button)linearlayout.findViewById(0x7f0a00ef)).setOnClickListener(clickListner);
            ((LinearLayout)linearlayout.findViewById(0x7f0a00f0)).setOnClickListener(clickListner);
            return linearlayout;
        } else
        {
            return null;
        }
    }

    public void setTimeStamp(long l)
    {
        timeStamp = l;
    }

    public void updateView(ArrayList arraylist, LayoutDetails layoutdetails, android.view.View.OnClickListener onclicklistener, long l, ImageLoader imageloader, String s)
    {
        items = arraylist;
        clickListner = onclicklistener;
        timeStamp = l;
        imageLoader = imageloader;
        requestId = s;
        layoutDetails = layoutdetails;
    }
}
