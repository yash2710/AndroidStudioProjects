// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.DB.FlipkartProductInfo;
import com.flipkart.android.DB.FlipkartProductInfoDao;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.productInfo.ProductInfoResponse;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.GsonUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.volley.filters.ProductInfoDataFilter;
import com.flipkart.android.volley.request.discovery.ProductInfoRequest;
import com.flipkart.android.volley.request.discovery.params.ProductInfoParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class ProductInfoVDataHandler extends BaseVDataHandler
{

    private String a;

    public ProductInfoVDataHandler(int i)
    {
        a = UUID.randomUUID().toString();
        requestFilter = new ProductInfoDataFilter(a);
    }

    public void fetchProductInfoForProducts(List list, HashMap hashmap, HashMap hashmap1, String s, int i, AnalyticData analyticdata)
    {
        if (list != null && list.size() != 0)
        {
            String s1;
            String s2;
            ArrayList arraylist;
            HashMap hashmap2;
            ArrayList arraylist1;
            ArrayList arraylist2;
            if (StringUtils.isNullOrEmpty(s))
            {
                s1 = "";
            } else
            {
                s1 = s;
            }
            System.currentTimeMillis();
            s2 = "";
            arraylist = new ArrayList();
            hashmap2 = new HashMap();
            if (list != null && list.size() != 0)
            {
                List list1 = (new FlipkartProductInfoDao(FlipkartApplication.getAppContext())).getFlipkartProductInfoList(new ArrayList(list));
                if (list1 != null)
                {
                    arraylist.addAll(list);
                    for (int l = 0; l < list1.size();)
                    {
                        FlipkartProductInfo flipkartproductinfo = (FlipkartProductInfo)list1.get(l);
                        ProductInfo productinfo = (ProductInfo)GsonUtils.getResponse(com/flipkart/android/response/productInfo/ProductInfo, flipkartproductinfo.getResponse(), true);
                        String s3 = productinfo.getRequestId();
                        if (productinfo != null && !StringUtils.isNullOrEmpty(productinfo.getProductId()))
                        {
                            hashmap2.put(productinfo.getProductId(), productinfo);
                        }
                        long l1 = AppConfigUtils.getInstance().getProductInfoDbTimeout();
                        if (ScreenMathUtils.getCurrentLinuxTimeInSeconds() - flipkartproductinfo.getTime() <= l1 && productinfo != null && productinfo.getInfoLevel() <= i && productinfo != null)
                        {
                            String s4 = productinfo.getPin();
                            if (StringUtils.isNullOrEmpty(s4))
                            {
                                s4 = "";
                            }
                            if (s4.equals(s1))
                            {
                                arraylist.remove(productinfo.getProductId());
                            }
                        }
                        l++;
                        s2 = s3;
                    }

                }
            }
            if (hashmap2.size() > 0)
            {
                ProductInfoResponse productinforesponse = new ProductInfoResponse();
                productinforesponse.setProductInfo(hashmap2);
                productinforesponse.setRequestId(s2);
                int k;
                if (arraylist.size() != 0)
                {
                    resultReceived(productinforesponse, true);
                } else
                {
                    resultReceived(productinforesponse, false);
                }
            }
            arraylist1 = new ArrayList();
            if (!StringUtils.isNullOrEmpty(hashmap))
            {
                for (k = 0; k < arraylist.size(); k++)
                {
                    arraylist1.add(hashmap.get(arraylist.get(k)));
                }

            }
            arraylist2 = new ArrayList();
            if (!StringUtils.isNullOrEmpty(hashmap1))
            {
                for (int j = 0; j < arraylist.size(); j++)
                {
                    arraylist2.add(hashmap1.get(arraylist.get(j)));
                }

            }
            if (arraylist.size() != 0)
            {
                String as[] = new String[arraylist.size()];
                String as1[] = new String[arraylist1.size()];
                String as2[] = new String[arraylist2.size()];
                arraylist.toArray(as);
                arraylist1.toArray(as1);
                arraylist2.toArray(as2);
                ProductInfoRequest productinforequest = new ProductInfoRequest(new ProductInfoParam(as, as1, as2, s1, i), listner, errorListner, a, analyticdata);
                request = productinforequest;
                FlipkartApplication.getRequestQueue().add(productinforequest);
                return;
            }
        }
    }
}
