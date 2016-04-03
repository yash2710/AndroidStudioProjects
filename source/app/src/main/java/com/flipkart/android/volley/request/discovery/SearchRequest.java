// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.discovery;

import com.flipkart.android.DB.FlipkartProductInfo;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.discovery.DiscoveryResponse;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.GZipCompressorUtil;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.discovery.params.SearchParam;
import com.google.mygson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.volley.request.discovery:
//            c, d, e

public class SearchRequest extends GsonRequest
{

    private final SearchParam a;
    private String b;

    public SearchRequest(SearchParam searchparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, String s, AnalyticData analyticdata)
    {
        super(0, constructGetUri("2", "discover/getSearch", searchparam.generateURI()), null, (new c()).getType(), listener, errorlistener, analyticdata);
        b = null;
        a = searchparam;
        b = s;
    }

    static SearchParam a(SearchRequest searchrequest)
    {
        return searchrequest.a;
    }

    public String getRequestId()
    {
        return b;
    }

    public void performJsonUpdate(byte abyte0[], DiscoveryResponse discoveryresponse, boolean flag)
    {
        if (discoveryresponse != null)
        {
            Map map = discoveryresponse.getProductInfoMap();
            ArrayList arraylist = new ArrayList();
            if (map != null)
            {
                Iterator iterator = map.keySet().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    String s = (String)iterator.next();
                    if (!StringUtils.isNullOrEmpty(s))
                    {
                        ProductInfo productinfo = (ProductInfo)map.get(s);
                        if (productinfo != null)
                        {
                            long l = ScreenMathUtils.getCurrentLinuxTimeInSeconds();
                            productinfo.setLastUpdatedTimeStamp(l);
                            productinfo.setRequestId(discoveryresponse.getRequestId());
                            arraylist.add(new FlipkartProductInfo(s, l, GZipCompressorUtil.compress(FlipkartApplication.getGsonInstance().toJson(productinfo).getBytes())));
                        }
                    }
                } while (true);
            }
            if (arraylist.size() > 0)
            {
                (new d(this, arraylist)).start();
            }
            (new e(this, abyte0)).start();
        }
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (DiscoveryResponse)obj, flag);
    }

    public void setRequestId(String s)
    {
        b = s;
    }
}
