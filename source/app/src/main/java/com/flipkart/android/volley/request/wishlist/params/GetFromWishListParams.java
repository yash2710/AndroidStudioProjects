// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.wishlist.params;


public class GetFromWishListParams
{

    private int a;
    private int b;
    private int c;

    public GetFromWishListParams(int i, int j, int k)
    {
        a = i;
        b = j;
        c = k;
    }

    public String generateURI()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("?");
        stringbuilder.append("start=").append(a).append("&");
        stringbuilder.append("count=").append(b).append("&");
        if (c >= 0)
        {
            stringbuilder.append("infoLevel=").append(c);
        }
        if (stringbuilder.charAt(-1 + stringbuilder.length()) == '&')
        {
            stringbuilder.deleteCharAt(-1 + stringbuilder.length());
        }
        return stringbuilder.toString();
    }

    public int getCount()
    {
        return b;
    }

    public int getInfoLevel()
    {
        return c;
    }

    public int getStart()
    {
        return a;
    }

    public void setCount(int i)
    {
        b = i;
    }

    public void setInfoLevel(int i)
    {
        c = i;
    }

    public void setStart(int i)
    {
        a = i;
    }
}
