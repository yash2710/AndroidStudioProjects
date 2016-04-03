// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.customwidget.BucketWidget;
import com.flipkart.android.customwidget.ProductWidget;
import com.flipkart.android.customwidget.ViewMoreWidget;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.OMUValue;
import java.util.ArrayList;

public class OmuWidgetBuilder
{

    public OmuWidgetBuilder()
    {
    }

    public static boolean buildOmuWidget(Activity activity, Context context, android.view.View.OnClickListener onclicklistener, ImageLoader imageloader, ArrayList arraylist, LinearLayout linearlayout, boolean flag, Action action)
    {
        linearlayout.removeAllViews();
        int i = 0;
        if (arraylist != null)
        {
            int j = arraylist.size();
            i = 0;
            if (j > 0)
            {
                int k = 0;
                int l = 0;
                while (k < arraylist.size()) 
                {
                    WidgetItem widgetitem = (WidgetItem)arraylist.get(k);
                    if (widgetitem != null)
                    {
                        OMUValue omuvalue = (OMUValue)widgetitem.getValue();
                        Action action1 = widgetitem.getAction();
                        if (omuvalue != null)
                        {
                            int i1;
                            if (!omuvalue.isBanner())
                            {
                                ProductWidget productwidget = new ProductWidget(activity, context, omuvalue, imageloader, "horizontal");
                                productwidget.setTag(action1);
                                productwidget.setOnClickListener(onclicklistener);
                                productwidget.setContentDescription((new StringBuilder("DotdProduct_")).append(k).toString());
                                linearlayout.addView(productwidget);
                            } else
                            {
                                BucketWidget bucketwidget = new BucketWidget(context, omuvalue);
                                bucketwidget.setTag(action1);
                                bucketwidget.setOnClickListener(onclicklistener);
                                linearlayout.addView(bucketwidget);
                            }
                            if (++l >= 8 && action != null)
                            {
                                break;
                            }
                        }
                    }
                    i1 = l;
                    k++;
                    l = i1;
                }
                i = l;
                if (action != null)
                {
                    ViewMoreWidget viewmorewidget = new ViewMoreWidget(context, imageloader);
                    viewmorewidget.setOnClickListener(onclicklistener);
                    viewmorewidget.setTag(action);
                    viewmorewidget.setContentDescription("ViewMore");
                    linearlayout.addView(viewmorewidget);
                }
            }
        }
        return i > 0;
    }
}
