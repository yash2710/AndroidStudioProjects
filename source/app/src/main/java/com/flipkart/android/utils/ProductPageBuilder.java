// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.DB.WishListDao;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.AddCartLocation;
import com.flipkart.android.cart.CartHandler;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.CustomRobotoLightTextView;
import com.flipkart.android.customviews.CustomRobotoMediumTextView;
import com.flipkart.android.customviews.CustomRobotoRegularTextView;
import com.flipkart.android.customviews.NewEditText;
import com.flipkart.android.customviews.OfferExpandablePanel;
import com.flipkart.android.customviews.ViewPortScrollView;
import com.flipkart.android.customwidget.CallUsWidget;
import com.flipkart.android.customwidget.InstallCareWidget;
import com.flipkart.android.customwidget.RecommendationsWidget;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.fragments.model.PromiseWidgetModel;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.customvalues.Image;
import com.flipkart.android.response.productInfo.ExtraMessage;
import com.flipkart.android.response.productInfo.InstallCareWidgetData;
import com.flipkart.android.response.productInfo.MarketPlaceSeller;
import com.flipkart.android.response.productInfo.PriceType;
import com.flipkart.android.response.productInfo.PriceWidget;
import com.flipkart.android.response.productInfo.ProductAvailabilityDetails;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.productInfo.SantaOffers;
import com.flipkart.android.response.ugc.UGCRating;
import com.flipkart.android.response.ugc.UGCRatingObj;
import com.flipkart.android.utils.browser.HtmlTagHandler;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.utils:
//            ScreenMathUtils, StringUtils, FbfUtils, PageTypeUtils, 
//            WishListUtils, AppConfigUtils, ImageUtils, n, 
//            p, FkProductListContext, o, OfferBackgroundUtils, 
//            m, l, MiscUtils

public class ProductPageBuilder
{

    public ProductPageBuilder()
    {
    }

