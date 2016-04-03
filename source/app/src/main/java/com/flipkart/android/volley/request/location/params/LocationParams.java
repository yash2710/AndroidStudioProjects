// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.location.params;


public class LocationParams
{

    private String a;
    private String b;

    public LocationParams(String s, String s1)
    {
        a = s;
        b = s1;
    }

    public byte[] generateToByteArray()
    {
        if (a != null)
        {
            return a.getBytes();
        } else
        {
            return null;
        }
    }

    public String generateURI()
    {
        return "";
    }

    public String getMessage()
    {
        return a;
    }

    public String getType()
    {
        return b;
    }

    public void setMessage(String s)
    {
        a = s;
    }

    public void setType(String s)
    {
        b = s;
    }
}
