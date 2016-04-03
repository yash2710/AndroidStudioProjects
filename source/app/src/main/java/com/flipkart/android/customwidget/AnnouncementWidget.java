// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.AnnouncementValue;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class AnnouncementWidget extends BaseWidget
{

    public static final String WIDGET_COMMON_NAME = "ANNOUNCEMENT";
    private Context context;
    private ArrayList items;
    String requestId;

    public AnnouncementWidget(Context context1, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist, Activity activity, 
            int i)
    {
        super(context1, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        items = null;
        context = context1;
        items = arraylist;
        if (arraylist != null && arraylist.size() > 0)
        {
            setOrientation(1);
            setGravity(16);
            addView(getWidgetView());
        }
    }

    private void addRequestIdToActionParamsExplicitly(Action action)
    {
        Map map = action.getParams();
        if (map == null)
        {
            LinkedHashMap linkedhashmap = new LinkedHashMap();
            linkedhashmap.put("REQUEST_ID", requestId);
            action.setParams(linkedhashmap);
            return;
        } else
        {
            map.put("REQUEST_ID", requestId);
            return;
        }
    }

    public RelativeLayout getWidgetView()
    {
        WidgetItem widgetitem = (WidgetItem)items.get(0);
        if (widgetitem != null)
        {
            AnnouncementValue announcementvalue = (AnnouncementValue)widgetitem.getValue();
            ImageValue imagevalue = announcementvalue.getImageValue();
            String s = announcementvalue.getTitle();
            String s1 = announcementvalue.getSubTitle();
            RelativeLayout relativelayout = (RelativeLayout)((Activity)context).getLayoutInflater().inflate(0x7f030020, null);
            relativelayout.setOnClickListener(onClickListener);
            TextView textview = (TextView)relativelayout.findViewById(0x7f0a007e);
            TextView textview1 = (TextView)relativelayout.findViewById(0x7f0a007f);
            NetworkImageView networkimageview = (NetworkImageView)relativelayout.findViewById(0x7f0a007c);
            ImageView imageview = (ImageView)relativelayout.findViewById(0x7f0a007d);
            Action action = widgetitem.getAction();
            if (action == null || StringUtils.isNullOrEmpty(action.getScreenType()))
            {
                imageview.setVisibility(8);
            } else
            {
                addRequestIdToActionParamsExplicitly(action);
                relativelayout.setTag(action);
            }
            textview.setText(s);
            if (!StringUtils.isNullOrEmpty(s1))
            {
                textview1.setText(s1);
                textview1.setVisibility(0);
            } else
            {
                textview1.setVisibility(8);
            }
            if (theme != null && theme.equals(BaseWidget.WidgetTheme.dark))
            {
                textview.setTextColor(getResources().getColor(0x7f090070));
                textview1.setTextColor(getResources().getColor(0x7f090070));
                imageview.setImageResource(0x7f020148);
            } else
            {
                textview.setTextColor(getResources().getColor(0x7f090006));
                textview1.setTextColor(getResources().getColor(0x7f090006));
                imageview.setImageResource(0x7f02005d);
            }
            if (imagevalue != null)
            {
                networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
                String s2 = ImageUtils.getImageUrl(imagevalue.getImage());
                if (!StringUtils.isNullOrEmpty(s2))
                {
                    networkimageview.setImageUrl(s2, FlipkartApplication.getImageLoader());
                    return relativelayout;
                } else
                {
                    networkimageview.setVisibility(8);
                    return relativelayout;
                }
            } else
            {
                networkimageview.setVisibility(8);
                return relativelayout;
            }
        } else
        {
            return null;
        }
    }

    public void updateView(ArrayList arraylist, LayoutDetails layoutdetails, android.view.View.OnClickListener onclicklistener, String s)
    {
        items = arraylist;
        onClickListener = onclicklistener;
        requestId = s;
        layoutDetails = layoutdetails;
    }
}
