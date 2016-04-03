// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.notifyme.params;

import com.flipkart.android.utils.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class NotifymeParam
{

    private String a;
    private String b;

    public NotifymeParam(String s, String s1)
    {
        a = s;
        b = s1;
    }

    public byte[] generateToByteArray()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (!StringUtils.isNullOrEmpty(a))
        {
            try
            {
                stringbuilder.append("email=").append(URLEncoder.encode(a, "UTF-8"));
            }
            catch (UnsupportedEncodingException unsupportedencodingexception1) { }
        }
        if (!StringUtils.isNullOrEmpty(b))
        {
            try
            {
                stringbuilder.append("&productId=").append(URLEncoder.encode(b, "UTF-8"));
            }
            catch (UnsupportedEncodingException unsupportedencodingexception) { }
        }
        return stringbuilder.toString().getBytes();
    }

    public String generateURI()
    {
        return "";
    }

    public String getEmail()
    {
        return a;
    }

    public String getProductId()
    {
        return b;
    }

    public void setEmail(String s)
    {
        a = s;
    }

    public void setProductId(String s)
    {
        b = s;
    }
}