    private static int a(ImageLoader imageloader, ProductPageModel productpagemodel, LinearLayout linearlayout, android.view.View.OnClickListener onclicklistener, int i)
    {
        LinearLayout linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a01e4);
        if (productpagemodel.isEbook() || !productpagemodel.isCheckoutEnabled()) goto _L2; else goto _L1
_L1:
        if (linearlayout1 != null) goto _L4; else goto _L3
_L3:
        linearlayout1 = (LinearLayout)((LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater")).inflate(0x7f030098, null);
        if (linearlayout.getChildCount() < i + 1) goto _L6; else goto _L5
_L5:
        linearlayout.addView(linearlayout1, i);
_L19:
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout1.getLayoutParams();
        layoutparams.topMargin = ScreenMathUtils.dpToPx(8, linearlayout.getContext());
        linearlayout1.setLayoutParams(layoutparams);
_L4:
        LinearLayout linearlayout2 = linearlayout1;
        LinearLayout linearlayout3 = (LinearLayout)linearlayout2.findViewById(0x7f0a01a2);
        if (StringUtils.isNullOrEmpty(productpagemodel.getFbf()) || !productpagemodel.getFbf().equalsIgnoreCase("FBF")) goto _L8; else goto _L7
_L7:
        FbfUtils.showFbf(linearlayout3, imageloader, onclicklistener);
_L11:
        View view;
        view = linearlayout2.findViewById(0x7f0a01e5);
        int j = ScreenMathUtils.dpToPx(1, linearlayout.getContext());
        view.setPadding(j, j, j, j);
        view.setBackgroundResource(0x7f0200f6);
        if (productpagemodel.isSellerInfoAvailable() && productpagemodel.getSellerName() != null && productpagemodel.getSellerName().length() != 0) goto _L10; else goto _L9
_L9:
        linearlayout2.setVisibility(8);
        view.setVisibility(8);
        return i + 1;
_L6:
        linearlayout.addView(linearlayout1);
        continue; /* Loop/switch isn't completed */
_L8:
        linearlayout3.setVisibility(8);
          goto _L11
_L10:
        linearlayout2.setVisibility(0);
        view.setVisibility(0);
        if (productpagemodel.getSellerName() != null && !productpagemodel.getSellerName().equalsIgnoreCase(""))
        {
            break MISSING_BLOCK_LABEL_292;
        }
        view.setVisibility(8);
        return i + 1;
        TextView textview1;
        long l1;
        view.setVisibility(0);
        view.setOnClickListener(onclicklistener);
        TextView textview = (TextView)view.findViewById(0x7f0a01e6);
        textview.setContentDescription("product_page_seller_name_text");
        textview.setText(productpagemodel.getSellerName());
        ((RatingBar)view.findViewById(0x7f0a01e7)).setRating(productpagemodel.getSellerRating());
        textview1 = (TextView)view.findViewById(0x7f0a01e8);
        l1 = productpagemodel.getSellerRatingCount();
        if (l1 > 1L) goto _L13; else goto _L12
_L12:
        textview1.setText((new StringBuilder()).append(l1).append(" Rating").toString());
_L14:
        TextView textview2;
        textview2 = (TextView)view.findViewById(0x7f0a01e9);
        if (!productpagemodel.isSellerReturnPolicyShown())
        {
            break MISSING_BLOCK_LABEL_489;
        }
        textview2.setVisibility(0);
        textview2.setText(productpagemodel.getSellerReturnPolicy());
_L15:
        View view1;
        view1 = linearlayout2.findViewById(0x7f0a01ea);
        if (productpagemodel.isMoreSellers())
        {
            break MISSING_BLOCK_LABEL_499;
        }
        view1.setVisibility(8);
        return i + 1;
_L13:
        textview1.setText((new StringBuilder()).append(l1).append(" Ratings").toString());
          goto _L14
        textview2.setVisibility(8);
          goto _L15
        try
        {
            int k = ScreenMathUtils.dpToPx(1, linearlayout.getContext());
            view1.setPadding(k, k, k, k);
            view1.setBackgroundResource(0x7f0200f6);
            view1.setVisibility(0);
            view1.setOnClickListener(onclicklistener);
            ((TextView)view1.findViewById(0x7f0a01eb)).setText((new StringBuilder()).append(productpagemodel.getMoreSellerList().size()).append(" Sellers from").toString());
            ((TextView)view1.findViewById(0x7f0a01ec)).setText((new StringBuilder("Rs.")).append(productpagemodel.getMoreSellerStartingPrice()).toString());
        }
        catch (Exception exception) { }
          goto _L16
_L2:
        if (linearlayout1 == null) goto _L16; else goto _L17
_L17:
        linearlayout1.setVisibility(8);
_L16:
        return i + 1;
        if (true) goto _L19; else goto _L18
_L18:
    }

    private static int a(ProductPageModel productpagemodel, LinearLayout linearlayout, int i)
    {
        LinearLayout linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a0206);
        if (linearlayout1 != null) goto _L2; else goto _L1
_L1:
        linearlayout1 = (LinearLayout)((LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater")).inflate(0x7f0300a9, null);
        if (linearlayout.getChildCount() < i + 1)
        {
            break MISSING_BLOCK_LABEL_104;
        }
        linearlayout.addView(linearlayout1, i);
_L3:
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout1.getLayoutParams();
        layoutparams.topMargin = ScreenMathUtils.dpToPx(10, linearlayout.getContext());
        linearlayout1.setLayoutParams(layoutparams);
_L2:
        if (!productpagemodel.isWarrantyAvailable())
        {
            linearlayout1.setVisibility(8);
            break MISSING_BLOCK_LABEL_136;
        }
        break MISSING_BLOCK_LABEL_113;
        linearlayout.addView(linearlayout1);
          goto _L3
        try
        {
            linearlayout1.setVisibility(0);
            ((TextView)linearlayout1.findViewById(0x7f0a0207)).setText(productpagemodel.getWarrantyText());
        }
        catch (Exception exception) { }
        return i + 1;
    }

    private static int a(ProductPageModel productpagemodel, LinearLayout linearlayout, int i, android.view.View.OnClickListener onclicklistener)
    {
        LinearLayout linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a0186);
        if (linearlayout1 != null) goto _L2; else goto _L1
_L1:
        linearlayout1 = (LinearLayout)((LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater")).inflate(0x7f030082, null);
        if (linearlayout.getChildCount() < i + 1) goto _L4; else goto _L3
_L3:
        linearlayout.addView(linearlayout1, i);
        LinearLayout linearlayout2 = linearlayout1;
_L12:
        linearlayout2.removeAllViews();
        if (productpagemodel.isExtraMessageAvailable()) goto _L6; else goto _L5
_L5:
        linearlayout2.setVisibility(8);
          goto _L7
_L4:
        linearlayout.addView(linearlayout1);
          goto _L2
_L6:
        ArrayList arraylist;
        linearlayout2.setVisibility(0);
        arraylist = productpagemodel.getExtraMessages();
        if (arraylist == null) goto _L7; else goto _L8
_L8:
        Iterator iterator = arraylist.iterator();
_L11:
        if (!iterator.hasNext()) goto _L7; else goto _L9
_L9:
        ExtraMessage extramessage = (ExtraMessage)iterator.next();
        if (extramessage == null) goto _L11; else goto _L10
_L10:
        TextView textview1;
label0:
        {
            if (!StringUtils.isNullOrEmpty(extramessage.getTitle()))
            {
                break MISSING_BLOCK_LABEL_283;
            }
            textview1 = (TextView)((LayoutInflater)linearlayout2.getContext().getSystemService("layout_inflater")).inflate(0x7f030080, null);
            linearlayout2.addView(textview1);
            android.widget.LinearLayout.LayoutParams layoutparams1 = (android.widget.LinearLayout.LayoutParams)textview1.getLayoutParams();
            layoutparams1.topMargin = ScreenMathUtils.dpToPx(10, linearlayout2.getContext());
            textview1.setLayoutParams(layoutparams1);
            textview1.setVisibility(0);
            textview1.setText(extramessage.getMessage());
            if (extramessage.getAction() == null)
            {
                break label0;
            }
            textview1.setOnClickListener(onclicklistener);
            textview1.setTag(extramessage.getAction());
        }
          goto _L11
        textview1.setCompoundDrawables(null, null, null, null);
        textview1.setBackgroundColor(Color.parseColor("#ffffff"));
          goto _L11
        LinearLayout linearlayout3 = (LinearLayout)((LayoutInflater)linearlayout2.getContext().getSystemService("layout_inflater")).inflate(0x7f030081, null);
        linearlayout2.addView(linearlayout3);
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout3.getLayoutParams();
        layoutparams.topMargin = ScreenMathUtils.dpToPx(10, linearlayout2.getContext());
        linearlayout3.setLayoutParams(layoutparams);
        linearlayout3.setVisibility(0);
        TextView textview = (TextView)linearlayout3.findViewById(0x7f0a0184);
        textview.setText(extramessage.getTitle());
        ((TextView)linearlayout3.findViewById(0x7f0a0185)).setText(extramessage.getMessage());
        if (extramessage.getAction() == null)
        {
            textview.setCompoundDrawables(null, null, null, null);
            textview.setBackgroundDrawable(null);
        }
        if (extramessage.getAction() != null)
        {
            textview.setOnClickListener(onclicklistener);
            textview.setTag(extramessage.getAction());
        }
          goto _L11
_L7:
        return i + 1;
_L2:
        linearlayout2 = linearlayout1;
          goto _L12
        Exception exception;
        exception;
          goto _L7
    }

    private static int a(ProductPageModel productpagemodel, LinearLayout linearlayout, android.view.View.OnClickListener onclicklistener, int i)
    {
        LinearLayout linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a01f5);
        if (linearlayout1 != null) goto _L2; else goto _L1
_L1:
        linearlayout1 = (LinearLayout)((LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater")).inflate(0x7f03009f, null);
        if (linearlayout.getChildCount() < i + 1) goto _L4; else goto _L3
_L3:
        linearlayout.addView(linearlayout1, i);
        LinearLayout linearlayout2 = linearlayout1;
_L9:
        if (productpagemodel.isSummaryEnabled()) goto _L6; else goto _L5
_L5:
        linearlayout2.setVisibility(8);
          goto _L7
_L4:
        linearlayout.addView(linearlayout1);
          goto _L2
_L6:
        try
        {
            CustomRobotoMediumTextView customrobotomediumtextview = (CustomRobotoMediumTextView)linearlayout2.findViewById(0x7f0a01f6);
            customrobotomediumtextview.setOnClickListener(onclicklistener);
            customrobotomediumtextview.setVisibility(0);
            linearlayout2.setVisibility(0);
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
        }
_L7:
        return i + 1;
_L2:
        linearlayout2 = linearlayout1;
        if (true) goto _L9; else goto _L8
_L8:
    }

    private static int a(ProductPageModel productpagemodel, LinearLayout linearlayout, android.view.View.OnClickListener onclicklistener, int i, Activity activity)
    {
        CallUsWidget calluswidget;
        calluswidget = (CallUsWidget)linearlayout.findViewById(CallUsWidget.Id);
        if (productpagemodel.getCallUsWidget() == null)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        if (calluswidget != null)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        CallUsWidget calluswidget1 = new CallUsWidget(activity.getApplicationContext(), productpagemodel.getCallUsWidget(), onclicklistener, activity, PageTypeUtils.ProductPage);
        if (linearlayout.getChildCount() >= i + 1)
        {
            linearlayout.addView(calluswidget1, i);
            break MISSING_BLOCK_LABEL_136;
        }
        int j;
        try
        {
            linearlayout.addView(calluswidget1);
        }
        catch (Exception exception) { }
        break MISSING_BLOCK_LABEL_136;
        j = linearlayout.indexOfChild(calluswidget);
        linearlayout.removeView(calluswidget);
        linearlayout.addView(new CallUsWidget(activity.getApplicationContext(), productpagemodel.getCallUsWidget(), onclicklistener, activity, PageTypeUtils.ProductPage), j);
        return j + 1;
        if (calluswidget == null)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        calluswidget.setVisibility(8);
        return i + 1;
    }

    private static int a(ProductPageModel productpagemodel, LinearLayout linearlayout, android.view.View.OnClickListener onclicklistener, boolean flag)
    {
        View view;
        View view1;
        TextView textview;
        TextView textview1;
        RelativeLayout relativelayout;
        try
        {
            view = linearlayout.findViewById(0x7f0a0194);
        }
        catch (Exception exception)
        {
            return 0;
        }
        if (view != null)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        view = ((LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater")).inflate(0x7f0300a8, null);
        linearlayout.addView(view);
        view1 = view;
        textview = (TextView)view1.findViewById(0x7f0a0201);
        textview.setContentDescription("product_page_main_title");
        textview.setText(productpagemodel.getMainTitle());
        textview1 = (TextView)view1.findViewById(0x7f0a0202);
        textview1.setContentDescription("product_page_sub_title");
        if (!StringUtils.isNullOrEmpty(productpagemodel.getSubTitle())) goto _L2; else goto _L1
_L1:
        textview1.setVisibility(8);
        textview.setPadding(ScreenMathUtils.dpToPx(10, linearlayout.getContext()), ScreenMathUtils.dpToPx(10, linearlayout.getContext()), 0, ScreenMathUtils.dpToPx(10, linearlayout.getContext()));
_L3:
        relativelayout = (RelativeLayout)view1.findViewById(0x7f0a0203);
        relativelayout.setVisibility(0);
        relativelayout.setOnClickListener(onclicklistener);
        if ((new WishListDao(FlipkartApplication.getAppContext())).getWishListById(productpagemodel.getProductId()) == null)
        {
            break MISSING_BLOCK_LABEL_254;
        }
        WishListUtils.setWishListTagOnButtons(relativelayout, "on_click_remove_from_wishlist", PageTypeUtils.ProductPage);
        return 0;
_L2:
        textview1.setVisibility(0);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_242;
        }
        textview1.setText((new StringBuilder("Ebook ")).append(productpagemodel.getSubTitle()).toString());
          goto _L3
        textview1.setText(productpagemodel.getSubTitle());
          goto _L3
        WishListUtils.setWishListTagOnButtons(relativelayout, "on_click_add_to_wishlist", PageTypeUtils.ProductPage);
        return 0;
    }

    private static int a(ProductPageModel productpagemodel, LinearLayout linearlayout, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener, int i)
    {
        LinearLayout linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a0193);
        if (linearlayout1 != null)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        linearlayout1 = (LinearLayout)((LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater")).inflate(0x7f030088, null);
        linearlayout.addView(linearlayout1);
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout1.getLayoutParams();
        int j = ScreenMathUtils.dpToPx(10, linearlayout.getContext());
        layoutparams.leftMargin = j;
        layoutparams.rightMargin = j;
        layoutparams.topMargin = j;
        linearlayout1.setLayoutParams(layoutparams);
        LinearLayout linearlayout2 = linearlayout1;
        RelativeLayout relativelayout = (RelativeLayout)linearlayout2.findViewById(0x7f0a018d);
        if (relativelayout != null)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        relativelayout = (RelativeLayout)((LayoutInflater)linearlayout2.getContext().getSystemService("layout_inflater")).inflate(0x7f030087, null);
        linearlayout2.addView(relativelayout);
        RelativeLayout relativelayout1 = relativelayout;
        NetworkImageView networkimageview;
        boolean flag;
        networkimageview = (NetworkImageView)relativelayout1.findViewById(0x7f0a0191);
        flag = productpagemodel.shouldOverLayOfferImage();
        if (!flag) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        android.view.ViewGroup.LayoutParams layoutparams1;
        String s;
        TextView textview1;
        TextView textview2;
        TextView textview3;
        try
        {
            String s3 = ImageUtils.getImageUrl((Image)AppConfigUtils.getInstance().getRunning_out_logo().get("imageMap"));
            if (!StringUtils.isNullOrEmpty(s3))
            {
                networkimageview.setImageUrl(s3, imageloader);
            }
        }
        catch (Exception exception1) { }
        arraylist = productpagemodel.getOriginalUrls();
        layoutparams1 = relativelayout1.getLayoutParams();
        if (arraylist == null) goto _L4; else goto _L3
_L3:
        if (arraylist.size() != 0) goto _L5; else goto _L4
_L4:
        s = productpagemodel.getProductErrorImageUrl();
        if (!StringUtils.isNullOrEmpty(s))
        {
            break MISSING_BLOCK_LABEL_1418;
        }
        layoutparams1.height = 0;
_L29:
        if (linearlayout2.findViewById(100) == null)
        {
            linearlayout2.addView(a(((View) (linearlayout)), 100));
        }
        a(productpagemodel, linearlayout2, onclicklistener);
        if (linearlayout2.findViewById(101) == null)
        {
            linearlayout2.addView(a(((View) (linearlayout)), 101));
        }
        a(productpagemodel, linearlayout2);
        textview1 = (TextView)linearlayout2.findViewById(0x7f0a01f4);
        if (textview1 != null)
        {
            break MISSING_BLOCK_LABEL_398;
        }
        textview1 = (TextView)((LayoutInflater)linearlayout2.getContext().getSystemService("layout_inflater")).inflate(0x7f03009e, null);
        linearlayout2.addView(textview1);
        android.widget.LinearLayout.LayoutParams layoutparams3 = (android.widget.LinearLayout.LayoutParams)textview1.getLayoutParams();
        layoutparams3.topMargin = ScreenMathUtils.dpToPx(0, linearlayout2.getContext());
        textview1.setLayoutParams(layoutparams3);
        textview2 = textview1;
        if (!productpagemodel.isEbook()) goto _L7; else goto _L6
_L6:
        textview3 = (TextView)linearlayout2.findViewById(0x7f0a0104);
        if (textview3 == null) goto _L9; else goto _L8
_L8:
        textview3.setText(Html.fromHtml("<a href=\"https://play.google.com/store/apps/details?id=com.flipkart.fkreader\">Checkout this book on our ebook app</a> "));
        textview3.setMovementMethod(LinkMovementMethod.getInstance());
_L7:
        textview2.setText(productpagemodel.getInventoryStatus());
        textview2.setTextColor(productpagemodel.getInventoryStatusColor());
        a(productpagemodel, linearlayout2, imageloader, onclicklistener);
        if (linearlayout2.findViewById(102) == null)
        {
            linearlayout2.addView(a(((View) (linearlayout)), 102));
        }
        break MISSING_BLOCK_LABEL_1425;
_L2:
        String s1;
        int k;
        View view;
        LinearLayout linearlayout3;
        int i1;
        NetworkImageView networkimageview1;
        View view1;
        int j1;
        LayoutInflater layoutinflater;
        Iterator iterator;
        int k1;
        boolean flag1;
        LinearLayout linearlayout4;
        ImageView imageview;
        android.widget.LinearLayout.LayoutParams layoutparams2;
        TextView textview;
        TextView textview4;
        String s2;
        LinearLayout linearlayout5;
        NetworkImageView networkimageview2;
        android.widget.LinearLayout.LayoutParams layoutparams4;
        int l1;
        View view2;
        int i2;
        try
        {
            networkimageview.setDefaultImageResId(0x7f02011c);
            break MISSING_BLOCK_LABEL_216;
        }
        catch (Exception exception) { }
        break MISSING_BLOCK_LABEL_1425;
_L5:
        i2 = arraylist.size();
        if (i >= i2 || i >= 5) goto _L11; else goto _L10
_L10:
        s1 = (String)arraylist.get(i);
_L21:
        k = productpagemodel.getImageViewHeight();
        layoutparams1.height = k;
        view = relativelayout1.findViewById(0x7f0a018e);
        linearlayout3 = (LinearLayout)relativelayout1.findViewById(0x7f0a0192);
        linearlayout3.getLayoutParams().height = (int)(0.17000000000000001D * (double)k);
        linearlayout3.removeAllViews();
        if (arraylist.size() > 1) goto _L13; else goto _L12
_L12:
        view.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, k));
        linearlayout3.setVisibility(8);
_L22:
        i1 = ScreenMathUtils.dpToPx(1, linearlayout2.getContext());
        view.setPadding(i1, i1, i1, i1);
        view.setBackgroundResource(0x7f0200f6);
        view.setOnClickListener(onclicklistener);
        view.setTag("open_gallery_view");
        networkimageview1 = (NetworkImageView)relativelayout1.findViewById(0x7f0a018f);
        networkimageview1.getLocationOnScreen(new int[2]);
        networkimageview1.setImageUrl(s1, imageloader);
        view1 = relativelayout1.findViewById(0x7f0a0190);
        if (!productpagemodel.isOfferExisting()) goto _L15; else goto _L14
_L14:
        view1.setVisibility(0);
        view1.setOnClickListener(onclicklistener);
_L23:
        if (arraylist.size() <= 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        j1 = (FlipkartApplication.getAppContext().getResources().getDisplayMetrics().widthPixels - (ScreenMathUtils.dpToPx(55, linearlayout2.getContext()) + ScreenMathUtils.dpToPx(2, linearlayout2.getContext()))) / 5;
        layoutinflater = (LayoutInflater)linearlayout2.getContext().getSystemService("layout_inflater");
        iterator = productpagemodel.getThumbnailsUrls().iterator();
        k1 = 0;
_L24:
        if (!iterator.hasNext()) goto _L17; else goto _L16
_L16:
        s2 = (String)iterator.next();
        if (k1 != 4) goto _L19; else goto _L18
_L18:
        if (productpagemodel.getThumbnailsUrls().size() <= 5) goto _L19; else goto _L20
_L20:
        flag1 = true;
_L27:
        if (!flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        linearlayout4 = new LinearLayout(linearlayout2.getContext());
        linearlayout4.setLayoutParams(new android.widget.LinearLayout.LayoutParams(j1, -1));
        linearlayout4.setBackgroundResource(0x7f02012c);
        linearlayout4.setOrientation(1);
        linearlayout4.setGravity(16);
        imageview = new ImageView(linearlayout2.getContext());
        layoutparams2 = new android.widget.LinearLayout.LayoutParams(-2, -2);
        layoutparams2.gravity = 1;
        layoutparams2.setMargins(0, ScreenMathUtils.dpToPx(4, linearlayout2.getContext()), 0, 0);
        imageview.setLayoutParams(layoutparams2);
        imageview.setImageResource(0x7f02015f);
        imageview.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
        linearlayout4.addView(imageview);
        textview = new TextView(linearlayout2.getContext());
        textview.setGravity(1);
        textview.setText("See\nmore");
        textview.setTextSize(12F);
        linearlayout4.addView(textview);
        linearlayout4.setClickable(true);
        linearlayout4.setOnClickListener(onclicklistener);
        linearlayout4.setTag("open_gallery_view");
        linearlayout3.addView(linearlayout4);
        continue; /* Loop/switch isn't completed */
_L11:
        s1 = (String)arraylist.get(0);
        i = 0;
          goto _L21
_L13:
        view.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, (int)(0.80000000000000004D * (double)k) - ScreenMathUtils.dpToPx(10, linearlayout2.getContext())));
        linearlayout3.setVisibility(0);
          goto _L22
_L15:
        view1.setVisibility(4);
          goto _L23
_L19:
        linearlayout5 = (LinearLayout)layoutinflater.inflate(0x7f0300a7, null);
        linearlayout5.setLayoutParams(new android.widget.LinearLayout.LayoutParams(j1, -1));
        if (i != k1)
        {
            break MISSING_BLOCK_LABEL_1337;
        }
        linearlayout5.setBackgroundResource(0x7f02012b);
_L25:
        networkimageview2 = (NetworkImageView)linearlayout5.findViewById(0x7f0a004f);
        layoutparams4 = new android.widget.LinearLayout.LayoutParams(j1 - ScreenMathUtils.dpToPx(4, linearlayout2.getContext()), -1);
        l1 = ScreenMathUtils.dpToPx(2, linearlayout2.getContext());
        layoutparams4.setMargins(l1, l1, l1, l1);
        networkimageview2.setLayoutParams(layoutparams4);
        networkimageview2.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
        networkimageview2.setImageUrl(s2, imageloader);
        networkimageview2.setClickable(true);
        networkimageview2.setOnClickListener(onclicklistener);
        networkimageview2.setTag((new StringBuilder("on_click_change_product_image/")).append(k1).toString());
        linearlayout3.addView(linearlayout5);
        view2 = new View(linearlayout2.getContext());
        view2.setLayoutParams(new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(5, linearlayout2.getContext()), -1));
        linearlayout3.addView(view2);
        k1++;
          goto _L24
        linearlayout5.setBackgroundResource(0x7f02012c);
          goto _L25
_L9:
        if (textview3 != null) goto _L7; else goto _L26
_L26:
        textview4 = (TextView)((LayoutInflater)linearlayout2.getContext().getSystemService("layout_inflater")).inflate(0x7f03004c, null);
        textview4.setText(Html.fromHtml("<a href=\"https://play.google.com/store/apps/details?id=com.flipkart.fkreader\">Checkout this book on our ebook app</a> "));
        textview4.setMovementMethod(LinkMovementMethod.getInstance());
        linearlayout2.addView(textview4);
          goto _L7
_L17:
        flag1 = false;
          goto _L27
        s1 = s;
          goto _L21
        return 2;
        if (true) goto _L29; else goto _L28
_L28:
    }

    private static int a(ProductPageModel productpagemodel, LinearLayout linearlayout, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener, android.widget.AdapterView.OnItemSelectedListener onitemselectedlistener, boolean flag, int i, FkProductListContext fkproductlistcontext, 
            Activity activity)
    {
        LinearLayout linearlayout1;
        LayoutInflater layoutinflater;
        linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a01fb);
        layoutinflater = (LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater");
        if (linearlayout1 != null) goto _L2; else goto _L1
_L1:
        LinearLayout linearlayout2 = (LinearLayout)layoutinflater.inflate(0x7f0300a6, null);
        if (linearlayout.getChildCount() < i + 1) goto _L4; else goto _L3
_L3:
        linearlayout.addView(linearlayout2, i);
_L19:
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout2.getLayoutParams();
        ScreenMathUtils.dpToPx(10, linearlayout.getContext());
        linearlayout2.setLayoutParams(layoutparams);
        LinearLayout linearlayout3 = linearlayout2;
_L20:
        Map map = productpagemodel.getSwatchesMap();
        if (map == null) goto _L6; else goto _L5
_L5:
        LinearLayout linearlayout4;
        Set set;
        linearlayout3.setVisibility(0);
        linearlayout4 = (LinearLayout)linearlayout3.findViewById(0x7f0a0200);
        set = map.keySet();
        if (set.size() <= 0) goto _L8; else goto _L7
_L7:
        linearlayout4.setVisibility(0);
        linearlayout4.removeAllViews();
        int j = 0;
        if (!flag) goto _L10; else goto _L9
_L9:
        int i2 = 0;
        Iterator iterator2 = set.iterator();
_L28:
        if (!iterator2.hasNext()) goto _L10; else goto _L11
_L11:
        if (!((String)iterator2.next()).contains("size")) goto _L13; else goto _L12
_L12:
        int k = i2;
_L27:
        boolean flag1 = false;
        Iterator iterator = set.iterator();
_L31:
        if (!iterator.hasNext()) goto _L15; else goto _L14
_L14:
        String s;
        com.flipkart.android.fragments.model.ProductPageModel.SwatchModel swatchmodel;
        s = (String)iterator.next();
        swatchmodel = (com.flipkart.android.fragments.model.ProductPageModel.SwatchModel)map.get(s);
        if (swatchmodel.getType() == 3) goto _L17; else goto _L16
_L16:
        ArrayList arraylist1 = swatchmodel.getSwaatchValues();
        int j1 = 0;
        boolean flag2;
        for (Iterator iterator1 = arraylist1.iterator(); iterator1.hasNext();)
        {
            ArrayList arraylist;
            Spinner spinner;
            android.widget.LinearLayout.LayoutParams layoutparams1;
            int i1;
            Context context;
            View view;
            android.widget.LinearLayout.LayoutParams layoutparams2;
            LinearLayout linearlayout5;
            android.widget.LinearLayout.LayoutParams layoutparams3;
            int k1;
            int l1;
            View view1;
            if (((com.flipkart.android.fragments.model.ProductPageModel.SwatchValueModel)iterator1.next()).isAvailable())
            {
                l1 = j1 + 1;
            } else
            {
                l1 = j1;
            }
            j1 = l1;
        }

          goto _L18
_L4:
        linearlayout.addView(linearlayout2);
          goto _L19
_L2:
        view1 = linearlayout1.getChildAt(0);
        linearlayout1.removeAllViews();
        linearlayout1.addView(view1);
        linearlayout3 = linearlayout1;
          goto _L20
_L30:
        if (flag1) goto _L22; else goto _L21
_L21:
        if (flag) goto _L24; else goto _L23
_L23:
        a(productpagemodel, ((View) (linearlayout3)), j, s, activity, fkproductlistcontext, imageloader, onclicklistener, onitemselectedlistener, flag);
        flag1 = true;
_L22:
        linearlayout5 = (LinearLayout)layoutinflater.inflate(0x7f0300a5, null);
        layoutparams3 = new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(58, linearlayout.getContext()), -1, 1.0F);
        k1 = ScreenMathUtils.dpToPx(5, linearlayout.getContext());
        layoutparams3.setMargins(k1, k1, k1, k1);
        layoutparams3.gravity = 16;
        linearlayout5.setLayoutParams(layoutparams3);
        ((TextView)linearlayout5.findViewById(0x7f0a01ff)).setText((new StringBuilder("SELECT ")).append(swatchmodel.getTitle().toUpperCase()).toString());
        linearlayout5.setTag((new StringBuilder("on_click_swatch/")).append(s).toString());
        linearlayout5.setOnClickListener(onclicklistener);
        linearlayout4.addView(linearlayout5);
        flag2 = flag1;
_L26:
        j++;
        if (j < map.size())
        {
            context = linearlayout.getContext();
            view = new View(context);
            layoutparams2 = new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(1, context), -1);
            layoutparams2.topMargin = ScreenMathUtils.dpToPx(5, context);
            layoutparams2.bottomMargin = ScreenMathUtils.dpToPx(5, context);
            view.setLayoutParams(layoutparams2);
            view.setBackgroundColor(context.getResources().getColor(0x7f09002a));
            linearlayout4.addView(view);
        }
        break MISSING_BLOCK_LABEL_884;
_L24:
        if (k != j) goto _L22; else goto _L25
_L25:
        a(productpagemodel, ((View) (linearlayout3)), k, s, activity, fkproductlistcontext, imageloader, onclicklistener, onitemselectedlistener, flag);
        flag1 = true;
          goto _L22
_L17:
        arraylist = swatchmodel.getSwaatchValues();
        spinner = (Spinner)layoutinflater.inflate(0x7f0300a1, null);
        spinner.setAdapter(a(linearlayout.getContext(), 0x1090009, ((List) (arraylist)), swatchmodel.getTitle()));
        spinner.setSelection(swatchmodel.getCurrentIndex());
        spinner.post(new n(spinner, onitemselectedlistener));
        layoutparams1 = new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(58, linearlayout.getContext()), -1, 1.0F);
        i1 = ScreenMathUtils.dpToPx(5, linearlayout.getContext());
        layoutparams1.setMargins(i1, i1, i1, i1);
        layoutparams1.gravity = 16;
        spinner.setLayoutParams(layoutparams1);
        spinner.setBackgroundColor(0xffeeebe3);
        linearlayout4.addView(spinner);
        flag2 = flag1;
          goto _L26
_L8:
        try
        {
            linearlayout3.setVisibility(8);
        }
        catch (Exception exception) { }
          goto _L15
_L6:
        linearlayout3.setVisibility(8);
          goto _L15
_L10:
        k = 0;
          goto _L27
_L15:
        return i + 1;
_L13:
        i2++;
          goto _L28
_L18:
        if (j1 != 0) goto _L30; else goto _L29
_L29:
        j++;
          goto _L31
        flag1 = flag2;
          goto _L31
    }

