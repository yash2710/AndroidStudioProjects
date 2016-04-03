// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.seller;

import java.util.LinkedHashMap;
import java.util.Map;

public class SellerResponse
{

    private Map sellerInfoResponse;
    private Map sellerPolicy;
    private Map sellerUgcResponse;

    public SellerResponse()
    {
        sellerPolicy = new LinkedHashMap();
        sellerInfoResponse = new LinkedHashMap();
        sellerUgcResponse = new LinkedHashMap();
    }

    public Map getSellerInfoResponse()
    {
        if (sellerInfoResponse == null)
        {
            sellerInfoResponse = new LinkedHashMap();
        }
        return sellerInfoResponse;
    }

    public Map getSellerPolicy()
    {
        if (sellerPolicy == null)
        {
            sellerPolicy = new LinkedHashMap();
        }
        return sellerPolicy;
    }

    public Map getSellerUgcResponse()
    {
        if (sellerUgcResponse == null)
        {
            sellerUgcResponse = new LinkedHashMap();
        }
        return sellerUgcResponse;
    }

    public void setSellerInfoResponse(Map map)
    {
        sellerInfoResponse = map;
    }

    public void setSellerPolicy(Map map)
    {
        sellerPolicy = map;
    }

    public void setSellerUgcResponse(Map map)
    {
        sellerUgcResponse = map;
    }
}
