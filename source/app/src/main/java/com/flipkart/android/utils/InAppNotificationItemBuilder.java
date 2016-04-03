// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.fragments.model.InAppNotificationModel;
import com.flipkart.android.response.customwidgetitemvalue.Action;
import java.util.Map;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils

public class InAppNotificationItemBuilder
{

    public InAppNotificationItemBuilder()
    {
    }

    public static View buildInAppNotificationItem(InAppNotificationModel inappnotificationmodel, View view, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener, Context context)
    {
        RelativeLayout relativelayout;
        if (inappnotificationmodel == null)
        {
            return new View(context);
        }
        LayoutInflater layoutinflater = LayoutInflater.from(context);
        if (inappnotificationmodel.isShareable() && !StringUtils.isNullOrEmpty(inappnotificationmodel.getShareUrl()))
        {
            relativelayout = (RelativeLayout)layoutinflater.inflate(0x7f030055, null);
        } else
        {
            relativelayout = (RelativeLayout)layoutinflater.inflate(0x7f030054, null);
        }
        if (relativelayout == null)
        {
            return new View(context);
        }
        TextView textview;
        TextView textview1;
        ImageView imageview;
        TextView textview2;
        NetworkImageView networkimageview;
        TextView textview3;
        View view1;
        String s;
        textview = (TextView)relativelayout.findViewById(0x7f0a0111);
        textview1 = (TextView)relativelayout.findViewById(0x7f0a0112);
        imageview = (ImageView)relativelayout.findViewById(0x7f0a011a);
        textview2 = (TextView)relativelayout.findViewById(0x7f0a0118);
        networkimageview = (NetworkImageView)relativelayout.findViewById(0x7f0a0110);
        textview3 = (TextView)relativelayout.findViewById(0x7f0a0119);
        view1 = relativelayout.findViewById(0x7f0a010e);
        s = inappnotificationmodel.getImageUrl();
        if (StringUtils.isNullOrEmpty(s)) goto _L2; else goto _L1
_L1:
        networkimageview.setImageUrl(s, imageloader);
_L14:
        textview.setText(inappnotificationmodel.getTitle());
        if (!inappnotificationmodel.getNotificationType().equals("UPGRADE_APP")) goto _L4; else goto _L3
_L3:
        textview2.setVisibility(8);
        textview1.setText(Html.fromHtml(inappnotificationmodel.getSubTitle()));
_L8:
        if (inappnotificationmodel.isShareable() && !StringUtils.isNullOrEmpty(inappnotificationmodel.getShareUrl()))
        {
            TextView textview4 = (TextView)relativelayout.findViewById(0x7f0a011e);
            textview4.setOnClickListener(onclicklistener);
            textview4.setTag((new StringBuilder("share:")).append(inappnotificationmodel.getNotificationType()).append(":").append(inappnotificationmodel.getShareUrl()).toString());
        }
        if (!inappnotificationmodel.isNew()) goto _L6; else goto _L5
_L5:
        textview3.setVisibility(0);
        view1.setVisibility(0);
_L9:
        String s1 = inappnotificationmodel.getNotificationType();
        if (!StringUtils.isNullOrEmpty(s1))
        {
            if (!s1.equals("TARGETED_OFFERS") && !s1.equals("SANTA_OFFERS"))
            {
                break MISSING_BLOCK_LABEL_494;
            }
            imageview.setVisibility(0);
        }
_L10:
        Action action = inappnotificationmodel.getAction();
        if (action == null)
        {
            break MISSING_BLOCK_LABEL_430;
        }
        Map map = action.getParams();
        if (map == null)
        {
            break MISSING_BLOCK_LABEL_423;
        }
        map.put("notificationType", inappnotificationmodel.getNotificationType());
        map.put("notificationId", inappnotificationmodel.getNotificationId());
        map.put("notificationTimeStamp", Long.valueOf(inappnotificationmodel.getTimeStamp()));
        action.setParams(map);
        relativelayout.setTag(action);
        break MISSING_BLOCK_LABEL_504;
_L2:
        try
        {
            networkimageview.setDefaultImageResId(0x7f020189);
            continue; /* Loop/switch isn't completed */
        }
        catch (Exception exception) { }
          goto _L7
_L4:
        textview1.setText(inappnotificationmodel.getSubTitle());
        textview2.setVisibility(0);
        textview2.setText(inappnotificationmodel.getTime());
          goto _L8
_L6:
        textview3.setVisibility(8);
        view1.setVisibility(8);
          goto _L9
        imageview.setVisibility(8);
          goto _L10
_L12:
        return relativelayout;
_L7:
        if (true) goto _L12; else goto _L11
_L11:
        if (true) goto _L14; else goto _L13
_L13:
    }

    public static View buildRefreshingListitem(View view, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener, Context context, boolean flag)
    {
        if (!flag)
        {
            return new View(context);
        } else
        {
            return (RelativeLayout)LayoutInflater.from(context).inflate(0x7f03005d, null);
        }
    }
}
