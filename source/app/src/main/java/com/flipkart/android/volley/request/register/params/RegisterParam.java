// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.register.params;

import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RegisterParam
{

    private String a;

    public RegisterParam(String s)
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
                stringbuilder.append("checksum=").append(URLEncoder.encode(a, "UTF-8"));
            }
            catch (UnsupportedEncodingException unsupportedencodingexception) { }
        }
        return stringbuilder.toString().getBytes();
    }

    public String generateURI()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("?");
        if (!StringUtils.isNullOrEmpty(a))
        {
            try
            {
                stringbuilder.append("checksum=").append(a);
            }
            catch (Exception exception)
            {
                FkLogger.printStackTrace(exception);
            }
        }
        return stringbuilder.toString();
    }

    public String getHash()
    {
        return a;
    }

    public void setHash(String s)
    {
        a = s;
    }
}
