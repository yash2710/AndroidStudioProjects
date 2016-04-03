// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.DB.BrowseHistoryDao;
import com.flipkart.android.DB.FlipkartProductInfo;
import com.flipkart.android.DB.FlipkartProductInfoDao;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.PMUValue;
import com.flipkart.android.response.customwidgetitemvalue.TrackingParams;
import com.flipkart.android.response.productInfo.ProductInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils, GsonUtils

public class BrowseHistoryUtils
{

    public BrowseHistoryUtils()
    {
    }

    public static ArrayList getHistoryProdIds()
    {
        return (new BrowseHistoryDao(FlipkartApplication.getAppContext())).getAllBrowseHistoryPids();
    }

    public static ArrayList getHistoryProducts(String s, String s1)
    {
        HashMap hashmap;
        ArrayList arraylist;
        ArrayList arraylist1;
        ArrayList arraylist2;
        ArrayList arraylist3;
        FlipkartProductInfoDao flipkartproductinfodao;
        List list;
        hashmap = new HashMap();
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        arraylist2 = new ArrayList();
        new HashMap();
        arraylist3 = new ArrayList();
        ArrayList arraylist4 = (new BrowseHistoryDao(FlipkartApplication.getAppContext())).getAllBrowseHistoryPids();
        boolean flag = false;
        int i = 0;
        for (int j = 0; j < arraylist4.size(); j++)
        {
            String s5 = (String)arraylist4.get(j);
            if (StringUtils.isNullOrEmpty(s5))
            {
                continue;
            }
            flag = true;
            if (i < 6)
            {
                arraylist1.add(s5);
                i++;
            }
            arraylist2.add(s5);
        }

        if (!flag)
        {
            break MISSING_BLOCK_LABEL_673;
        }
        flipkartproductinfodao = new FlipkartProductInfoDao(FlipkartApplication.getAppContext());
        list = flipkartproductinfodao.getFlipkartProductInfoList(arraylist2);
        if (list == null || list.size() <= 0) goto _L2; else goto _L1
_L1:
        int j1 = 0;
_L3:
        if (j1 >= list.size())
        {
            break; /* Loop/switch isn't completed */
        }
        String s4 = ((ProductInfo)GsonUtils.getResponse(com/flipkart/android/response/productInfo/ProductInfo, ((FlipkartProductInfo)list.get(j1)).getResponse(), true)).getPreferredListingId();
        if (s4 == null)
        {
            s4 = "";
        }
        try
        {
            arraylist3.add(s4);
        }
        catch (Exception exception)
        {
            arraylist3.add("");
        }
        j1++;
        if (true) goto _L3; else goto _L2
_L2:
        List list1 = flipkartproductinfodao.getFlipkartProductInfoList(arraylist1);
        if (list1 != null && list1.size() > 0)
        {
            for (int l = 0; l < list1.size(); l++)
            {
                ProductInfo productinfo = (ProductInfo)GsonUtils.getResponse(com/flipkart/android/response/productInfo/ProductInfo, ((FlipkartProductInfo)list1.get(l)).getResponse(), true);
                if (productinfo == null || StringUtils.isNullOrEmpty(productinfo.getProductId()))
                {
                    continue;
                }
                WidgetItem widgetitem1 = new WidgetItem();
                String s2 = productinfo.getProductId();
                String s3 = productinfo.getMainTitle();
                if (StringUtils.isNullOrEmpty(s3))
                {
                    s3 = "";
                }
                int i1 = l + 1;
                Action action = new Action();
                action.setScreenType("productPage");
                HashMap hashmap1 = new HashMap();
                hashmap1.put("pid", s2);
                hashmap1.put("pids", new JSONArray(arraylist2));
                hashmap1.put("lids", new JSONArray(arraylist3));
                hashmap1.put("title", (new StringBuilder()).append(s).append(" (").append(arraylist2.size()).append(" Results)").toString());
                TrackingParams trackingparams = new TrackingParams();
                trackingparams.setOtracker((new StringBuilder("hp_recently_viewed_")).append(i1).append("_").append(s3).toString());
                trackingparams.setPageType("hp");
                trackingparams.setWidgetType("recently_viewed");
                action.setTracking(trackingparams);
                action.setParams(hashmap1);
                action.getParams().put("REQUEST_ID", s1);
                widgetitem1.setAction(action);
                widgetitem1.setValue(new PMUValue(productinfo));
                hashmap.put(productinfo.getProductId(), widgetitem1);
            }

        }
        for (int k = 0; k < arraylist1.size(); k++)
        {
            WidgetItem widgetitem = (WidgetItem)hashmap.get(arraylist1.get(k));
            if (widgetitem != null)
            {
                arraylist.add(widgetitem);
            }
        }

        return arraylist;
    }
}
