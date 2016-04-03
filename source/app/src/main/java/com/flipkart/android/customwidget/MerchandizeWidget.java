// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.customviews.CustomRobotoLightTextView;
import com.flipkart.android.customviews.CustomRobotoRegularTextView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.SMUValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            ScrollWidget

public class MerchandizeWidget extends ScrollWidget
{

    public static final int MW_STORE_FRONT_BUTTON_SCROLL_VIEW = 3;
    public static final String WIDGET_COMMON_NAME_SMU6 = "SMU";
    private int colorPallete[] = {
        0xffe64a55, 0xff5fa8db, 0xffa1c927
    };
    public int itemHeight;
    public int itemWidth;
    private String requestId;

    public MerchandizeWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist, String s, 
            Activity activity, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, true, true, activity, i);
        itemWidth = 0;
        itemHeight = 0;
        requestId = s;
        screenDpi = ScreenMathUtils.getScreenDpi(context);
        if (arraylist != null && arraylist.size() > 0)
        {
            calculateWidthAndHeight();
            createAndAddItemsForSMU6(arraylist);
            return;
        } else
        {
            setTitleGone();
            setVisibility(8);
            return;
        }
    }

    private void addRequestIdToActionParamsExplicitly(Action action)
    {
        action.getParams().put("REQUEST_ID", requestId);
    }

    private void calculateWidthAndHeight()
    {
        itemWidth = (int)((double)(getResources().getDisplayMetrics().widthPixels - (5 * ScreenMathUtils.dpToPx(1, context) + 2 * ScreenMathUtils.dpToPx(2, context))) / 2.5D);
        itemHeight = (int)(1.4299999999999999D * (double)itemWidth);
    }

    private void createAndAddItemsForSMU6(ArrayList arraylist)
    {
        int i = 0;
        boolean flag;
        boolean flag1;
        for (flag = false; i < arraylist.size(); flag = flag1)
        {
            WidgetItem widgetitem = (WidgetItem)arraylist.get(i);
            if (widgetitem != null)
            {
                SMUValue smuvalue = (SMUValue)widgetitem.getValue();
                if (smuvalue != null)
                {
                    FkLogger.debug("MerchandizeWidget", (new StringBuilder("image is for merch ")).append(smuvalue.getImage()).toString());
                    boolean flag2 = StringUtils.isNullOrEmpty(smuvalue.getImage());
                    String s = null;
                    if (!flag2)
                    {
                        s = ImageUtils.fetchBestImage(smuvalue.getImage(), ScreenMathUtils.dpToPx(133, context), ScreenMathUtils.dpToPx(90, context));
                        FkLogger.debug("MerchandizeWidget", (new StringBuilder("url for merch ")).append(s).toString());
                    }
                    String s1 = smuvalue.getTextLinePrimary();
                    String s2 = smuvalue.getTextLineSecondary();
                    String s3 = smuvalue.getTextLineTernary();
                    ProductLayout productlayout = new ProductLayout(context, null, i, s, s1, s2, s3);
                    productlayout.setBackgroundColor(-1);
                    setHeading(widgetitem.getAction(), s1, s2, s3, i);
                    addRequestIdToActionParamsExplicitly(widgetitem.getAction());
                    productlayout.setTag(widgetitem.getAction());
                    productlayout.setOnClickListener(onClickListener);
                    View view = new View(context);
                    scrollLayout.addView(productlayout);
                    flag = true;
                    view.setBackgroundColor(0xffdcdcdc);
                    scrollLayout.addView(view, new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(1, context), -1));
                }
            }
            flag1 = flag;
            i++;
        }

        if (!flag)
        {
            setTitleGone();
            setVisibility(8);
        }
    }

    private void setHeading(Action action, String s, String s1, String s2, int i)
    {
        if (action != null)
        {
            Map map = action.getParams();
            if (map != null)
            {
                if (!StringUtils.isNullOrEmpty((new StringBuilder()).append(s).append(s1).append(s2).toString()))
                {
                    map.put("heading", (new StringBuilder()).append(s).append(s1).append(s2).toString());
                }
                map.put("position", Integer.valueOf(i + 1));
            }
            action.setParams(map);
        }
    }

    private ImageView setImageView(String s, int i, Context context, boolean flag)
    {
        NetworkImageView networkimageview = new NetworkImageView(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, 0, i);
        layoutparams.gravity = 17;
        networkimageview.setLayoutParams(layoutparams);
        if (flag)
        {
            networkimageview.setVisibility(0);
            networkimageview.setImageUrl(s, FlipkartApplication.getImageLoader());
            return networkimageview;
        } else
        {
            networkimageview.setBackgroundDrawable(new ColorDrawable(0xaa000000));
            return networkimageview;
        }
    }

    private android.widget.LinearLayout.LayoutParams setLinearLayoutParams()
    {
        return new android.widget.LinearLayout.LayoutParams(itemWidth, itemHeight);
    }

    private TextView setTextView(String s, String s1, int i, int j, int k, int l, Context context, 
            boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4)
    {
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        if (flag2)
        {
            layoutparams.setMargins(0, 0, 0, ScreenMathUtils.dpToPx(10, context));
        }
        if (flag3)
        {
            layoutparams.setMargins(0, ScreenMathUtils.dpToPx(10, context), 0, 0);
        }
        Object obj;
        if (flag4)
        {
            obj = new CustomRobotoLightTextView(context, null);
        } else
        {
            obj = new CustomRobotoRegularTextView(context, null);
        }
        if (flag)
        {
            ((TextView) (obj)).setText(s.toUpperCase());
        } else
        {
            ((TextView) (obj)).setText(s);
        }
        ((TextView) (obj)).setTextSize(2, j);
        ((TextView) (obj)).setLayoutParams(layoutparams);
        ((TextView) (obj)).setTextColor(l);
        ((TextView) (obj)).setSingleLine(true);
        if (i == 1)
        {
            ((TextView) (obj)).setGravity(49);
            ((TextView) (obj)).setIncludeFontPadding(false);
            ((TextView) (obj)).setPadding(0, 0, 0, 0);
        } else
        if (i == 3)
        {
            ((TextView) (obj)).setPadding(ScreenMathUtils.dpToPx(5, context), 0, 0, 0);
            ((TextView) (obj)).setGravity(3);
        } else
        {
            ((TextView) (obj)).setPadding(0, 0, 0, 0);
            ((TextView) (obj)).setGravity(81);
        }
        ((TextView) (obj)).setEllipsize(android.text.TextUtils.TruncateAt.END);
        if (!flag1)
        {
            ((TextView) (obj)).setVisibility(8);
        }
        return ((TextView) (obj));
    }





    private class ProductLayout extends LinearLayout
    {

        final MerchandizeWidget this$0;

        public ProductLayout(Context context, AttributeSet attributeset, int i, String s, String s1, String s2, 
                String s3)
        {
            this$0 = MerchandizeWidget.this;
            super(context);
            setBackgroundResource(0x7f0200f6);
            setLayoutParams(setLinearLayoutParams());
            setOrientation(1);
            setContentDescription((new StringBuilder("product_")).append(i).toString());
            int j = ScreenMathUtils.dpToPx(10, context);
            setPadding(j, j, j, j);
            if (s1 != null && s1.length() > 0)
            {
                addView(setTextView(s1, "sans-serif-light", 0, 15, 34, 0xff484848, context, true, true, false, false, true));
            } else
            {
                addView(setTextView("", "sans-serif-light", 0, 15, 34, 0xff484848, context, true, true, false, false, true));
            }
            if (s2 != null && s2.length() > 0)
            {
                addView(setTextView(s2, "sans-serif-light", 0, 17, 38, colorPallete[i % 3], context, false, true, true, false, true));
            } else
            {
                addView(setTextView("", "sans-serif-light", 0, 17, 38, colorPallete[i % 3], context, false, true, true, false, true));
            }
            addView(setImageView(s, 155, context, true));
            if (s3 != null && s3.length() > 0)
            {
                addView(setTextView(s3, "sans-serif-light", 0, 12, 26, 0xff696969, context, true, true, false, true, true));
                return;
            } else
            {
                addView(setTextView("", "sans-serif-light", 12, 0, 26, 0xff696969, context, true, true, false, true, true));
                return;
            }
        }
    }

}
