// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import com.flipkart.android.response.baseresponse.BaseResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductInfoResponse extends BaseResponse
{

    private Map productInfo;

    public ProductInfoResponse()
    {
        productInfo = new LinkedHashMap();
    }

    public Map getProductInfo()
    {
        if (productInfo == null)
        {
            productInfo = new LinkedHashMap();
        }
        return productInfo;
    }

    public void setProductInfo(Map map)
    {
        productInfo = map;
    }
}
