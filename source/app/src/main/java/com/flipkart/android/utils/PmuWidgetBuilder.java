// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.DB.FlipkartProductInfo;
import com.flipkart.android.DB.FlipkartProductInfoDao;
import com.flipkart.android.customviews.PmuProductLayout;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.PMUValue;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.google.mygson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.utils:
//            ScreenMathUtils, GZipCompressorUtil, StringUtils, k

public class PmuWidgetBuilder
{

    public PmuWidgetBuilder()
    {
    }

    static void a(ProductInfo productinfo)
    {
        FlipkartProductInfoDao flipkartproductinfodao = new FlipkartProductInfoDao(FlipkartApplication.getAppContext());
        long l = ScreenMathUtils.getCurrentLinuxTimeInSeconds();
        productinfo.setLastUpdatedTimeStamp(l);
        flipkartproductinfodao.create(new FlipkartProductInfo(productinfo.getProductId(), l, GZipCompressorUtil.compress(FlipkartApplication.getGsonInstance().toJson(productinfo).getBytes())), false);
    }

    public static boolean buildPmuWidget(Context context, android.view.View.OnClickListener onclicklistener, ImageLoader imageloader, ArrayList arraylist, LinearLayout linearlayout, boolean flag, Action action)
    {
        ArrayList arraylist1 = new ArrayList();
        linearlayout.removeAllViews();
        int i = 0;
        int j = 0;
        ArrayList arraylist2 = new ArrayList();
        for (int l = 0; l < arraylist.size(); l++)
        {
            WidgetItem widgetitem1 = (WidgetItem)arraylist.get(l);
            if (widgetitem1 == null || widgetitem1.getAction() == null)
            {
                continue;
            }
            Map map2 = widgetitem1.getAction().getParams();
            if (map2 == null)
            {
                continue;
            }
            String s1 = (String)map2.get("pid");
            if (!StringUtils.isNullOrEmpty(s1))
            {
                arraylist2.add(s1);
            }
        }

        View view = null;
        int i1 = 0;
        while (i1 < arraylist.size()) 
        {
            WidgetItem widgetitem = (WidgetItem)arraylist.get(i1);
            Thread thread;
            View view1;
            int j1;
            if (widgetitem != null && widgetitem.getValue() != null)
            {
                PmuProductLayout pmuproductlayout = new PmuProductLayout(context, widgetitem, "", imageloader);
                Action action1 = widgetitem.getAction();
                if (action1 != null && action != null)
                {
                    Map map = action1.getParams();
                    Map map1 = action.getParams();
                    if (map != null && map1 != null)
                    {
                        Iterator iterator = map1.keySet().iterator();
                        do
                        {
                            if (!iterator.hasNext())
                            {
                                break;
                            }
                            String s = (String)iterator.next();
                            if (!StringUtils.isNullOrEmpty(s) && map1.get(s) != null)
                            {
                                map.put(s, map1.get(s));
                            }
                        } while (true);
                        map.put("position", Integer.valueOf(i));
                        if (!map.containsKey("pids"))
                        {
                            map.put("product_list_ids", arraylist2);
                        }
                        i++;
                    }
                }
                pmuproductlayout.setTag(action1);
                pmuproductlayout.setOnClickListener(onclicklistener);
                linearlayout.addView(pmuproductlayout);
                int k1 = j + 1;
                if (flag)
                {
                    ProductInfo productinfo = ((PMUValue)widgetitem.getValue()).getProduct();
                    if (productinfo != null)
                    {
                        arraylist1.add(productinfo);
                    }
                }
                view1 = new View(context);
                view1.setBackgroundColor(0xffdcdcdc);
                linearlayout.addView(view1, new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(1, context), -1));
                j1 = k1;
            } else
            {
                view1 = view;
                j1 = j;
            }
            i1++;
            j = j1;
            view = view1;
        }
        if (view != null)
        {
            linearlayout.removeView(view);
        }
        thread = new Thread(new k(arraylist1));
        if (arraylist1.size() > 0)
        {
            thread.start();
        }
        return j > 0;
    }
}
