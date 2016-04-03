// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.customvalues.OMUImageValue;
import com.flipkart.android.response.component.data.customvalues.OMUValue;
import com.flipkart.android.utils.ScreenMathUtils;

public class BucketWidget extends LinearLayout
{

    private Context context;
    public int itemHeight;
    public int itemWidth;
    private OMUValue omuValue;
    public int screenDpi;
    private String shareUrl;

    public BucketWidget(Context context1)
    {
        super(context1);
        itemWidth = 0;
        itemHeight = 0;
        screenDpi = -1;
    }

    public BucketWidget(Context context1, OMUValue omuvalue)
    {
        super(context1);
        itemWidth = 0;
        itemHeight = 0;
        screenDpi = -1;
        context = context1;
        omuValue = omuvalue;
        shareUrl = omuvalue.getShareUrl();
        calculateWidthAndHeight();
        setBackgroundColor(getResources().getColor(0x7f090070));
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        setOrientation(1);
        addView(getBucketLayout());
    }

    private RelativeLayout getBucketLayout()
    {
        RelativeLayout relativelayout = (RelativeLayout)LayoutInflater.from(context).inflate(0x7f030067, null);
        NetworkImageView networkimageview = (NetworkImageView)relativelayout.findViewById(0x7f0a0142);
        OMUImageValue omuimagevalue = omuValue.getPrimaryImage();
        if (omuimagevalue != null)
        {
            String s = omuimagevalue.fetchUrl(screenDpi);
            if (s != null)
            {
                networkimageview.setImageUrl(s, FlipkartApplication.getImageLoader());
            }
            networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
        }
        return relativelayout;
    }

    public void calculateWidthAndHeight()
    {
        itemWidth = (int)((double)(2 * (getResources().getDisplayMetrics().widthPixels - (5 * ScreenMathUtils.dpToPx(1, context) + 2 * ScreenMathUtils.dpToPx(2, context)))) / 2.5D);
        itemHeight = (int)(1.6000000000000001D * (double)itemWidth) / 2;
        screenDpi = ScreenMathUtils.getScreenDpi(context);
    }
}
