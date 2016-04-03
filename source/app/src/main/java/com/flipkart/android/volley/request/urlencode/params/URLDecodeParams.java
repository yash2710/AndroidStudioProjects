// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.urlencode.params;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLDecodeParams
{

    private String a;

    public URLDecodeParams(String s)
    {
        a = s;
    }

    public String generateURI()
    {
        if (a != null)
        {
            String s;
            try
            {
                s = (new StringBuilder("?encodedUrl=")).append(URLEncoder.encode(a, "UTF-8")).toString();
            }
            catch (UnsupportedEncodingException unsupportedencodingexception)
            {
                return "";
            }
            return s;
        } else
        {
            return "";
        }
    }
}
