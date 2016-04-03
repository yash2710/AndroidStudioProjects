// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.response.component.data.customvalues.SponsoredValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class SponsoredWidget extends BaseWidget
{

    public static final String WIDGET_COMMON_NAME = "SPONSORED";
    Double defaultHeightFactor;

    public SponsoredWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, ArrayList arraylist, Activity activity, int i)
    {
        int j;
        super(context, layoutdetails, widgettheme, onclicklistener, null, activity, i);
        defaultHeightFactor = Double.valueOf(0.40000000000000002D);
        j = 0;
        if (arraylist == null) goto _L2; else goto _L1
_L1:
        int k;
        k = arraylist.size();
        j = 0;
        if (k <= 0) goto _L2; else goto _L3
_L3:
        int l = 0;
_L4:
        int i1;
        if (l >= arraylist.size())
        {
            break; /* Loop/switch isn't completed */
        }
        WidgetItem widgetitem = (WidgetItem)arraylist.get(l);
        if (widgetitem == null)
        {
            break MISSING_BLOCK_LABEL_245;
        }
        SponsoredValue sponsoredvalue = (SponsoredValue)widgetitem.getValue();
        if (sponsoredvalue == null || sponsoredvalue.getImage() == null)
        {
            break MISSING_BLOCK_LABEL_245;
        }
        com.flipkart.android.response.component.data.customvalues.Image image = sponsoredvalue.getImage().getImage();
        com.flipkart.android.response.component.data.customvalues.Action action = widgetitem.getAction();
        String s = ImageUtils.getImageUrl(image);
        if (StringUtils.isNullOrEmpty(s))
        {
            break MISSING_BLOCK_LABEL_245;
        }
        context.getResources().getDisplayMetrics().widthPixels;
        defaultHeightFactor.doubleValue();
        NetworkImageView networkimageview = new NetworkImageView(context);
        networkimageview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
        networkimageview.setImageUrl(s, FlipkartApplication.getImageLoader());
        networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
        addView(networkimageview);
        if (action != null)
        {
            networkimageview.setTag(action);
            networkimageview.setOnClickListener(onclicklistener);
        }
        i1 = j + 1;
_L5:
        l++;
        j = i1;
        if (true) goto _L4; else goto _L2
_L2:
        if (j == 0)
        {
            setTitleGone();
        }
        return;
        i1 = j;
          goto _L5
    }
}
