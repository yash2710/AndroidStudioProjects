// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.customwidget.DotdProductWidget;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.DotdValue;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.flipkart.android.utils:
//            ScreenMathUtils

public class DotdWidgetBuilder
{

    public DotdWidgetBuilder()
    {
    }

    public static boolean buildDotdWidget(Activity activity, Context context, android.view.View.OnClickListener onclicklistener, ImageLoader imageloader, ArrayList arraylist, LinearLayout linearlayout, boolean flag, Action action)
    {
        linearlayout.removeAllViews();
        int i = 0;
        if (arraylist != null)
        {
            int j = arraylist.size();
            i = 0;
            if (j > 0)
            {
                for (int k = 0; k < arraylist.size(); k++)
                {
                    WidgetItem widgetitem = (WidgetItem)arraylist.get(k);
                    if (widgetitem == null)
                    {
                        continue;
                    }
                    DotdValue dotdvalue = (DotdValue)widgetitem.getValue();
                    Action action1 = widgetitem.getAction();
                    if (dotdvalue == null || action1 == null)
                    {
                        continue;
                    }
                    for (int l = 0; l < dotdvalue.getProducts().size() && l <= 0; l++)
                    {
                        DotdProductWidget dotdproductwidget = new DotdProductWidget(context, dotdvalue, imageloader, l);
                        dotdproductwidget.setTag(action1);
                        dotdproductwidget.setOnClickListener(onclicklistener);
                        linearlayout.addView(dotdproductwidget);
                        View view = new View(context);
                        view.setBackgroundColor(0xffdcdcdc);
                        linearlayout.addView(view, new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(1, context), -1));
                        i++;
                    }

                }

            }
        }
        return i > 0;
    }
}