    private static View a(View view, int i)
    {
        View view1 = new View(view.getContext());
        view1.setBackgroundDrawable(view.getContext().getResources().getDrawable(0x7f020158));
        view1.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(2, view.getContext())));
        view1.setId(i);
        return view1;
    }

    private static ArrayAdapter a(Context context, int i, List list, String s)
    {
        return new p(context, 0x1090009, list, s, context);
    }

    private static void a(ProductPageModel productpagemodel, View view, int i, String s, Activity activity, FkProductListContext fkproductlistcontext, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener, 
            android.widget.AdapterView.OnItemSelectedListener onitemselectedlistener, boolean flag)
    {
label0:
        {
            Map map = productpagemodel.getSwatchesMap();
            Iterator iterator = map.keySet().iterator();
            int j = 0;
            while (iterator.hasNext()) 
            {
                ArrayList arraylist = ((com.flipkart.android.fragments.model.ProductPageModel.SwatchModel)map.get((String)iterator.next())).getSwaatchValues();
                int k = 0;
                Iterator iterator1 = arraylist.iterator();
                while (iterator1.hasNext()) 
                {
                    com.flipkart.android.fragments.model.ProductPageModel.SwatchModel swatchmodel;
                    LinearLayout linearlayout;
                    View view1;
                    View view2;
                    ProductInfo productinfo;
                    View view3;
                    int i1;
                    int j1;
                    if (((com.flipkart.android.fragments.model.ProductPageModel.SwatchValueModel)iterator1.next()).isAvailable())
                    {
                        j1 = k + 1;
                    } else
                    {
                        j1 = k;
                    }
                    k = j1;
                }
                if (k > 0)
                {
                    i1 = j + 1;
                } else
                {
                    i1 = j;
                }
                j = i1;
            }
            if (map != null)
            {
                swatchmodel = (com.flipkart.android.fragments.model.ProductPageModel.SwatchModel)map.get(s);
                if (view.getId() == 0x7f0a01fb)
                {
                    linearlayout = (LinearLayout)view;
                    linearlayout.setContentDescription("product_page_swatch_parent_layout");
                    if (linearlayout.getChildCount() <= 1 || !linearlayout.getChildAt(1).getTag().equals(s))
                    {
                        break label0;
                    }
                    view3 = linearlayout.getChildAt(0);
                    linearlayout.removeAllViews();
                    linearlayout.addView(view3);
                }
            }
            return;
        }
        view1 = linearlayout.getChildAt(0);
        linearlayout.removeAllViews();
        linearlayout.addView(view1);
        view2 = buildSwatchArrowView(activity, j, i, ScreenMathUtils.dpToPx(290, activity) / j);
        view2.setTag(s);
        linearlayout.addView(view2);
        productinfo = (ProductInfo)fkproductlistcontext.getProductMap().get(productpagemodel.getProductId());
        linearlayout.addView(buildSwatchItemsView(activity, swatchmodel, onclicklistener, imageloader, onitemselectedlistener, flag, productpagemodel.getSizeChartUrl(), fkproductlistcontext.getSelectedSizes(), fkproductlistcontext.getProductIds(), new AnalyticData(productinfo.getRequestId(), null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil())));
    }

    private static void a(ProductPageModel productpagemodel, LinearLayout linearlayout)
    {
        if (linearlayout.findViewById(999) != null || productpagemodel.getTitleNote() == null || productpagemodel.getTitleNote().size() == 0)
        {
            return;
        }
        LinearLayout linearlayout1 = new LinearLayout(linearlayout.getContext());
        linearlayout1.setId(999);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        int i = ScreenMathUtils.dpToPx(10, linearlayout.getContext());
        layoutparams.topMargin = i;
        layoutparams.bottomMargin = i;
        linearlayout1.setLayoutParams(layoutparams);
        linearlayout1.setOrientation(1);
        CustomRobotoRegularTextView customrobotoregulartextview = new CustomRobotoRegularTextView(linearlayout.getContext(), null);
        customrobotoregulartextview.setText("Note");
        customrobotoregulartextview.setTextSize(2, 15F);
        customrobotoregulartextview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(25, linearlayout.getContext())));
        customrobotoregulartextview.setPadding(0, 0, i, 0);
        customrobotoregulartextview.setTextColor(Color.parseColor("#000000"));
        linearlayout1.addView(customrobotoregulartextview);
        for (int j = 0; j < productpagemodel.getTitleNote().size(); j++)
        {
            String s = (String)productpagemodel.getTitleNote().get(j);
            Context context = linearlayout.getContext();
            int k = ScreenMathUtils.dpToPx(10, context);
            ScreenMathUtils.dpToPx(15, context);
            CustomRobotoLightTextView customrobotolighttextview = new CustomRobotoLightTextView(context, null);
            customrobotolighttextview.setText(s);
            customrobotolighttextview.setTextSize(2, 14F);
            customrobotolighttextview.setTextColor(Color.parseColor("#565656"));
            customrobotolighttextview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2, 35F));
            LinearLayout linearlayout2 = new LinearLayout(context);
            linearlayout2.setOrientation(0);
            android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-1, -2);
            layoutparams1.bottomMargin = k;
            linearlayout2.setLayoutParams(layoutparams1);
            ImageView imageview = new ImageView(context);
            android.widget.LinearLayout.LayoutParams layoutparams2 = new android.widget.LinearLayout.LayoutParams(-2, -2, 5F);
            layoutparams2.gravity = 48;
            layoutparams2.topMargin = ScreenMathUtils.dpToPx(6, context);
            imageview.setLayoutParams(layoutparams2);
            imageview.setImageResource(0x7f0200da);
            linearlayout2.setPadding(0, 0, k, 0);
            customrobotolighttextview.setPadding(ScreenMathUtils.dpToPx(6, context), 1, 0, 1);
            linearlayout2.addView(imageview);
            linearlayout2.addView(customrobotolighttextview);
            linearlayout1.addView(linearlayout2);
        }

        linearlayout1.addView(a(((View) (linearlayout)), 110));
        linearlayout.addView(linearlayout1, 4);
    }

    private static void a(ProductPageModel productpagemodel, LinearLayout linearlayout, android.view.View.OnClickListener onclicklistener)
    {
        LayoutInflater layoutinflater;
        LinearLayout linearlayout2;
        LinearLayout linearlayout3;
        ArrayList arraylist;
        LinearLayout linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a01bb);
        layoutinflater = (LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater");
        if (linearlayout1 == null)
        {
            linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f030090, null);
            linearlayout.addView(linearlayout1);
            android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout1.getLayoutParams();
            int k = ScreenMathUtils.dpToPx(10, linearlayout.getContext());
            layoutparams.topMargin = k;
            layoutparams.bottomMargin = k;
            linearlayout1.setLayoutParams(layoutparams);
        }
        linearlayout2 = linearlayout1;
        linearlayout3 = (LinearLayout)linearlayout2.findViewById(0x7f0a01bc);
        arraylist = productpagemodel.getPriceWidget().getPrices();
        if (arraylist == null || arraylist.size() <= 0) goto _L2; else goto _L1
