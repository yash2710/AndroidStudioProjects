// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class PromotionsWidget extends BaseWidget
{

    public static final String WIDGET_COMMON_NAME = "BANNER";
    private ArrayList items;
    String requestId;
    private int screenWidth;
    private CustomViewPager viewPager;
    private int widgetHeight;

    public PromotionsWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist, String s, 
            Activity activity, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        items = null;
        widgetHeight = 0;
        items = arraylist;
        requestId = s;
        if (arraylist != null && arraylist.size() > 0)
        {
            screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            widgetHeight = (int)(0.39000000000000001D * (double)screenWidth);
            screenDpi = ScreenMathUtils.getScreenDpi(context);
            setOrientation(1);
            setLayoutParams(setCustomLinearLayoutParams(widgetHeight));
            RelativeLayout relativelayout = new RelativeLayout(context);
            relativelayout.setLayoutParams(setCustomRelativeLayoutParams(widgetHeight, false));
            viewPager = new CustomViewPager(context);
            relativelayout.addView(viewPager);
            if (items.size() > 1)
            {
                CustomCirclePageIndicator customcirclepageindicator = new CustomCirclePageIndicator(this.context, viewPager);
                customcirclepageindicator.setBackgroundDrawable(new ColorDrawable(0x76000000));
                relativelayout.addView(customcirclepageindicator);
            }
            addView(relativelayout);
            return;
        } else
        {
            FkLogger.debug("Test", "Widget Item is null or empty");
            setTitleGone();
            return;
        }
    }

    private void addRequestIdToActionParamsExplicitly(Action action)
    {
        action.getParams().put("REQUEST_ID", requestId);
    }

    private android.widget.LinearLayout.LayoutParams setCustomLinearLayoutParams(int i)
    {
        return new android.widget.LinearLayout.LayoutParams(-1, i);
    }

    private android.widget.RelativeLayout.LayoutParams setCustomRelativeLayoutParams(int i, boolean flag)
    {
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, i);
        if (flag)
        {
            layoutparams.addRule(12);
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

    public void updateWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist)
    {
        super.updateWidget(context, layoutdetails, widgettheme, onclicklistener, widgetitem, arraylist);
        items = arraylist;
        if (viewPager != null)
        {
            viewPager.updateAdapter();
        }
    }







    private class CustomViewPager extends UninterceptableViewPager
    {
        private class ImagePagerAdapter extends PagerAdapter
        {

            private Context context;
            final PromotionsWidget this$0;

            public void destroyItem(ViewGroup viewgroup, int i, Object obj)
            {
                ((ViewPager)viewgroup).removeView((LinearLayout)obj);
            }

            public int getCount()
            {
                return items.size();
            }

            public Object instantiateItem(ViewGroup viewgroup, int i)
            {
                WidgetItem widgetitem = (WidgetItem)items.get(i);
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
                            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, widgetHeight);
                            linearlayout.setBackgroundColor(0xff000000);
                            linearlayout.setLayoutParams(layoutparams);
                            setHeading(widgetitem.getAction(), i);
                            addRequestIdToActionParamsExplicitly(widgetitem.getAction());
                            linearlayout.setTag(widgetitem.getAction());
                            linearlayout.setOnClickListener(onClickListener);
                            NetworkImageView networkimageview = new NetworkImageView(context);
                            networkimageview.setContentDescription((new StringBuilder("banner_")).append(i).toString());
                            android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-1, -1);
                            networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
                            networkimageview.setLayoutParams(layoutparams1);
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

            public ImagePagerAdapter(Context context1)
            {
                this$0 = PromotionsWidget.this;
                super();
                context = context1;
            }
        }


        ImagePagerAdapter adapter;
        final PromotionsWidget this$0;

        public void updateAdapter()
        {
            adapter.notifyDataSetChanged();
        }

        public CustomViewPager(Context context)
        {
            this$0 = PromotionsWidget.this;
            super(context);
            setLayoutParams(setCustomLinearLayoutParams(widgetHeight));
            adapter = new ImagePagerAdapter(context);
            setAdapter(adapter);
        }
    }


    private class CustomCirclePageIndicator extends CirclePageIndicator
    {

        final PromotionsWidget this$0;

        public CustomCirclePageIndicator(Context context, ViewPager viewpager)
        {
            this$0 = PromotionsWidget.this;
            super(context);
            setLayoutParams(setCustomRelativeLayoutParams(ScreenMathUtils.dpToPx(15, context), true));
            setPadding(0, ScreenMathUtils.dpToPx(4, context), 0, ScreenMathUtils.dpToPx(4, context));
            setViewPager(viewpager);
        }
    }

}
