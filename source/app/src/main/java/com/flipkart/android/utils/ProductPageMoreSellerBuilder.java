// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.fragments.model.ProductPageMoreSellerModel;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils, ScreenMathUtils, q, FbfUtils, 
//            r, s

public class ProductPageMoreSellerBuilder
{

    public ProductPageMoreSellerBuilder()
    {
    }

    public static void buildProductPageMoreSeller(ProductPageMoreSellerModel productpagemoresellermodel, View view, android.view.View.OnClickListener onclicklistener, Activity activity)
    {
        if (!productpagemoresellermodel.isTitleVisible()) goto _L2; else goto _L1
_L1:
        LinearLayout linearlayout;
        EditText edittext;
        LinearLayout linearlayout2;
        int j;
        int k;
        String s1 = productpagemoresellermodel.getMainTitle();
        String s2 = productpagemoresellermodel.getSubTitle();
        View view1 = view.findViewById(0x7f0a0194);
        view1.setVisibility(0);
        view1.setOnClickListener(onclicklistener);
        TextView textview = (TextView)view1.findViewById(0x7f0a0201);
        textview.setText(s1);
        TextView textview1 = (TextView)view1.findViewById(0x7f0a0202);
        LinearLayout linearlayout1;
        com.flipkart.android.fragments.model.ProductPageMoreSellerModel.MoreSellerModel amoresellermodel[];
        int i;
        boolean flag;
        int l;
        int i1;
        boolean flag2;
        TextView textview7;
        com.flipkart.android.fragments.model.ProductPageMoreSellerModel.MoreSellerModel moresellermodel1;
        if (StringUtils.isNullOrEmpty(s2))
        {
            textview1.setVisibility(8);
            textview.setPadding(0, ScreenMathUtils.dpToPx(10, view.getContext()), 0, ScreenMathUtils.dpToPx(10, view.getContext()));
        } else
        {
            textview1.setText(s2);
        }
        if (!productpagemoresellermodel.isMoreSellerVisible()) goto _L2; else goto _L3
_L3:
        linearlayout = (LinearLayout)view.findViewById(0x7f0a019d);
        linearlayout1 = (LinearLayout)view.findViewById(0x7f0a0196);
        edittext = (EditText)view.findViewById(0x7f0a0198);
        edittext.setOnEditorActionListener(new q((Button)view.findViewById(0x7f0a0199)));
        linearlayout2 = (LinearLayout)view.findViewById(0x7f0a019a);
        linearlayout1.setVisibility(8);
        linearlayout2.setVisibility(0);
        amoresellermodel = productpagemoresellermodel.getMoreSeller();
        i = amoresellermodel.length;
        j = 0;
_L13:
        flag = false;
        if (j >= i) goto _L5; else goto _L4
_L4:
        moresellermodel1 = amoresellermodel[j];
        FkLogger.debug("loc", (new StringBuilder("seller details: ")).append(moresellermodel1.getDisplayName()).append("deliv = ").append(moresellermodel1.getPinCodeServiceability()).toString());
        if (!moresellermodel1.getPinCodeServiceability()) goto _L7; else goto _L6
_L6:
        flag = true;
_L5:
        int j1;
        FkLogger.debug("call", (new StringBuilder("delivery status ")).append(flag).toString());
        LayoutInflater layoutinflater;
        boolean flag1;
        com.flipkart.android.fragments.model.ProductPageMoreSellerModel.MoreSellerModel moresellermodel;
        if (flag)
        {
            textview7 = (TextView)linearlayout2.findViewById(0x7f0a019b);
            textview7.setText((new StringBuilder("These sellers are available for your pin code ")).append(FlipkartPreferenceManager.instance().getUserPinCode()).toString());
            ((TextView)linearlayout2.findViewById(0x7f0a019c)).setVisibility(0);
            textview7.setVisibility(0);
        } else
        {
            TextView textview2 = (TextView)linearlayout2.findViewById(0x7f0a019b);
            textview2.setText((new StringBuilder("No sellers are available for your pin code ")).append(FlipkartPreferenceManager.instance().getUserPinCode()).toString());
            ((TextView)linearlayout2.findViewById(0x7f0a019c)).setVisibility(0);
            textview2.setVisibility(0);
        }
        layoutinflater = (LayoutInflater)view.getContext().getSystemService("layout_inflater");
        View view2;
        TextView textview3;
        long l1;
        LinearLayout linearlayout3;
        TextView textview4;
        SpannableString spannablestring;
        TextView textview5;
        LinearLayout linearlayout4;
        Button button;
        View view3;
        TextView textview6;
        int k1;
        LinearLayout linearlayout5;
        View view4;
        android.widget.LinearLayout.LayoutParams layoutparams;
        Exception exception1;
        if (FlipkartPreferenceManager.instance().getUserPinCode().equalsIgnoreCase(""))
        {
            linearlayout1.setVisibility(0);
            linearlayout2.setVisibility(8);
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        linearlayout.removeAllViews();
        k = 0;
        l = amoresellermodel.length;
        i1 = 0;
        if (i1 >= l) goto _L9; else goto _L8
_L8:
        moresellermodel = amoresellermodel[i1];
        if (flag1) goto _L11; else goto _L10
_L10:
        flag2 = moresellermodel.getPinCodeServiceability();
        if (flag2) goto _L11; else goto _L12
_L12:
        i1++;
        break MISSING_BLOCK_LABEL_423;
_L7:
        j++;
          goto _L13
_L11:
        j1 = k + 1;
        view2 = layoutinflater.inflate(0x7f03008a, null);
        ((LinearLayout)view2.findViewById(0x7f0a019e)).setOnClickListener(onclicklistener);
        ((TextView)view2.findViewById(0x7f0a019f)).setText(moresellermodel.getDisplayName());
        ((RatingBar)view2.findViewById(0x7f0a01a0)).setRating(moresellermodel.getAvgRatings());
        textview3 = (TextView)view2.findViewById(0x7f0a01a1);
        l1 = moresellermodel.getTotalRatings();
        if (l1 > 1L) goto _L15; else goto _L14
_L14:
        textview3.setText((new StringBuilder()).append(l1).append(" Rating").toString());
_L27:
        linearlayout3 = (LinearLayout)view2.findViewById(0x7f0a01a2);
        if (StringUtils.isNullOrEmpty(moresellermodel.getFbf()) || !moresellermodel.getFbf().equalsIgnoreCase("FBF")) goto _L17; else goto _L16
_L16:
        FbfUtils.showFbf(linearlayout3, FlipkartApplication.getImageLoader(), onclicklistener);
_L28:
        ((TextView)view2.findViewById(0x7f0a01a7)).setText((new StringBuilder("Rs.")).append(moresellermodel.getSellingPrice()).append(" ").toString());
        textview4 = (TextView)view2.findViewById(0x7f0a01a8);
        textview4.setClickable(false);
        if (moresellermodel.getShippingCharge() <= 0) goto _L19; else goto _L18
_L18:
        textview4.setText((new StringBuilder("+ Rs. ")).append(moresellermodel.getShippingCharge()).append(" Delivery charge").toString());
_L30:
        ((TextView)view2.findViewById(0x7f0a01a9)).setText(moresellermodel.getStatus());
        ((TextView)view2.findViewById(0x7f0a01aa)).setText(moresellermodel.getShippingText());
        textview5 = (TextView)view2.findViewById(0x7f0a01a5);
        if (!moresellermodel.isSellerReturnPolicyShown()) goto _L21; else goto _L20
_L20:
        textview5.setText(moresellermodel.getReplacementPolicy());
_L31:
        linearlayout4 = (LinearLayout)view2.findViewById(0x7f0a01ab);
        if (!moresellermodel.isOfferExisting()) goto _L23; else goto _L22
_L22:
        view3 = view2.findViewById(0x7f0a01ad);
        textview6 = (TextView)view2.findViewById(0x7f0a01ac);
        linearlayout4.removeAllViews();
        linearlayout4.setVisibility(0);
        if (moresellermodel.getOffersText().size() <= 1) goto _L25; else goto _L24
_L24:
        textview6.setText((new StringBuilder()).append(moresellermodel.getOffersText().size()).append(" Offers Available.").toString());
_L32:
        linearlayout4.addView(view3);
        linearlayout4.addView(textview6);
        k1 = 0;
_L26:
        if (k1 >= moresellermodel.getOffersText().size())
        {
            break; /* Loop/switch isn't completed */
        }
        linearlayout5 = (LinearLayout)layoutinflater.inflate(0x7f030074, null);
        ((TextView)linearlayout5.findViewById(0x7f0a0163)).setText((CharSequence)moresellermodel.getOffersText().get(k1));
        if (k1 != 0)
        {
            break MISSING_BLOCK_LABEL_1092;
        }
        view4 = linearlayout5.findViewById(0x7f0a0162);
        layoutparams = new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(1, view.getContext()));
        layoutparams.setMargins(0, ScreenMathUtils.dpToPx(5, view.getContext()), 0, ScreenMathUtils.dpToPx(5, view.getContext()));
        view4.setLayoutParams(layoutparams);
        linearlayout4.addView(linearlayout5);
        k1++;
        if (true) goto _L26; else goto _L23
_L15:
        textview3.setText((new StringBuilder()).append(l1).append(" Ratings").toString());
          goto _L27
_L17:
        linearlayout3.setVisibility(8);
          goto _L28
_L19:
        if (!StringUtils.isNullOrEmpty(FlipkartPreferenceManager.instance().getUserPinCode())) goto _L30; else goto _L29
_L29:
        spannablestring = new SpannableString("+ Delivery charge?");
        spannablestring.setSpan(new r(), 0, "+ Delivery charge?".length(), 18);
        textview4.setText(spannablestring);
        textview4.setClickable(true);
        textview4.setOnClickListener(new s(view, activity, edittext));
          goto _L30
_L21:
        textview5.setVisibility(8);
          goto _L31
_L25:
label0:
        {
            if (moresellermodel.getOffersText().size() != 1)
            {
                break label0;
            }
            textview6.setText((new StringBuilder()).append(moresellermodel.getOffersText().size()).append(" Offer Available.").toString());
        }
          goto _L32
        linearlayout4.setVisibility(8);
          goto _L32
_L23:
        button = (Button)view2.findViewById(0x7f0a01a6);
        button.setTag((new StringBuilder("buy_now/")).append(moresellermodel.getDisplayName()).toString());
        button.setOnClickListener(onclicklistener);
        view2.setTag("open_particular_seller_page");
        view2.setOnClickListener(onclicklistener);
        linearlayout.addView(view2);
        k = j1;
          goto _L12
_L9:
        if (k == 0 && !StringUtils.isNullOrEmpty(FlipkartPreferenceManager.instance().getUserPinCode()))
        {
            TrackingHelper.sendNoSellerAvailableForPinCode();
        }
_L2:
        return;
        exception1;
          goto _L12
        Exception exception;
        exception;
        k = j1;
          goto _L12
    }
}
