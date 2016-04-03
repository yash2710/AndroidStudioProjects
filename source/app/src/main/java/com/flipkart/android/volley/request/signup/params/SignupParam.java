// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.signup.params;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SignupParam
{

    private String a;
    private String b;

    public SignupParam(String s, String s1)
    {
        a = s;
        b = s1;
    }

    public byte[] generateToByteArray()
    {
        StringBuilder stringbuilder = new StringBuilder();
        try
        {
            if (a != null && a.length() > 0)
            {
                stringbuilder.append("email=").append(URLEncoder.encode(a, "UTF-8"));
            }
            if (b != null && b.length() > 0)
            {
                stringbuilder.append("&password=").append(URLEncoder.encode(b, "UTF-8"));
            }
        }
        catch (UnsupportedEncodingException unsupportedencodingexception) { }
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

    public String getPassword()
    {
        return b;
    }

    public void setEmail(String s)
    {
        a = s;
    }

    public void setPassword(String s)
    {
        b = s;
    }
}
