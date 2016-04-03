// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.content.Context;
import android.content.res.Resources;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.customviews.CustomRobotoCondensedBoldTextView;
import com.flipkart.android.customviews.PmuProductLayout;

public class ViewMoreWidget extends PmuProductLayout
{

    private Context context;
    private ImageLoader imageLoader;

    public ViewMoreWidget(Context context1, ImageLoader imageloader)
    {
        super(context1, imageloader, "horizontal", false);
        context = context1;
        imageLoader = imageloader;
        buildView();
    }

    private void buildView()
    {
        setPadding(0, (int)(0.29999999999999999D * (double)itemHeight), 0, 0);
        ImageView imageview = new ImageView(context);
        imageview.setImageResource(0x7f02017e);
        linearParentLayout.addView(imageview);
        CustomRobotoCondensedBoldTextView customrobotocondensedboldtextview = new CustomRobotoCondensedBoldTextView(context);
        customrobotocondensedboldtextview.setText("VIEW MORE\nOFFERS");
        customrobotocondensedboldtextview.setGravity(17);
        customrobotocondensedboldtextview.setTextColor(getResources().getColor(0x7f090053));
        customrobotocondensedboldtextview.setTextSize(16F);
        linearParentLayout.addView(customrobotocondensedboldtextview);
        ImageView imageview1 = new ImageView(context);
        imageview1.setImageResource(0x7f02017f);
        linearParentLayout.addView(imageview1);
    }
}