_L1:
        PriceType pricetype;
        linearlayout3.setVisibility(0);
        pricetype = null;
        if (linearlayout3.getChildCount() > 2)
        {
            linearlayout3.removeViews(0, -2 + linearlayout3.getChildCount());
        }
        for (int j = 0; j < arraylist.size();)
        {
            PriceType pricetype1 = (PriceType)arraylist.get(j);
            if (!pricetype1.isFinal())
            {
                LinearLayout linearlayout5 = (LinearLayout)layoutinflater.inflate(0x7f03006e, null);
                TextView textview5 = (TextView)linearlayout5.findViewById(0x7f0a014f);
                TextView textview6 = (TextView)linearlayout5.findViewById(0x7f0a0150);
                textview5.setText((new StringBuilder()).append(pricetype1.getDisplayName()).append(": ").toString());
                textview6.setText((new StringBuilder()).append(pricetype1.getCurrency()).append(pricetype1.getPrice()).toString());
                textview6.setPaintFlags(0x10 | textview6.getPaintFlags());
                linearlayout3.addView(linearlayout5, j);
                pricetype1 = pricetype;
            }
            j++;
            pricetype = pricetype1;
        }

        if (pricetype == null) goto _L4; else goto _L3
_L3:
        TextView textview4;
        String s2;
        RelativeLayout relativelayout = (RelativeLayout)linearlayout3.findViewById(0x7f0a01bd);
        relativelayout.setVisibility(0);
        TextView textview2 = (TextView)relativelayout.findViewById(0x7f0a0150);
        TextView textview3 = (TextView)relativelayout.findViewById(0x7f0a014f);
        textview4 = (TextView)relativelayout.findViewById(0x7f0a01be);
        textview2.setText((new StringBuilder()).append(pricetype.getCurrency()).append(pricetype.getPrice()).toString());
        textview3.setText(pricetype.getDisplayName());
        s2 = productpagemodel.getPriceWidget().getDiscount();
        textview4.setTypeface(null, 2);
        textview4.setVisibility(8);
        textview4.setContentDescription("product_page_price_off_text");
        if (StringUtils.isNullOrEmpty(s2)) goto _L6; else goto _L5
_L5:
        textview4.setText(s2);
        textview4.setVisibility(0);
_L4:
        RatingBar ratingbar;
        TextView textview;
        ImageView imageview;
        TextView textview1 = (TextView)linearlayout3.findViewById(0x7f0a01bf);
        String s = productpagemodel.getPriceWidget().getEmiUrl();
        String s1 = productpagemodel.getPriceWidget().getEmi();
        if (!StringUtils.isNullOrEmpty(s1))
        {
            textview1.setVisibility(0);
            textview1.setText(s1);
            LinearLayout linearlayout4;
            int i;
            Exception exception;
            if (!StringUtils.isNullOrEmpty(s))
            {
                textview1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f02013e, 0);
                textview1.setOnClickListener(onclicklistener);
                if (!s.contains("http://flipkart.com"))
                {
                    s = (new StringBuilder()).append(GsonRequest.BASE_WEB_URL).append(s).toString();
                }
                textview1.setTag((new StringBuilder("on_click_emi_text")).append(s).toString());
            } else
            {
                textview1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        } else
        {
            textview1.setVisibility(8);
        }
_L7:
        linearlayout4 = (LinearLayout)linearlayout2.findViewById(0x7f0a01c1);
        i = ScreenMathUtils.dpToPx(1, linearlayout.getContext());
        linearlayout4.setPadding(i, i, i, i);
        linearlayout4.setBackgroundResource(0x7f0200f6);
        ratingbar = (RatingBar)linearlayout2.findViewById(0x7f0a01c2);
        textview = (TextView)linearlayout2.findViewById(0x7f0a01c3);
        imageview = (ImageView)linearlayout2.findViewById(0x7f0a01c4);
        if (productpagemodel.isRatingVisible())
        {
            ratingbar.setVisibility(0);
            imageview.setVisibility(0);
            linearlayout4.setOnClickListener(onclicklistener);
            ratingbar.setRating(productpagemodel.getRating());
            long l1 = productpagemodel.getTotalRatings();
            long l2 = productpagemodel.getTotalReviews();
            StringBuilder stringbuilder = new StringBuilder();
            if (l1 == 1L)
            {
                stringbuilder.append((new StringBuilder()).append(l1).append(" Rating ").toString());
            } else
            if (l1 > 1L)
            {
                stringbuilder.append((new StringBuilder()).append(l1).append(" Ratings ").toString());
            }
            if (l2 == 1L)
            {
                stringbuilder.append((new StringBuilder(", ")).append(l2).append(" Review").toString());
            } else
            if (l2 > 1L)
            {
                stringbuilder.append((new StringBuilder(", ")).append(l2).append(" Reviews").toString());
            }
            textview.setText(stringbuilder.toString());
            linearlayout2.findViewById(0x7f0a01c0).setVisibility(0);
            return;
        }
        break MISSING_BLOCK_LABEL_971;
_L6:
        try
        {
            textview4.setVisibility(8);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception) { }
          goto _L4
_L2:
        linearlayout3.setVisibility(8);
          goto _L7
        ratingbar.setVisibility(8);
        linearlayout2.findViewById(0x7f0a01c0).setVisibility(8);
        textview.setText("");
        imageview.setVisibility(8);
        return;
          goto _L7
    }

    private static void a(ProductPageModel productpagemodel, LinearLayout linearlayout, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener)
    {
        LinearLayout linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a017f);
        if (productpagemodel.isEbook() || !productpagemodel.isCheckoutEnabled()) goto _L2; else goto _L1
