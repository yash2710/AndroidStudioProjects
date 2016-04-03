// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            PromotionsWidget

class context extends PagerAdapter
{

    private Context context;
    final PromotionsWidget this$0;

    public void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        ((ViewPager)viewgroup).removeView((LinearLayout)obj);
    }

    public int getCount()
    {
        return PromotionsWidget.access$100(PromotionsWidget.this).size();
    }

    public Object instantiateItem(ViewGroup viewgroup, int i)
    {
        WidgetItem widgetitem = (WidgetItem)PromotionsWidget.access$100(PromotionsWidget.this).get(i);
        FkLogger.debug("Test", (new StringBuilder("widgetitem is ")).append(widgetitem).toString());
        if (widgetitem != null)
        {
            ImageValue imagevalue = (ImageValue)widgetitem.getValue();
            FkLogger.debug("Test", (new StringBuilder("value is ")).append(imagevalue).toString());
            if (imagevalue != null)
            {
                com.flipkart.android.response.component.data.customvalues.Image image = imagevalue.getImage();
                FkLogger.debug("Test", (new StringBuilder("url is ")).append(image).toString());
                String s = ImageUtils.getImageUrl(image);
                FkLogger.debug("Test", (new StringBuilder("url is ")).append(s).toString());
                if (!StringUtils.isNullOrEmpty(s))
                {
                    LinearLayout linearlayout = new LinearLayout(context);
                    android.widget.  = new android.widget..ImagePagerAdapter.context(-2, PromotionsWidget.access$200(PromotionsWidget.this));
                    linearlayout.setBackgroundColor(0xff000000);
                    linearlayout.setLayoutParams();
                    PromotionsWidget.access$300(PromotionsWidget.this, widgetitem.getAction(), i);
                    PromotionsWidget.access$400(PromotionsWidget.this, widgetitem.getAction());
                    linearlayout.setTag(widgetitem.getAction());
                    linearlayout.setOnClickListener(onClickListener);
                    NetworkImageView networkimageview = new NetworkImageView(context);
                    networkimageview.setContentDescription((new StringBuilder("banner_")).append(i).toString());
                    android.widget.mvalues.Action action = new android.widget.tentDescription(-1, -1);
                    networkimageview.setScaleType(android.widget.leType);
                    networkimageview.setLayoutParams(action);
                    networkimageview.setImageUrl(s, FlipkartApplication.getImageLoader());
                    linearlayout.addView(networkimageview);
                    ((ViewPager)viewgroup).addView(linearlayout);
                    return linearlayout;
                }
            }
        }
        return null;
    }

    public boolean isViewFromObject(View view, Object obj)
    {
        return view == (LinearLayout)obj;
    }

    public (Context context1)
    {
        this$0 = PromotionsWidget.this;
        super();
        context = context1;
    }
}
