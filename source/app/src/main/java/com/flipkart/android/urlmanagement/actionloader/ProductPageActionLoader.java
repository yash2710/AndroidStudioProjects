// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement.actionloader;

import android.app.Activity;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.ProductFindingMethod;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.urlmanagement.AppParams;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.urlmanagement.actionloader:
//            AppActionLoader

public class ProductPageActionLoader extends AppActionLoader
{

    private static String a = "pid";
    private static String b = "source";
    private static String c = "cart";
    private static String d = "lid";

    public ProductPageActionLoader(AppParams appparams, Activity activity)
    {
        super(appparams, activity);
    }

    public void load()
    {
        String s = (String)getQueryParams().get(a);
        String s1 = (String)getQueryParams().get(b);
        String s2;
        for (s2 = (String)getQueryParams().get(d); StringUtils.isNullOrEmpty(s) || !(activity instanceof HomeFragmentHolderActivity);)
        {
            return;
        }

        HomeFragmentHolderActivity homefragmentholderactivity = (HomeFragmentHolderActivity)activity;
        if (!StringUtils.isNullOrEmpty(s1) && s1.equalsIgnoreCase(c))
        {
            TrackingHelper.setProductFindingMethod(ProductFindingMethod.Cart.name());
            homefragmentholderactivity.getAndUpdateCart(s);
            return;
        } else
        {
            ArrayList arraylist = new ArrayList();
            ArrayList arraylist1 = new ArrayList();
            arraylist.add(s);
            arraylist1.add(s2);
            homefragmentholderactivity.openProductPage(arraylist, arraylist1, 0, "", new AnalyticData(null, null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
            return;
        }
    }

}