_L1:
        View view;
        LinearLayout linearlayout2;
        ArrayList arraylist;
        if (linearlayout1 == null)
        {
            LinearLayout linearlayout4 = (LinearLayout)((LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater")).inflate(0x7f03007f, null);
            linearlayout.addView(linearlayout4);
            android.widget.LinearLayout.LayoutParams layoutparams2 = (android.widget.LinearLayout.LayoutParams)linearlayout4.getLayoutParams();
            ScreenMathUtils.dpToPx(10, linearlayout.getContext());
            layoutparams2.bottomMargin = ScreenMathUtils.dpToPx(6, linearlayout.getContext());
            linearlayout4.setLayoutParams(layoutparams2);
            NewEditText newedittext = (NewEditText)linearlayout4.findViewById(0x7f0a0182);
            EditText edittext = newedittext.getEditText();
            newedittext.setParams("Enter Pincode", 2, 6);
            edittext.setTextColor(linearlayout.getResources().getColor(0x7f090031));
            edittext.setTextSize(2, 14F);
            edittext.setContentDescription("product_page_pincode_text");
            Button button = (Button)linearlayout4.findViewById(0x7f0a0183);
            button.setContentDescription("product_page_pincode_enter");
            button.setOnClickListener(onclicklistener);
            edittext.setOnEditorActionListener(new o(button));
            linearlayout2 = linearlayout4;
        } else
        {
            linearlayout1.setVisibility(0);
            linearlayout2 = linearlayout1;
        }
        view = linearlayout2.findViewById(0x7f0a0180);
        linearlayout2.removeAllViews();
        arraylist = productpagemodel.getPromiseWidget();
        if (arraylist != null)
        {
            Iterator iterator = arraylist.iterator();
            while (iterator.hasNext()) 
            {
                PromiseWidgetModel promisewidgetmodel = (PromiseWidgetModel)iterator.next();
                LinearLayout linearlayout3 = (LinearLayout)((LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater")).inflate(0x7f030092, null);
                ImageView imageview = (ImageView)linearlayout3.findViewById(0x7f0a01c7);
                NetworkImageView networkimageview;
                TextView textview;
                NetworkImageView networkimageview1;
                android.widget.LinearLayout.LayoutParams layoutparams;
                if (promisewidgetmodel.getBulletType() == com.flipkart.android.response.productInfo.PromiseWidget.BulletType.TICK)
                {
                    imageview.setImageResource(0x7f020173);
                } else
                if (promisewidgetmodel.getBulletType() == com.flipkart.android.response.productInfo.PromiseWidget.BulletType.CROSS)
                {
                    imageview.setImageResource(0x7f02008f);
                } else
                if (promisewidgetmodel.getBulletType() == com.flipkart.android.response.productInfo.PromiseWidget.BulletType.LINE)
                {
                    linearlayout3.removeView(imageview);
                    View view1 = new View(linearlayout.getContext());
                    android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(1, linearlayout.getContext()));
                    layoutparams1.setMargins(0, ScreenMathUtils.dpToPx(5, linearlayout.getContext()), 0, ScreenMathUtils.dpToPx(5, linearlayout.getContext()));
                    view1.setLayoutParams(layoutparams1);
                    view1.setBackgroundColor(0xffefefef);
                    linearlayout2.addView(view1);
                } else
                {
                    linearlayout3.removeView(imageview);
                }
                networkimageview = (NetworkImageView)linearlayout3.findViewById(0x7f0a01c8);
                if (!StringUtils.isNullOrEmpty(promisewidgetmodel.getImageView1Url()))
                {
                    networkimageview.setImageUrl(promisewidgetmodel.getImageView1Url(), imageloader);
                    networkimageview.setOnClickListener(onclicklistener);
                    networkimageview.setTag(promisewidgetmodel.getImageView1Action());
                } else
                {
                    linearlayout3.removeView(networkimageview);
                }
                textview = (TextView)linearlayout3.findViewById(0x7f0a01c9);
                textview.setPadding(ScreenMathUtils.dpToPx(ScreenMathUtils.dpToPx(3, linearlayout.getContext()), linearlayout.getContext()), 0, 0, 0);
                textview.setText(Html.fromHtml(promisewidgetmodel.getBody(), null, new HtmlTagHandler()));
                networkimageview1 = (NetworkImageView)linearlayout3.findViewById(0x7f0a01ca);
                if (!StringUtils.isNullOrEmpty(promisewidgetmodel.getImageView2Url()))
                {
                    networkimageview1.setImageUrl(promisewidgetmodel.getImageView2Url(), imageloader);
                    networkimageview1.setOnClickListener(onclicklistener);
                    networkimageview1.setTag(promisewidgetmodel.getImageView2Action());
                } else
                {
                    networkimageview1.setVisibility(8);
                    textview.setMaxWidth(0x7fffffff);
                }
                layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
                layoutparams.setMargins(0, ScreenMathUtils.dpToPx(2, linearlayout.getContext()), 0, ScreenMathUtils.dpToPx(2, linearlayout.getContext()));
                linearlayout2.addView(linearlayout3, layoutparams);
            }
        }
        linearlayout2.addView(view);
        if (!productpagemodel.isPinAvailable()) goto _L4; else goto _L3
_L3:
        view.setVisibility(8);
_L6:
        return;
_L4:
        view.setVisibility(0);
        return;
_L2:
        if (linearlayout1 != null)
        {
            linearlayout1.setVisibility(8);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    private static int b(ProductPageModel productpagemodel, LinearLayout linearlayout, int i, android.view.View.OnClickListener onclicklistener)
    {
        LinearLayout linearlayout1;
        LayoutInflater layoutinflater;
        linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a01ae);
        layoutinflater = (LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater");
        if (linearlayout1 != null) goto _L2; else goto _L1
_L1:
        linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f03008c, null);
        if (linearlayout.getChildCount() < i + 1) goto _L4; else goto _L3
_L3:
        linearlayout.addView(linearlayout1, i);
_L9:
        linearlayout1.setLayoutParams((android.widget.LinearLayout.LayoutParams)linearlayout1.getLayoutParams());
_L2:
        LinearLayout linearlayout2 = linearlayout1;
        LinearLayout linearlayout3;
        ArrayList arraylist;
        linearlayout3 = (LinearLayout)((OfferExpandablePanel)linearlayout2.findViewById(0x7f0a01b0)).findViewById(0x7f0a01b2);
        arraylist = productpagemodel.getAllOffers();
        if (arraylist == null) goto _L6; else goto _L5
_L5:
        if (arraylist.size() != 0) goto _L7; else goto _L6
_L6:
        linearlayout2.setVisibility(8);
          goto _L8
_L4:
        linearlayout.addView(linearlayout1);
          goto _L9
_L7:
        linearlayout2.setVisibility(0);
        linearlayout3.removeAllViews();
        int j;
        int k;
        int i1;
        j = 0;
        k = 0;
        i1 = 0;
_L14:
        if (j >= arraylist.size()) goto _L11; else goto _L10
_L10:
        TextView textview;
        SantaOffers santaoffers = (SantaOffers)arraylist.get(j);
        CustomRobotoLightTextView customrobotolighttextview = (CustomRobotoLightTextView)layoutinflater.inflate(0x7f03008b, null);
        customrobotolighttextview.setTextColor(Color.parseColor("#FFFFFF"));
        customrobotolighttextview.setText(santaoffers.getDescription());
        linearlayout3.addView(customrobotolighttextview);
        if (santaoffers.getOfferId() == null || !santaoffers.getOfferType().equals("santa"))
        {
            break MISSING_BLOCK_LABEL_333;
        }
        textview = (TextView)layoutinflater.inflate(0x7f03004c, null);
        textview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(170, linearlayout.getContext()), -2));
        if (textview == null)
        {
            break MISSING_BLOCK_LABEL_305;
        }
        textview.setText(StringUtils.getHyperLinkedText("View Terms & Conditions"));
        textview.setLinkTextColor(-1);
        textview.setTag("offer_terms");
        textview.setId(k);
        textview.setOnClickListener(onclicklistener);
        linearlayout3.addView(textview);
        if (i1 != -1 + arraylist.size())
        {
            View view = new View(linearlayout.getContext());
            view.setBackgroundColor(Color.parseColor("#F5F5F5"));
            view.getBackground().setAlpha(75);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(1, linearlayout.getContext()) / 2);
            layoutparams.topMargin = ScreenMathUtils.dpToPx(5, linearlayout.getContext());
            layoutparams.leftMargin = ScreenMathUtils.dpToPx(5, linearlayout.getContext());
            layoutparams.rightMargin = ScreenMathUtils.dpToPx(5, linearlayout.getContext());
            view.setLayoutParams(layoutparams);
            view.setId(103);
            linearlayout3.addView(view);
        }
          goto _L12
_L11:
        try
        {
            OfferBackgroundUtils.setOffersBackground(linearlayout2, AppConfigUtils.getInstance().getProductPageTheme());
        }
        catch (Exception exception) { }
_L8:
        return i + 1;
_L12:
        i1++;
        int j1 = k + 1;
        j++;
        k = j1;
        if (true) goto _L14; else goto _L13
_L13:
    }

    private static int b(ProductPageModel productpagemodel, LinearLayout linearlayout, android.view.View.OnClickListener onclicklistener, int i)
    {
        int j = 0;
        LinearLayout linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a01f0);
        if (linearlayout1 != null) goto _L2; else goto _L1
_L1:
        linearlayout1 = (LinearLayout)((LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater")).inflate(0x7f03009c, null);
        if (linearlayout.getChildCount() < i + 1) goto _L4; else goto _L3
_L3:
        linearlayout.addView(linearlayout1, i);
_L8:
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout1.getLayoutParams();
        layoutparams.topMargin = ScreenMathUtils.dpToPx(5, linearlayout.getContext());
        linearlayout1.setLayoutParams(layoutparams);
_L2:
        LinearLayout linearlayout2 = linearlayout1;
        linearlayout2.findViewById(0x7f0a01f1).setOnClickListener(onclicklistener);
        if (productpagemodel.isSpecificationEnabled()) goto _L6; else goto _L5
_L5:
        linearlayout2.setVisibility(8);
          goto _L7
_L4:
        Exception exception;
        linearlayout.addView(linearlayout1);
          goto _L8
_L6:
        if (productpagemodel.getKeyFeatures() == null || productpagemodel.getKeyFeatures().size() <= 0) goto _L10; else goto _L9
_L9:
        ArrayList arraylist;
        linearlayout2.setVisibility(0);
        arraylist = productpagemodel.getKeyFeatures();
        LinearLayout linearlayout4;
        linearlayout4 = (LinearLayout)linearlayout2.findViewById(0x7f0a01f2);
        linearlayout4.removeAllViews();
_L16:
        if (j >= 4) goto _L12; else goto _L11
_L11:
        if (j >= arraylist.size()) goto _L12; else goto _L13
_L13:
        if (arraylist.get(j) != null)
        {
            CustomRobotoLightTextView customrobotolighttextview = new CustomRobotoLightTextView(FlipkartApplication.getAppContext(), null);
            customrobotolighttextview.setText((CharSequence)arraylist.get(j));
            customrobotolighttextview.setTextSize(14F);
            customrobotolighttextview.setTextColor(Color.parseColor("#565656"));
            android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-1, -2, 95F);
            customrobotolighttextview.setPadding(ScreenMathUtils.dpToPx(6, FlipkartApplication.getAppContext()), 1, 0, 1);
            customrobotolighttextview.setLayoutParams(layoutparams1);
            LinearLayout linearlayout5 = new LinearLayout(FlipkartApplication.getAppContext());
            linearlayout5.setOrientation(0);
            linearlayout5.setPadding(ScreenMathUtils.dpToPx(10, FlipkartApplication.getAppContext()), 0, ScreenMathUtils.dpToPx(10, FlipkartApplication.getAppContext()), 0);
            linearlayout5.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
            ImageView imageview = new ImageView(FlipkartApplication.getAppContext());
            android.widget.LinearLayout.LayoutParams layoutparams2 = new android.widget.LinearLayout.LayoutParams(-2, -2, 5F);
            layoutparams2.gravity = 48;
            layoutparams2.topMargin = ScreenMathUtils.dpToPx(8, FlipkartApplication.getAppContext());
            imageview.setLayoutParams(layoutparams2);
            imageview.setImageResource(0x7f0200da);
            linearlayout5.addView(imageview);
            linearlayout5.addView(customrobotolighttextview);
            linearlayout4.addView(linearlayout5);
        }
          goto _L14
_L12:
        try
        {
            linearlayout4.setVisibility(0);
        }
        catch (Exception exception1)
        {
            try
            {
                FkLogger.printStackTrace(exception1);
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception)
            {
                FkLogger.printStackTrace(exception);
            }
        }
          goto _L7
_L10:
        LinearLayout linearlayout3 = (LinearLayout)linearlayout2.findViewById(0x7f0a01f2);
        linearlayout2.setVisibility(0);
        linearlayout3.setVisibility(8);
_L7:
        return i + 1;
_L14:
        j++;
        if (true) goto _L16; else goto _L15
_L15:
    }

    private static int b(ProductPageModel productpagemodel, LinearLayout linearlayout, android.view.View.OnClickListener onclicklistener, int i, Activity activity)
    {
        InstallCareWidget installcarewidget;
        installcarewidget = (InstallCareWidget)linearlayout.findViewById(InstallCareWidget.Id);
        if (productpagemodel.getInstallCare() == null)
        {
            break MISSING_BLOCK_LABEL_140;
        }
        if (installcarewidget != null)
        {
            break MISSING_BLOCK_LABEL_85;
        }
        InstallCareWidget installcarewidget1 = new InstallCareWidget(activity.getApplicationContext(), productpagemodel.getInstallCare().getItems(), onclicklistener, productpagemodel.getInstallCare().getTimeStamp(), PageTypeUtils.ProductPage);
        if (linearlayout.getChildCount() >= i + 1)
        {
            linearlayout.addView(installcarewidget1, i);
            break MISSING_BLOCK_LABEL_152;
        }
        int j;
        try
        {
            linearlayout.addView(installcarewidget1);
        }
        catch (Exception exception) { }
        break MISSING_BLOCK_LABEL_152;
        j = linearlayout.indexOfChild(installcarewidget);
        linearlayout.removeView(installcarewidget);
        linearlayout.addView(new InstallCareWidget(activity.getApplicationContext(), productpagemodel.getInstallCare().getItems(), onclicklistener, productpagemodel.getInstallCare().getTimeStamp(), PageTypeUtils.ProductPage), j);
        return j + 1;
        if (installcarewidget == null)
        {
            break MISSING_BLOCK_LABEL_152;
        }
        installcarewidget.setVisibility(8);
        return i + 1;
    }

    private static int b(ProductPageModel productpagemodel, LinearLayout linearlayout, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener, int i)
    {
        LinearLayout linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a01b8);
        if (linearlayout1 != null) goto _L2; else goto _L1
