// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.flipkart.android.customviews.OfferExpandablePanel;
import com.flipkart.android.datahandler.ProductUgcVDataHandler;
import com.flipkart.android.fragments.model.ProductPageUgcModel;
import com.flipkart.android.response.ugc.UGCReviewDetail;
import java.text.DateFormatSymbols;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.utils:
//            e, ProductPageReviewContext, StringUtils, v, 
//            u, t

public class ProductPageUgcBuilder
{

    private static ListView a;
    private static v b;
    private static ProductPageReviewContext c;
    private static LayoutInflater d;
    private static ProductUgcVDataHandler e;
    private static boolean f = false;
    private static boolean g = false;
    private static Context h;

    public ProductPageUgcBuilder()
    {
    }

    static Context a()
    {
        return h;
    }

    static View a(int i, View view)
    {
        return b(i, view);
    }

    static void a(boolean flag)
    {
        d(false);
    }

    private static View b(int i, View view)
    {
        String s1;
        e e2;
        String s;
        String as[];
        int j;
        String s2;
        String s3;
        if (view == null || view.getTag() == null)
        {
            LinearLayout linearlayout = (LinearLayout)d.inflate(0x7f030093, null);
            e e1 = new e();
            e1.a = (TextView)linearlayout.findViewById(0x7f0a01cb);
            e1.b = (TextView)linearlayout.findViewById(0x7f0a01cc);
            e1.c = (TextView)linearlayout.findViewById(0x7f0a01d2);
            e1.d = (TextView)linearlayout.findViewById(0x7f0a01d1);
            e1.e = (TextView)linearlayout.findViewById(0x7f0a01d5);
            e1.f = (TextView)linearlayout.findViewById(0x7f0a01d6);
            e1.g = (RatingBar)linearlayout.findViewById(0x7f0a01cd);
            e1.h = (TextView)linearlayout.findViewById(0x7f0a01ce);
            linearlayout.setTag(e1);
            e2 = e1;
            view = linearlayout;
        } else
        {
            e2 = (e)view.getTag();
        }
        if (!StringUtils.isNullOrEmpty(((UGCReviewDetail)c.getReviewList().get(i)).getAuthor()))
        {
            e2.a.setText(((UGCReviewDetail)c.getReviewList().get(i)).getAuthor());
        }
        if (!StringUtils.isNullOrEmpty(((UGCReviewDetail)c.getReviewList().get(i)).getTitle()))
        {
            e2.d.setVisibility(0);
            e2.d.setText(((UGCReviewDetail)c.getReviewList().get(i)).getTitle());
        } else
        {
            e2.d.setVisibility(8);
        }
        s = ((UGCReviewDetail)c.getReviewList().get(i)).getReviewText();
        if (!StringUtils.isNullOrEmpty(s))
        {
            e2.c.setText(s);
        }
        if (s.length() != 0)
        {
            ((LinearLayout)view.findViewById(0x7f0a01d4)).setVisibility(0);
            e2.e.setText((new StringBuilder()).append(((UGCReviewDetail)c.getReviewList().get(i)).getYesCount()).toString());
            e2.f.setText((new StringBuilder()).append(((UGCReviewDetail)c.getReviewList().get(i)).getYesNoCount()).toString());
        }
        if (((UGCReviewDetail)c.getReviewList().get(i)).isCertifiedBuyer())
        {
            e2.h.setVisibility(0);
        } else
        {
            e2.h.setVisibility(8);
        }
        as = ((UGCReviewDetail)c.getReviewList().get(i)).getDate().split(" ")[0].split("/");
        j = Integer.parseInt(as[1]);
        s2 = (new DateFormatSymbols()).getMonths()[j - 1];
        s3 = (new StringBuilder()).append(as[0]).append(" ").append(s2).append(" ").append(as[2]).toString();
        s1 = s3;
_L2:
        e2.b.setText(s1);
        e2.g.setRating(((UGCReviewDetail)c.getReviewList().get(i)).getRating());
        ((OfferExpandablePanel)view.findViewById(0x7f0a01cf)).collapse();
        return view;
        Exception exception;
        exception;
        s1 = "";
        if (true) goto _L2; else goto _L1
_L1:
    }

    static ProductPageReviewContext b()
    {
        return c;
    }

    static boolean b(boolean flag)
    {
        f = true;
        return true;
    }

    public static void buildProductPageUgc(ProductPageUgcModel productpageugcmodel, View view)
    {
        if (StringUtils.isNull(productpageugcmodel))
        {
            return;
        } else
        {
            h = view.getContext();
            d = (LayoutInflater)view.getContext().getSystemService("layout_inflater");
            a = (ListView)view.findViewById(0x7f0a01d7);
            c = productpageugcmodel.getProductPageReviewContext();
            ListView listview = a;
            b = new v();
            listview.setAdapter(b);
            listview.setOnScrollListener(new u());
            e = new t();
            return;
        }
    }

    static v c()
    {
        return b;
    }

    static boolean c(boolean flag)
    {
        g = false;
        return false;
    }

    static void d()
    {
        if (!f && !g)
        {
            g = true;
            d(true);
            e.fetchUgcInfo(c.getProductId(), c.getOption(), c.getReviewList().size(), 10);
        }
    }

    private static void d(boolean flag)
    {
        if (flag)
        {
            a.findViewById(0x7f0a0126).setVisibility(0);
            return;
        } else
        {
            a.findViewById(0x7f0a0126).setVisibility(8);
            return;
        }
    }

    public static void destroyAdapter()
    {
        if (e != null)
        {
            e.cancelRequests();
        }
        b = null;
    }

    public static void removeProductPageReviewList(String s)
    {
        f = false;
        g = false;
        c.setOption(s);
        c.getReviewList().clear();
        if (b != null)
        {
            b.notifyDataSetChanged();
        }
    }

    public static void updateVars()
    {
        f = false;
        g = false;
    }

}
