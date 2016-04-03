// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.component.params;


public class ComponentDataParam
{

    private String a;
    private String b[];
    private int c[];

    public ComponentDataParam()
    {
    }

    public String generateURI()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("/").append(a).append("/widgets");
        return stringbuilder.toString();
    }

    public int[] getHashCodes()
    {
        return c;
    }

    public byte[] getPostData()
    {
        if (b == null || b.length == 0)
        {
            return null;
        }
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{");
        int i = 0;
        while (i < b.length) 
        {
            Integer integer;
            if (c[i] != 0)
            {
                integer = Integer.valueOf(c[i]);
            } else
            {
                integer = null;
            }
            stringbuilder.append("\"").append(b[i]).append("\":").append(integer).append(",");
            i++;
        }
        stringbuilder.replace(-1 + stringbuilder.length(), stringbuilder.length(), "");
        stringbuilder.append("}");
        return stringbuilder.toString().getBytes();
    }

    public String getScreenName()
    {
        return a;
    }

    public String[] getWidgets()
    {
        return b;
    }

    public void setHashCodes(int ai[])
    {
        c = ai;
    }

    public void setScreenName(String s)
    {
        a = s;
    }

    public void setWidgets(String as[])
    {
        b = as;
    }
}
