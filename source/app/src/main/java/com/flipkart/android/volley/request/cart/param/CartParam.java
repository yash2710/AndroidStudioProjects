// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.cart.param;

import com.flipkart.android.utils.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CartParam
{

    private String a;

    public CartParam(String s)
    {
        a = s;
    }

    public byte[] generateToByteArray()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (!StringUtils.isNullOrEmpty(a))
        {
            try
            {
                stringbuilder.append("listingId=").append(URLEncoder.encode(a, "UTF-8"));
            }
            catch (UnsupportedEncodingException unsupportedencodingexception) { }
        }
        return stringbuilder.toString().getBytes();
    }

    public String generateURI()
    {
        return "";
    }

    public String getListingId()
    {
        return a;
    }

    public void setListingId(String s)
    {
        a = s;
    }
}
