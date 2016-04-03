// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.PMUValue;
import com.flipkart.android.response.productInfo.ProductImage;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import net.simonvt.menudrawer.ColorDrawable;

// Referenced classes of package com.flipkart.android.customviews:
//            CustomRobotoRegularTextView

public class PmuProductLayout extends RelativeLayout
{

    public static final int PARENT_ID = 1;
    public Context context;
    public ImageLoader customImageloader;
    public int itemHeight;
    public int itemWidth;
    public LinearLayout linearParentLayout;
    public int screenDpi;

    public PmuProductLayout(Context context1, ImageLoader imageloader, String s, boolean flag)
    {
        super(context1);
        itemWidth = 0;
        itemHeight = 0;
        screenDpi = -1;
        context = context1;
        customImageloader = imageloader;
        initializeParams(flag, s);
    }

    public PmuProductLayout(Context context1, WidgetItem widgetitem, String s, ImageLoader imageloader)
    {
        super(context1);
        itemWidth = 0;
        itemHeight = 0;
        screenDpi = -1;
        customImageloader = imageloader;
        context = context1;
        initializeParams(true, null);
        drawProductLayout(widgetitem);
    }

    private void calculateWidthAndHeight(String s)
    {
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        int i = displaymetrics.widthPixels;
        int j = displaymetrics.heightPixels;
        if (StringUtils.isNullOrEmpty(s))
        {
            itemWidth = (int)((double)(i - 2 * ScreenMathUtils.dpToPx(2, context)) / 2.5D);
            itemHeight = (int)(1.4299999999999999D * (double)itemWidth);
            return;
        }
        if (s.equalsIgnoreCase("horizontal"))
        {
            itemWidth = (int)((double)(i - 2 * ScreenMathUtils.dpToPx(2, context)) / 2.5D);
            itemHeight = (int)(1.8D * (double)itemWidth);
            return;
        } else
        {
            itemWidth = (i - ScreenMathUtils.dpToPx(1, context)) / 2;
            itemHeight = (int)(0.5D * (double)j);
            return;
        }
    }

    private void drawProductLayout(WidgetItem widgetitem)
    {
        RelativeLayout relativelayout = new RelativeLayout(context);
        relativelayout.setLayoutParams(new android.widget.RelativeLayout.LayoutParams((int)(0.90000000000000002D * (double)itemWidth), (int)(0.90000000000000002D * (double)itemWidth)));
        PMUValue pmuvalue = (PMUValue)widgetitem.getValue();
        if (pmuvalue != null)
        {
            ProductInfo productinfo = pmuvalue.getProduct();
            String s = (new StringBuilder()).append(productinfo.getSellingPrice()).toString();
            String s1 = productinfo.fetchBestImageUrl("ProductList page");
            if (StringUtils.isNullOrEmpty(s1))
            {
                ProductImage productimage = ImageUtils.fetchBestImage((int)(0.90000000000000002D * (double)itemWidth), (int)(0.90000000000000002D * (double)itemWidth), productinfo.getPrimaryImageId(), productinfo.getProductMultipleImage());
                if (productimage != null)
                {
                    s1 = productimage.getUrl();
                }
                if (StringUtils.isNullOrEmpty(s1))
                {
                    s1 = productinfo.getProductErrorImage();
                }
            }
            relativelayout.addView(setProductImageView(context, s1));
            boolean flag;
            if (StringUtils.isNullOrEmpty(productinfo.getProductOffers()))
            {
                flag = false;
            } else
            {
                flag = true;
            }
            if (flag)
            {
                relativelayout.addView(setOfferImageView(context));
            }
            linearParentLayout.addView(relativelayout);
            linearParentLayout.addView(setTextView(productinfo.getMainTitle(), "#313131", false, context, false, 1.0F, true, false, false, 12));
            if (s != null && !s.equals("0"))
            {
                linearParentLayout.addView(setTextView((new StringBuilder("Rs. ")).append(s).toString(), "#c12929", false, context, false, 1.0F, false, false, false, 14));
            }
        }
    }

    private void initializeParams(boolean flag, String s)
    {
        calculateWidthAndHeight(s);
        int i = 0;
        if (flag)
        {
            i = ScreenMathUtils.dpToPx(10, context);
        }
        setBackgroundDrawable(new ColorDrawable(-1));
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(itemWidth, itemHeight);
        setLayoutParams(layoutparams);
        linearParentLayout = new LinearLayout(context);
        linearParentLayout.setPadding(i, i, i, i);
        linearParentLayout.setId(1);
        linearParentLayout.setOrientation(1);
        linearParentLayout.setLayoutParams(layoutparams);
        addView(linearParentLayout);
        screenDpi = ScreenMathUtils.getScreenDpi(context);
    }

    private ImageView setOfferImageView(Context context1)
    {
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        layoutparams.addRule(12);
        ImageView imageview = new ImageView(context1);
        imageview.setLayoutParams(layoutparams);
        imageview.setImageResource(0x7f02011c);
        return imageview;
    }

    private ImageView setProductImageView(Context context1, String s)
    {
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(itemWidth - 2 * ScreenMathUtils.dpToPx(10, context1), itemWidth);
        NetworkImageView networkimageview = new NetworkImageView(context1);
        layoutparams.setMargins(0, 0, ImageUtils.dpToPx(5), 0);
        networkimageview.setLayoutParams(layoutparams);
        networkimageview.setBackgroundColor(0);
        if (s != null)
        {
            networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
            networkimageview.setImageUrl(s, customImageloader);
            return networkimageview;
        } else
        {
            networkimageview.setScaleType(android.widget.ImageView.ScaleType.CENTER);
            networkimageview.setImageResource(0x7f0200d5);
            return networkimageview;
        }
    }

    private TextView setTextView(String s, String s1, boolean flag, Context context1, boolean flag1, float f, boolean flag2, 
            boolean flag3, boolean flag4, int i)
    {
        android.widget.LinearLayout.LayoutParams layoutparams;
        CustomRobotoRegularTextView customrobotoregulartextview;
        if (flag3)
        {
            layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -2, f);
        } else
        {
            layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -2);
        }
        customrobotoregulartextview = new CustomRobotoRegularTextView(context1, null);
        if (flag2)
        {
            layoutparams.setMargins(ImageUtils.dpToPx(0), ImageUtils.dpToPx(8), ImageUtils.dpToPx(5), ImageUtils.dpToPx(0));
        }
        customrobotoregulartextview.setLayoutParams(layoutparams);
        if (flag4)
        {
            customrobotoregulartextview.setText(s.toUpperCase());
        } else
        {
            customrobotoregulartextview.setText(s);
        }
        if (i != 0)
        {
            customrobotoregulartextview.setTextSize(i);
        }
        customrobotoregulartextview.setSingleLine(true);
        customrobotoregulartextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
        if (flag)
        {
            customrobotoregulartextview.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (flag1)
        {
            customrobotoregulartextview.setPaintFlags(0x10 | customrobotoregulartextview.getPaintFlags());
        }
        if (s1 != null)
        {
            customrobotoregulartextview.setTextColor(Color.parseColor(s1));
        }
        return customrobotoregulartextview;
    }
}
