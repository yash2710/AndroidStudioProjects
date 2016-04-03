// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.discovery;

import com.flipkart.android.DB.FlipkartProductInfo;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.productInfo.ProductInfoResponse;
import com.flipkart.android.utils.GZipCompressorUtil;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.discovery.params.ProductInfoParam;
import com.google.mygson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.volley.request.discovery:
//            a, b

public class ProductInfoRequest extends GsonRequest
{

    private String a;

    public ProductInfoRequest(ProductInfoParam productinfoparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, String s, AnalyticData analyticdata)
    {
        super(0, constructGetUri("2", "discover/productInfo", productinfoparam.generateURI()), null, (new a()).getType(), listener, errorlistener, analyticdata);
        a = null;
        a = s;
    }

    public String getRequestId()
    {
        return a;
    }

    public void performJsonUpdate(byte abyte0[], ProductInfoResponse productinforesponse, boolean flag)
    {
        if (productinforesponse != null)
        {
            Map map = productinforesponse.getProductInfo();
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
                            productinfo.setRequestId(productinforesponse.getRequestId());
                            arraylist.add(new FlipkartProductInfo(s, l, GZipCompressorUtil.compress(FlipkartApplication.getGsonInstance().toJson(productinfo).getBytes())));
                        }
                    }
                } while (true);
            }
            if (arraylist.size() > 0)
            {
                (new b(this, arraylist)).start();
            }
        }
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (ProductInfoResponse)obj, flag);
    }

    public void setRequestId(String s)
    {
        a = s;
    }
}
