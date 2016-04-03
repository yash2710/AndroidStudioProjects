// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import android.os.AsyncTask;
import com.flipkart.android.DB.ProductDiscovery;
import com.flipkart.android.DB.ProductDiscoveryDao;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.discovery.DiscoveryResponse;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.GsonUtils;
import com.flipkart.android.utils.HashUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.volley.filters.SearchDataFilter;
import com.flipkart.android.volley.request.discovery.params.SearchParam;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.UUID;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler, k, l

public abstract class SearchVDataHander extends BaseVDataHandler
{

    private String a;
    private String b;
    private SearchParam c;
    private AnalyticData d;

    public SearchVDataHander()
    {
        a = null;
        b = null;
        c = null;
        d = null;
        b = UUID.randomUUID().toString();
        requestFilter = new SearchDataFilter(b);
    }

    static DiscoveryResponse a(SearchVDataHander searchvdatahander, String s)
    {
        System.currentTimeMillis();
        ProductDiscovery productdiscovery = (new ProductDiscoveryDao(FlipkartApplication.getAppContext())).getProductDiscoveryById(HashUtils.md5(s));
        DiscoveryResponse discoveryresponse = null;
        if (productdiscovery != null)
        {
            long l1 = AppConfigUtils.getInstance().getProductDiscoveryDbTimeout();
            int i = ScreenMathUtils.getCurrentLinuxTimeInSeconds() - productdiscovery.getTime() != l1;
            discoveryresponse = null;
            if (i <= 0)
            {
                discoveryresponse = (DiscoveryResponse)GsonUtils.getResponse((new k(searchvdatahander)).getType(), productdiscovery.getResponse(), true);
            }
        }
        return discoveryresponse;
    }

    static SearchParam a(SearchVDataHander searchvdatahander)
    {
        return searchvdatahander.c;
    }

    static String b(SearchVDataHander searchvdatahander)
    {
        return searchvdatahander.b;
    }

    static AnalyticData c(SearchVDataHander searchvdatahander)
    {
        return searchvdatahander.d;
    }

    public void doSearch(String s, String s1, String s2, String s3, String as[], String s4, String as1[], 
            int i, int j, int i1, boolean flag, AnalyticData analyticdata, String as2[])
    {
        c = new SearchParam(s, s2, s3, j, i, s1, as, as1, s4, i1, flag, as2);
        d = analyticdata;
        a = c.generateURI();
        l l1 = new l(this);
        String as3[] = new String[1];
        as3[0] = a;
        if (!(l1 instanceof AsyncTask))
        {
            l1.execute(as3);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)l1, as3);
            return;
        }
    }

    public String getUrl()
    {
        return a;
    }
}
