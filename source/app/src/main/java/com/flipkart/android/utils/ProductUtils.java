// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.response.discovery.OmnitureData;
import com.flipkart.android.response.productInfo.ProductInfo;

public class ProductUtils
{

    public ProductUtils()
    {
    }

    public static String getCategory(ProductInfo productinfo)
    {
        if (productinfo == null)
        {
            return "";
        }
        OmnitureData omnituredata = productinfo.getOmnitureData();
        if (omnituredata == null)
        {
            return "";
        } else
        {
            return omnituredata.getCategory();
        }
    }
}
