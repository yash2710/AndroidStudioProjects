// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.flipkart.android.fragments.model.SellerPageModel;
import java.text.DecimalFormat;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils

public class SellerPageBuilder
{

    public SellerPageBuilder()
    {
    }

    private static void a(SellerPageModel sellerpagemodel, View view)
    {
        LayoutInflater layoutinflater = (LayoutInflater)view.getContext().getSystemService("layout_inflater");
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0a01d7);
        ((LinearLayout)view.findViewById(0x7f0a0224)).setVisibility(0);
        com.flipkart.android.fragments.model.SellerPageModel.SellerUgcModel asellerugcmodel[] = sellerpagemodel.getSellerUgc();
        int i = asellerugcmodel.length;
        for (int j = 0; j < i; j++)
        {
            com.flipkart.android.fragments.model.SellerPageModel.SellerUgcModel sellerugcmodel = asellerugcmodel[j];
            LinearLayout linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f0300b5, null);
            TextView textview = (TextView)linearlayout1.findViewById(0x7f0a01cb);
            textview.setTextSize(2, 13F);
            TextView textview1 = (TextView)linearlayout1.findViewById(0x7f0a01cc);
            textview1.setTextSize(2, 14F);
            TextView textview2 = (TextView)linearlayout1.findViewById(0x7f0a01d2);
            textview2.setTextSize(2, 14F);
            RatingBar ratingbar = (RatingBar)linearlayout1.findViewById(0x7f0a01cd);
            textview.setText(sellerugcmodel.getUserName());
            textview2.setText(sellerugcmodel.getReview());
            textview1.setText(sellerugcmodel.getTime());
            ratingbar.setRating(sellerugcmodel.getRating());
            linearlayout.addView(linearlayout1);
        }

    }

    public static void buildSellerPage(SellerPageModel sellerpagemodel, View view, android.view.View.OnClickListener onclicklistener)
    {
        if (sellerpagemodel.isDisplayNamevisible())
        {
            String s = sellerpagemodel.getDisplayName();
            String s1 = sellerpagemodel.getBusinessName();
            View view1 = view.findViewById(0x7f0a021b);
            view1.setVisibility(0);
            ((TextView)view1.findViewById(0x7f0a0225)).setText(s);
            if (!StringUtils.isNullOrEmpty(s1))
            {
                TextView textview5 = (TextView)view1.findViewById(0x7f0a0226);
                textview5.setText(s1);
                textview5.setVisibility(0);
            }
            ((LinearLayout)view.findViewById(0x7f0a021c)).setVisibility(0);
            ((RatingBar)view.findViewById(0x7f0a01cd)).setRating(sellerpagemodel.getAvgRating());
            ((TextView)view.findViewById(0x7f0a01d9)).setText((new StringBuilder()).append((new DecimalFormat("#.#")).format(sellerpagemodel.getAvgRating())).append("/5.0").toString());
            TextView textview = (TextView)view.findViewById(0x7f0a01da);
            long l = sellerpagemodel.getTotalRatings();
            if (l <= 1L)
            {
                textview.setText((new StringBuilder("Based on ")).append(l).append(" Rating").toString());
            } else
            {
                textview.setText((new StringBuilder("Based on ")).append(l).append(" Ratings").toString());
            }
            if (sellerpagemodel.isProgressBarVisible())
            {
                LayoutInflater layoutinflater = (LayoutInflater)view.getContext().getSystemService("layout_inflater");
                LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0a01db);
                linearlayout.setVisibility(0);
                int i = 5;
                while (i > 0) 
                {
                    LinearLayout linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f0300b7, null);
                    TextView textview3 = (TextView)linearlayout1.findViewById(0x7f0a0209);
                    TextView textview4 = (TextView)linearlayout1.findViewById(0x7f0a0227);
                    ProgressBar progressbar = (ProgressBar)linearlayout1.findViewById(0x7f0a020a);
                    textview3.setText((new StringBuilder()).append(i).toString());
                    long l1 = sellerpagemodel.getRatingsBreakupCount()[i - 1];
                    progressbar.setProgress((int)((100L * l1) / l));
                    if (l1 <= 1L)
                    {
                        textview4.setText((new StringBuilder()).append(l1).append(" User").toString());
                    } else
                    {
                        textview4.setText((new StringBuilder()).append(l1).append(" Users").toString());
                    }
                    linearlayout.addView(linearlayout1);
                    i--;
                }
            }
            if (sellerpagemodel.isDescriptionVisible())
            {
                String s3 = sellerpagemodel.getDescription();
                ((LinearLayout)view.findViewById(0x7f0a021e)).setVisibility(0);
                TextView textview2 = (TextView)view.findViewById(0x7f0a021f);
                textview2.setTextSize(2, 14F);
                textview2.setText(s3);
            }
            if (sellerpagemodel.isPoliciesVisible())
            {
                String s2 = sellerpagemodel.getSellerPolicy();
                RelativeLayout relativelayout = (RelativeLayout)view.findViewById(0x7f0a0220);
                TextView textview1 = (TextView)view.findViewById(0x7f0a0223);
                textview1.setTextSize(2, 14F);
                textview1.setText(Html.fromHtml(s2));
                relativelayout.setVisibility(0);
            }
            if (sellerpagemodel.isSellerUgcVisible())
            {
                a(sellerpagemodel, view);
            }
        }
    }
}