_L1:
        linearlayout1 = (LinearLayout)((LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater")).inflate(0x7f03008e, null);
        if (linearlayout.getChildCount() < i + 1) goto _L4; else goto _L3
_L3:
        linearlayout.addView(linearlayout1, i);
_L12:
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout1.getLayoutParams();
        layoutparams.topMargin = ScreenMathUtils.dpToPx(10, linearlayout.getContext());
        linearlayout1.setLayoutParams(layoutparams);
_L2:
        ProductPageModel aproductpagemodel[] = productpagemodel.getOtherVariants();
        if (aproductpagemodel == null) goto _L6; else goto _L5
_L5:
        if (aproductpagemodel.length <= 0) goto _L6; else goto _L7
_L7:
        LinearLayout linearlayout2;
        LayoutInflater layoutinflater;
        int j;
        linearlayout1.setVisibility(0);
        linearlayout2 = (LinearLayout)linearlayout1.findViewById(0x7f0a01b9);
        linearlayout2.removeAllViews();
        layoutinflater = (LayoutInflater)linearlayout.getContext().getSystemService("layout_inflater");
        j = aproductpagemodel.length;
        int k;
        int i1;
        k = 0;
        i1 = 0;
_L10:
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        ProductPageModel productpagemodel1 = aproductpagemodel[k];
        LinearLayout linearlayout3 = (LinearLayout)layoutinflater.inflate(0x7f03008d, null);
        linearlayout3.setTag((new StringBuilder("on_click_other_variants/")).append(productpagemodel1.getProductId()).toString());
        int j1 = ScreenMathUtils.dpToPx(1, linearlayout.getContext());
        linearlayout3.setPadding(j1, j1, j1, j1);
        linearlayout3.setBackgroundResource(0x7f0200f6);
        linearlayout3.setOnClickListener(onclicklistener);
        ((NetworkImageView)linearlayout3.findViewById(0x7f0a01b4)).setImageUrl(productpagemodel1.getPrimaryImageUrl(), imageloader);
        ((TextView)linearlayout3.findViewById(0x7f0a01b5)).setText(productpagemodel1.getMainTitle());
        ((TextView)linearlayout3.findViewById(0x7f0a01b6)).setText(productpagemodel1.getSubTitle());
        ((TextView)linearlayout3.findViewById(0x7f0a01b7)).setText((new StringBuilder("Rs. ")).append(productpagemodel1.getFsp()).toString());
        linearlayout2.addView(linearlayout3);
        int k1 = i1 + 1;
        if (k1 < aproductpagemodel.length)
        {
            View view = new View(linearlayout.getContext());
            view.setBackgroundDrawable(linearlayout.getContext().getResources().getDrawable(0x7f020158));
            android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(2, linearlayout.getContext()));
            layoutparams1.setMargins(ScreenMathUtils.dpToPx(10, linearlayout.getContext()), 0, ScreenMathUtils.dpToPx(10, linearlayout.getContext()), 0);
            view.setLayoutParams(layoutparams1);
            linearlayout2.addView(view);
        }
          goto _L8
_L4:
        linearlayout.addView(linearlayout1);
        continue; /* Loop/switch isn't completed */
_L6:
        try
        {
            linearlayout1.setVisibility(8);
        }
        catch (Exception exception) { }
        break; /* Loop/switch isn't completed */
_L8:
        k++;
        i1 = k1;
        if (true) goto _L10; else goto _L9
_L9:
        return i + 1;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public static void buildBottomBar(ProductPageModel productpagemodel, View view, android.view.View.OnClickListener onclicklistener)
    {
        if (productpagemodel == null)
        {
            break MISSING_BLOCK_LABEL_11;
        }
        if (!productpagemodel.isEbook())
        {
            break MISSING_BLOCK_LABEL_18;
        }
        view.setVisibility(8);
        return;
        MarketPlaceSeller marketplaceseller;
        view.setVisibility(0);
        marketplaceseller = productpagemodel.getPreferredSeller();
        if (marketplaceseller == null)
        {
            try
            {
                view.setVisibility(8);
                return;
            }
            catch (Exception exception)
            {
                FkLogger.printStackTrace(exception);
            }
            return;
        }
        View view1;
        View view2;
        CustomRobotoRegularTextView customrobotoregulartextview1;
        view1 = view.findViewById(0x7f0a0178);
        view2 = view.findViewById(0x7f0a017b);
        CustomRobotoRegularTextView customrobotoregulartextview = (CustomRobotoRegularTextView)view.findViewById(0x7f0a0175);
        customrobotoregulartextview.setTag((new StringBuilder("on_click_share_product:")).append(productpagemodel.getProductId()).toString());
        customrobotoregulartextview.setOnClickListener(onclicklistener);
        customrobotoregulartextview1 = (CustomRobotoRegularTextView)view.findViewById(0x7f0a0179);
        customrobotoregulartextview1.setOnClickListener(onclicklistener);
        customrobotoregulartextview1.setContentDescription("add_To_Cart");
        ((TextView)view.findViewById(0x7f0a017e)).setVisibility(8);
        if (!CartHandler.isCartItem(productpagemodel.getProductId()))
        {
            break MISSING_BLOCK_LABEL_253;
        }
        customrobotoregulartextview1.setText("GoTo Cart");
_L1:
        CustomRobotoMediumTextView customrobotomediumtextview;
        customrobotomediumtextview = (CustomRobotoMediumTextView)view.findViewById(0x7f0a017a);
        customrobotomediumtextview.setOnClickListener(onclicklistener);
        customrobotomediumtextview.setContentDescription("buy_Now");
        if (marketplaceseller == null)
        {
            break MISSING_BLOCK_LABEL_310;
        }
        if (!marketplaceseller.getAvailiabilityDetails().isEnableCheckout())
        {
            break MISSING_BLOCK_LABEL_310;
        }
        view.setVisibility(0);
        view1.setVisibility(0);
        view2.setVisibility(8);
        customrobotomediumtextview.setEnabled(true);
        if (marketplaceseller.getAvailiabilityDetails().isShowBuyButton())
        {
            customrobotomediumtextview.setText("BUY NOW");
            return;
        }
        break MISSING_BLOCK_LABEL_270;
        customrobotoregulartextview1.setText("+Cart");
        customrobotoregulartextview1.setEnabled(true);
          goto _L1
        if (marketplaceseller.getAvailiabilityDetails().isShowBookNow())
        {
            customrobotomediumtextview.setText("BOOK NOW");
            return;
        }
        if (marketplaceseller.getAvailiabilityDetails().isShowPreorder())
        {
            customrobotomediumtextview.setText("PRE ORDER");
            return;
        }
        break MISSING_BLOCK_LABEL_432;
        if (marketplaceseller == null)
        {
            break MISSING_BLOCK_LABEL_426;
        }
        if (marketplaceseller.getAvailiabilityDetails().isShowNotifyme())
        {
            view.setVisibility(0);
            view1.setVisibility(8);
            view2.setVisibility(0);
            Button button = (Button)view2.findViewById(0x7f0a017d);
            button.setOnClickListener(onclicklistener);
            EditText edittext = (EditText)view2.findViewById(0x7f0a017c);
            edittext.setOnEditorActionListener(new m(button));
            if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
            {
                String s = FlipkartPreferenceManager.instance().getUserEmail();
                if (!StringUtils.isNullOrEmpty(s))
                {
                    edittext.setText(s);
                    return;
                }
            }
            break MISSING_BLOCK_LABEL_432;
        }
        view.setVisibility(8);
    }

    public static void buildBottomBar(ProductInfo productinfo, View view, android.view.View.OnClickListener onclicklistener)
    {
        if (productinfo == null)
        {
            break MISSING_BLOCK_LABEL_11;
        }
        if (!productinfo.isEbook())
        {
            break MISSING_BLOCK_LABEL_18;
        }
        view.setVisibility(8);
        return;
        MarketPlaceSeller marketplaceseller;
        view.setVisibility(0);
        marketplaceseller = productinfo.getPreferredSeller();
        if (marketplaceseller == null)
        {
            try
            {
                view.setVisibility(8);
                return;
            }
            catch (Exception exception)
            {
                FkLogger.printStackTrace(exception);
            }
            return;
        }
        View view1;
        View view2;
        CustomRobotoRegularTextView customrobotoregulartextview1;
        view1 = view.findViewById(0x7f0a0178);
        view2 = view.findViewById(0x7f0a017b);
        CustomRobotoRegularTextView customrobotoregulartextview = (CustomRobotoRegularTextView)view.findViewById(0x7f0a0175);
        customrobotoregulartextview.setTag("on_click_share_product");
        customrobotoregulartextview.setOnClickListener(onclicklistener);
        customrobotoregulartextview1 = (CustomRobotoRegularTextView)view.findViewById(0x7f0a0179);
        customrobotoregulartextview1.setOnClickListener(onclicklistener);
        customrobotoregulartextview1.setContentDescription("add_To_Cart");
        ((TextView)view.findViewById(0x7f0a017e)).setVisibility(8);
        if (!CartHandler.isCartItem(productinfo.getProductId()))
        {
            break MISSING_BLOCK_LABEL_244;
        }
        customrobotoregulartextview1.setText("GoTo Cart");
_L1:
        CustomRobotoMediumTextView customrobotomediumtextview;
        customrobotomediumtextview = (CustomRobotoMediumTextView)view.findViewById(0x7f0a017a);
        customrobotomediumtextview.setOnClickListener(onclicklistener);
        customrobotomediumtextview.setContentDescription("buy_Now");
        if (marketplaceseller == null)
        {
            break MISSING_BLOCK_LABEL_418;
        }
        if (marketplaceseller.getAvailabilityDetails() == null)
        {
            break MISSING_BLOCK_LABEL_418;
        }
        if (!marketplaceseller.getAvailabilityDetails().isEnableCheckout())
        {
            break MISSING_BLOCK_LABEL_301;
        }
        view.setVisibility(0);
        view1.setVisibility(0);
        view2.setVisibility(8);
        customrobotomediumtextview.setEnabled(true);
        if (marketplaceseller.getAvailiabilityDetails().isShowBuyButton())
        {
            customrobotomediumtextview.setText("BUY NOW");
            return;
        }
        break MISSING_BLOCK_LABEL_261;
        customrobotoregulartextview1.setText("+Cart");
        customrobotoregulartextview1.setEnabled(true);
          goto _L1
        if (marketplaceseller.getAvailiabilityDetails().isShowBookNow())
        {
            customrobotomediumtextview.setText("BOOK NOW");
            return;
        }
        if (marketplaceseller.getAvailiabilityDetails().isShowPreorder())
        {
            customrobotomediumtextview.setText("PRE ORDER");
            return;
        }
        break MISSING_BLOCK_LABEL_418;
        if (marketplaceseller.getAvailiabilityDetails().isShowNotifyme())
        {
            view.setVisibility(0);
            view1.setVisibility(8);
            view2.setVisibility(0);
            Button button = (Button)view2.findViewById(0x7f0a017d);
            button.setOnClickListener(onclicklistener);
            EditText edittext = (EditText)view2.findViewById(0x7f0a017c);
            edittext.setOnEditorActionListener(new l(button));
            if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
            {
                String s = FlipkartPreferenceManager.instance().getUserEmail();
                if (!StringUtils.isNullOrEmpty(s))
                {
                    edittext.setText(s);
                    return;
                }
            }
            break MISSING_BLOCK_LABEL_418;
        }
        view.setVisibility(8);
    }

    public static void buildProductPage(ProductPageModel productpagemodel, View view, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener, android.widget.AdapterView.OnItemSelectedListener onitemselectedlistener, boolean flag, int i, Activity activity, 
            String s, FkProductListContext fkproductlistcontext)
    {
        if (productpagemodel != null && !StringUtils.isNullOrEmpty(productpagemodel.getProductId())) goto _L2; else goto _L1
_L1:
        return;
_L2:
        System.currentTimeMillis();
        if (view == null) goto _L1; else goto _L3
_L3:
        if (view instanceof ScrollView)
        {
            ScrollView scrollview = (ScrollView)view;
            scrollview.setContentDescription(productpagemodel.getProductId());
            if (scrollview.getTag() != null && (scrollview.getTag() instanceof ProductViewTag) && !((ProductViewTag)scrollview.getTag()).getProductId().equals(productpagemodel.getProductId()))
            {
                scrollview.scrollTo(0, 0);
            }
        }
        Object obj;
        ProductViewTag productviewtag;
        boolean flag1;
        String s1;
        LinearLayout linearlayout;
        try
        {
            obj = view.getTag();
        }
        catch (Exception exception)
        {
            return;
        }
        productviewtag = null;
        if (obj == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag1 = view.getTag() instanceof ProductViewTag;
        productviewtag = null;
        if (!flag1)
        {
            break; /* Loop/switch isn't completed */
        }
        productviewtag = (ProductViewTag)view.getTag();
        s1 = productviewtag.getProductId();
        if (productpagemodel.getProductId().equals(s1) && productpagemodel.getLastUpdatedTimeStamp() <= productviewtag.getTs() && productviewtag.a == flag) goto _L1; else goto _L4
_L4:
        if (productviewtag != null)
        {
            break MISSING_BLOCK_LABEL_188;
        }
        productviewtag = new ProductViewTag();
        productviewtag.setProductId(productpagemodel.getProductId());
        productviewtag.setTs(productpagemodel.getLastUpdatedTimeStamp());
        productviewtag.setInfoLevel(productpagemodel.getInfoLevel());
        productviewtag.setSizeSelected(flag);
        view.setTag(productviewtag);
        linearlayout = ((ViewPortScrollView)view).getRootLayout();
        a(productpagemodel, linearlayout, onclicklistener, productpagemodel.isEbook());
        a(productpagemodel, linearlayout, imageloader, onclicklistener, i);
        c(productpagemodel, linearlayout, onclicklistener, b(productpagemodel, linearlayout, onclicklistener, b(productpagemodel, linearlayout, imageloader, onclicklistener, a(productpagemodel, linearlayout, onclicklistener, b(productpagemodel, linearlayout, onclicklistener, a(productpagemodel, linearlayout, a(imageloader, productpagemodel, linearlayout, onclicklistener, a(productpagemodel, linearlayout, onclicklistener, b(productpagemodel, linearlayout, a(productpagemodel, linearlayout, a(productpagemodel, linearlayout, imageloader, onclicklistener, onitemselectedlistener, flag, 2, fkproductlistcontext, activity), onclicklistener), onclicklistener), activity)))))), activity), activity);
        return;
    }

    public static void buildProductPageSubPagesPriceBar(ProductPageModel productpagemodel, View view)
    {
        view.findViewById(0x7f0a01c5).setVisibility(0);
        TextView textview = (TextView)view.findViewById(0x7f0a0150);
        textview.setText((new StringBuilder("Rs.")).append(productpagemodel.getFsp()).toString());
        TextView textview1 = (TextView)view.findViewById(0x7f0a01c6);
        TextView textview2 = (TextView)view.findViewById(0x7f0a01be);
        if (productpagemodel.isMrpVisible())
        {
            textview1.setPaintFlags(0x10 | textview1.getPaintFlags());
            textview1.setText((new StringBuilder("Rs.")).append(productpagemodel.getMrp()).toString());
            if (!productpagemodel.getDiscount().equals("0"))
            {
                textview2.setText((new StringBuilder()).append(productpagemodel.getDiscount()).append("% OFF").toString());
            } else
            {
                textview2.setText("");
            }
        }
        if (productpagemodel.isFspShown())
        {
            textview.setText((new StringBuilder("Rs. ")).append(productpagemodel.getFsp()).toString());
            return;
        }
        if (productpagemodel.getFsp().equalsIgnoreCase("Free"))
        {
            textview.setText(productpagemodel.getFsp());
            return;
        }
        if (productpagemodel.isCheckoutEnabled())
        {
            textview.setText((new StringBuilder("Rs.")).append(productpagemodel.getFsp()).toString());
            return;
        } else
        {
            textview.setText("Price: Not Available");
            return;
        }
    }

    public static void buildProductPageSubPagesTiltBar(String s, String s1, View view, android.view.View.OnClickListener onclicklistener, boolean flag)
    {
        View view1 = view.findViewById(0x7f0a0194);
        view1.setVisibility(0);
        view1.setOnClickListener(onclicklistener);
        TextView textview = (TextView)view1.findViewById(0x7f0a0201);
        textview.setText(s);
        TextView textview1 = (TextView)view1.findViewById(0x7f0a0202);
        if (StringUtils.isNullOrEmpty(s1))
        {
            textview1.setVisibility(8);
            textview.setPadding(0, ScreenMathUtils.dpToPx(10, view.getContext()), 0, ScreenMathUtils.dpToPx(10, view.getContext()));
            return;
        }
        textview1.setVisibility(0);
        if (flag)
        {
            textview1.setText((new StringBuilder("Ebook ")).append(s1).toString());
            return;
        } else
        {
            textview1.setText(s1);
            return;
        }
    }

    public static void buildProductPageUgc(ProductInfo productinfo, View view, android.view.View.OnClickListener onclicklistener)
    {
        TextView textview2;
        TextView textview4;
        int i;
        String s = productinfo.getMainTitle();
        String s1 = productinfo.getSubTitle();
        boolean flag = productinfo.isEbook();
        View view1 = view.findViewById(0x7f0a0194);
        view1.setOnClickListener(onclicklistener);
        TextView textview = (TextView)view1.findViewById(0x7f0a0201);
        textview.setText(s);
        textview.setContentDescription("product_page_title_main_title");
        TextView textview1 = (TextView)view1.findViewById(0x7f0a0202);
        TextView textview3;
        double d;
        double d1;
        int j;
        if (StringUtils.isNullOrEmpty(s1))
        {
            textview1.setVisibility(8);
            textview.setPadding(ScreenMathUtils.dpToPx(10, view.getContext()), ScreenMathUtils.dpToPx(10, view.getContext()), 0, ScreenMathUtils.dpToPx(10, view.getContext()));
        } else
        {
            textview1.setVisibility(0);
            if (flag)
            {
                textview1.setText((new StringBuilder("Ebook ")).append(s1).toString());
            } else
            {
                textview1.setText(s1);
            }
        }
        view.findViewById(0x7f0a01c5).setVisibility(0);
        textview2 = (TextView)view.findViewById(0x7f0a0150);
        textview2.setText((new StringBuilder("Rs.")).append(productinfo.getSellingPrice()).toString());
        textview3 = (TextView)view.findViewById(0x7f0a01c6);
        textview4 = (TextView)view.findViewById(0x7f0a01be);
        if (!productinfo.isShowMrp()) goto _L2; else goto _L1
_L1:
        textview3.setPaintFlags(0x10 | textview3.getPaintFlags());
        textview3.setText((new StringBuilder("Rs.")).append(productinfo.getMrp()).toString());
        d = productinfo.getSellingPrice();
        d1 = productinfo.getMrp();
        j = MiscUtils.roundoffDecimal((float)(100D * ((d1 - d) / d1)));
        i = j;
_L3:
        Exception exception;
        if (i != 0)
        {
            textview4.setText((new StringBuilder()).append(i).append("% OFF").toString());
        } else
        {
            textview4.setText("");
        }
_L2:
        if (productinfo.getSellingPrice() != 0)
        {
            textview2.setText((new StringBuilder("Rs. ")).append(productinfo.getSellingPrice()).toString());
        } else
        if (productinfo.isEnableCheckout())
        {
            textview2.setText((new StringBuilder("Rs.")).append(productinfo.getSellingPrice()).toString());
        } else
        {
            textview2.setText("Price: Not Available");
        }
        buildProductPageUgcRatings(productinfo, view);
        return;
        exception;
        i = 0;
          goto _L3
    }

    public static void buildProductPageUgcRatings(ProductInfo productinfo, View view)
    {
        if (productinfo != null)
        {
            RatingBar ratingbar = (RatingBar)view.findViewById(0x7f0a01cd);
            double d = productinfo.getUgc().getRatingObj().getOverallRating();
            ratingbar.setRating((float)d);
            ((TextView)view.findViewById(0x7f0a01d9)).setText((new StringBuilder()).append((new DecimalFormat("#.#")).format(d)).append("/5.0").toString());
            TextView textview = (TextView)view.findViewById(0x7f0a01da);
            long l1 = productinfo.getUgc().getRatingObj().getTotalRatingCount();
            if (l1 <= 1L)
            {
                textview.setText((new StringBuilder("- Based on ")).append(l1).append(" Rating").toString());
            } else
            {
                textview.setText((new StringBuilder("- Based on ")).append(l1).append(" Ratings").toString());
            }
            buildProgressBar(productinfo.getUgc().getRatingObj().getRatingBreakupCount(), view, l1);
        }
    }

    public static void buildProgressBar(ArrayList arraylist, View view, long l1)
    {
        LayoutInflater layoutinflater = (LayoutInflater)view.getContext().getSystemService("layout_inflater");
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0a01db);
        linearlayout.removeAllViews();
        if (l1 == 0L || arraylist == null || arraylist.size() != 5)
        {
            linearlayout.setVisibility(8);
        } else
        {
            int i = 0;
            while (i < arraylist.size()) 
            {
                LinearLayout linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f0300ab, null);
                TextView textview = (TextView)linearlayout1.findViewById(0x7f0a0209);
                TextView textview1 = (TextView)linearlayout1.findViewById(0x7f0a020b);
                ProgressBar progressbar = (ProgressBar)linearlayout1.findViewById(0x7f0a020a);
                textview.setText(Integer.toString(arraylist.size() - i));
                long l2 = ((Long)arraylist.get(-1 + (arraylist.size() - i))).longValue();
                progressbar.setProgress((int)((100L * l2) / l1));
                if (l2 <= 1L)
                {
                    textview1.setText((new StringBuilder()).append(l2).append(" User").toString());
                } else
                {
                    textview1.setText((new StringBuilder()).append(l2).append(" Users").toString());
                }
                linearlayout.addView(linearlayout1);
                i++;
            }
        }
    }

    public static View buildSwatchArrowView(Context context, int i, int j, int k)
    {
        RelativeLayout relativelayout = new RelativeLayout(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        layoutparams.setMargins(ScreenMathUtils.dpToPx(10, context), 0, ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(10, context));
        relativelayout.setLayoutParams(layoutparams);
        View view = new View(context);
        android.widget.RelativeLayout.LayoutParams layoutparams1 = new android.widget.RelativeLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(1, context));
        layoutparams1.addRule(12, 1);
        view.setLayoutParams(layoutparams1);
        view.setBackgroundColor(0xb4b4b4b4);
        relativelayout.addView(view);
        View view1 = new View(context);
        android.widget.RelativeLayout.LayoutParams layoutparams2 = new android.widget.RelativeLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(8, context));
        if (i > 1)
        {
            if (j == 0)
            {
                layoutparams2.addRule(9);
                layoutparams2.setMargins(0, 0, ScreenMathUtils.dpToPx(i * 85, context), 0);
            } else
            {
                layoutparams2.addRule(11);
                layoutparams2.setMargins(ScreenMathUtils.dpToPx(i * 85, context), 0, 0, 0);
            }
        }
        view1.setBackgroundDrawable(context.getResources().getDrawable(0x7f02016c));
        view1.setLayoutParams(layoutparams2);
        relativelayout.addView(view1);
        return relativelayout;
    }

    public static View buildSwatchItemsView(Context context, com.flipkart.android.fragments.model.ProductPageModel.SwatchModel swatchmodel, android.view.View.OnClickListener onclicklistener, ImageLoader imageloader, android.widget.AdapterView.OnItemSelectedListener onitemselectedlistener, boolean flag, String s, List list, 
            List list1, AnalyticData analyticdata)
    {
        LayoutInflater layoutinflater = (LayoutInflater)context.getSystemService("layout_inflater");
        View view = layoutinflater.inflate(0x7f0300a2, null);
        if (swatchmodel != null)
        {
            ArrayList arraylist = swatchmodel.getSwaatchValues();
            int i = swatchmodel.getType();
            if (view != null)
            {
                TextView textview = (TextView)view.findViewById(0x7f0a01fd);
                View view1 = view.findViewById(0x7f0a01fc);
                HorizontalScrollView horizontalscrollview = (HorizontalScrollView)view.findViewById(0x7f0a01fa);
                if (i == 2)
                {
                    if (!StringUtils.isNullOrEmpty(s))
                    {
                        textview.setVisibility(0);
                        view1.setVisibility(0);
                        textview.setOnClickListener(onclicklistener);
                        Spinner spinner;
                        if (s.contains("http://flipkart.com"))
                        {
                            textview.setTag((new StringBuilder("on_click_size_chart")).append(s).toString());
                        } else
                        {
                            textview.setTag((new StringBuilder("on_click_size_chart")).append(GsonRequest.BASE_WEB_URL).append(s).toString());
                        }
                        ((android.widget.RelativeLayout.LayoutParams)horizontalscrollview.getLayoutParams()).setMargins(0, 0, ScreenMathUtils.dpToPx(60, context), 0);
                    } else
                    {
                        textview.setVisibility(8);
                        view1.setVisibility(8);
                        ((android.widget.RelativeLayout.LayoutParams)horizontalscrollview.getLayoutParams()).setMargins(0, 0, 0, 0);
                    }
                } else
                if (i == 1)
                {
                    textview.setVisibility(8);
                    view1.setVisibility(8);
                    ((android.widget.RelativeLayout.LayoutParams)horizontalscrollview.getLayoutParams()).setMargins(0, 0, 0, 0);
                }
                if (i == 3)
                {
                    ((RelativeLayout)view).removeAllViews();
                    spinner = (Spinner)layoutinflater.inflate(0x7f0300a1, null);
                    ((RelativeLayout)view).addView(spinner, new android.widget.RelativeLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(40, context)));
                    spinner.setAdapter(a(context, 0x1090009, arraylist, swatchmodel.getTitle()));
                    spinner.setSelection(swatchmodel.getCurrentIndex());
                    spinner.setOnItemSelectedListener(onitemselectedlistener);
                } else
                {
                    LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0a01fb);
                    Iterator iterator = arraylist.iterator();
                    int j = 0;
                    while (iterator.hasNext()) 
                    {
                        com.flipkart.android.fragments.model.ProductPageModel.SwatchValueModel swatchvaluemodel = (com.flipkart.android.fragments.model.ProductPageModel.SwatchValueModel)iterator.next();
                        if (swatchvaluemodel.isAvailable())
                        {
                            if (list != null && list.size() == 1 && list1 != null)
                            {
                                for (int j1 = 0; j1 < list1.size(); j1++)
                                {
                                    if (swatchvaluemodel.getItemId().equals(list1.get(j1)))
                                    {
                                        ((HomeFragmentHolderActivity)context).addToCart(swatchvaluemodel.getItemId(), null, null, swatchmodel.getTitle(), null, null, null, AddCartLocation.ProductListPage, (String)list.get(0), analyticdata);
                                        return null;
                                    }
                                }

                            }
                            if (i == 2)
                            {
                                TextView textview1 = (TextView)layoutinflater.inflate(0x7f0300a4, null);
                                textview1.setContentDescription("selectSize");
                                android.widget.LinearLayout.LayoutParams layoutparams1;
                                int i1;
                                if (j == swatchmodel.getCurrentIndex() && flag)
                                {
                                    textview1.setBackgroundResource(0x7f02016b);
                                    textview1.setOnClickListener(onclicklistener);
                                } else
                                {
                                    textview1.setBackgroundResource(0x7f02016a);
                                    textview1.setOnClickListener(onclicklistener);
                                }
                                textview1.setText(swatchvaluemodel.getText());
                                linearlayout.addView(textview1);
                                layoutparams1 = (android.widget.LinearLayout.LayoutParams)textview1.getLayoutParams();
                                i1 = ScreenMathUtils.dpToPx(10, context);
                                layoutparams1.setMargins(i1, i1, i1, i1);
                                textview1.setLayoutParams(layoutparams1);
                                textview1.setTag((new StringBuilder("on_click_item_swatch/")).append(swatchmodel.getTitle()).append("/").append(swatchvaluemodel.getItemId()).toString());
                            } else
                            if (i == 1)
                            {
                                LinearLayout linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f0300a3, null);
                                NetworkImageView networkimageview;
                                android.widget.LinearLayout.LayoutParams layoutparams;
                                int k;
                                if (j == swatchmodel.getCurrentIndex())
                                {
                                    linearlayout1.setOnClickListener(onclicklistener);
                                    linearlayout1.setBackgroundColor(context.getResources().getColor(0x7f090071));
                                } else
                                if (swatchvaluemodel.isAvailable())
                                {
                                    linearlayout1.setOnClickListener(onclicklistener);
                                }
                                networkimageview = (NetworkImageView)linearlayout1.findViewById(0x7f0a01fe);
                                networkimageview.setImageUrl(swatchvaluemodel.getImageUrl(), imageloader);
                                networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
                                linearlayout.addView(linearlayout1);
                                layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout1.getLayoutParams();
                                k = ScreenMathUtils.dpToPx(5, context);
                                layoutparams.setMargins(k, k, k, k);
                                linearlayout1.setLayoutParams(layoutparams);
                                linearlayout1.setTag((new StringBuilder("on_click_item_swatch/")).append(swatchmodel.getTitle()).append("/").append(swatchvaluemodel.getItemId()).toString());
                            }
                        }
                        j++;
                    }
                }
            }
        }
        return view;
    }

    private static int c(ProductPageModel productpagemodel, LinearLayout linearlayout, android.view.View.OnClickListener onclicklistener, int i, Activity activity)
    {
        if (productpagemodel == null)
        {
            break MISSING_BLOCK_LABEL_248;
        }
        String s;
        if (StringUtils.isNullOrEmpty(productpagemodel.getProductId()))
        {
            break MISSING_BLOCK_LABEL_248;
        }
        s = productpagemodel.getProductId();
        if ((RecommendationsWidget)linearlayout.findViewWithTag((new StringBuilder()).append(RecommendationsWidget.Id).append("/").append(s).toString()) != null)
        {
            return i + 1;
        }
        RecommendationsWidget recommendationswidget = (RecommendationsWidget)linearlayout.findViewById(RecommendationsWidget.Id);
        if (recommendationswidget == null)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        recommendationswidget.removeAllViews();
        linearlayout.removeView(recommendationswidget);
        RecommendationsWidget recommendationswidget1;
        ArrayList arraylist = new ArrayList();
        arraylist.add(s);
        recommendationswidget1 = new RecommendationsWidget(activity.getApplicationContext(), arraylist, onclicklistener, activity, "productPage");
        recommendationswidget1.setTag((new StringBuilder()).append(RecommendationsWidget.Id).append("/").append(s).toString());
        recommendationswidget1.setId(RecommendationsWidget.Id);
        if (linearlayout.getChildCount() < i + 1) goto _L2; else goto _L1
_L1:
        linearlayout.addView(recommendationswidget1, i);
_L4:
        ViewPortScrollView viewportscrollview = (ViewPortScrollView)linearlayout.getParent();
        viewportscrollview.setiViewPortView(recommendationswidget1);
        if (viewportscrollview.isInViewPort(recommendationswidget1))
        {
            recommendationswidget1.downloadRecoData();
        }
        recommendationswidget1.setBackgroundDrawable(linearlayout.getResources().getDrawable(0x7f02011b));
        break MISSING_BLOCK_LABEL_248;
_L2:
        linearlayout.addView(recommendationswidget1);
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        return i + 1;
    }

    public static Drawable drawableFromUrl(String s)
    {
        HttpURLConnection httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection((new URL(s)).openConnection());
        httpurlconnection.connect();
        return new BitmapDrawable(BitmapFactoryInstrumentation.decodeStream(httpurlconnection.getInputStream()));
    }

    public static void updateBottomBarCartIcon(String s, View view)
    {
        CustomRobotoRegularTextView customrobotoregulartextview;
label0:
        {
            if (view != null)
            {
                customrobotoregulartextview = (CustomRobotoRegularTextView)view.findViewById(0x7f0a0179);
                if (customrobotoregulartextview != null && !StringUtils.isNullOrEmpty(s))
                {
                    if (!CartHandler.isCartItem(s))
                    {
                        break label0;
                    }
                    customrobotoregulartextview.setText("GoTo Cart");
                }
            }
            return;
        }
        customrobotoregulartextview.setText("+Cart");
        customrobotoregulartextview.setEnabled(true);
    }

    private class ProductViewTag
    {

        boolean a;
        private String b;
        private long c;
        private int d;

        public boolean equals(Object obj)
        {
            if (obj != null && (obj instanceof ProductViewTag))
            {
                ProductViewTag productviewtag = (ProductViewTag)obj;
                if (b.equals(productviewtag.getProductId()))
                {
                    return true;
                }
            }
            return false;
        }

        public int getInfoLevel()
        {
            return d;
        }

        public String getProductId()
        {
            return b;
        }

        public long getTs()
        {
            return c;
        }

        public boolean isSizeSelected()
        {
            return a;
        }

        public void setInfoLevel(int i)
        {
            d = i;
        }

        public void setProductId(String s)
        {
            b = s;
        }

        public void setSizeSelected(boolean flag)
        {
            a = flag;
        }

        public void setTs(long l1)
        {
            c = l1;
        }

        public ProductViewTag()
        {
        }

        public ProductViewTag(String s)
        {
            b = s;
        }
    }

}
