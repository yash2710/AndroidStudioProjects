// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.offerTerms.param;

import com.flipkart.android.utils.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class OfferTermParam
{

    private String a;

    public OfferTermParam(String s)
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
                stringbuilder.append("offerId=").append(URLEncoder.encode(a, "UTF-8"));
            }
            catch (UnsupportedEncodingException unsupportedencodingexception) { }
        }
        return stringbuilder.toString().getBytes();
    }

    public String generateURI()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("?offerId=").append(a);
        return stringbuilder.toString();
    }

    public String getOfferId()
    {
        return a;
    }

    public void setOfferId(String s)
    {
        a = s;
    }
}
